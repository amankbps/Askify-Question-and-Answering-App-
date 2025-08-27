package org.aman.quora.services;

import lombok.RequiredArgsConstructor;
import org.aman.quora.adapter.QuestionAdapter;
import org.aman.quora.dto.QuestionRequestDTO;
import org.aman.quora.dto.QuestionResponseDTO;
import org.aman.quora.events.ViewCount;
import org.aman.quora.models.Question;
import org.aman.quora.producers.KafkaEventProducer;
import org.aman.quora.repositories.QuestionRepository;
import org.aman.quora.utils.CursorUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final KafkaEventProducer kafkaEventProducer;


    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {

        Question question = QuestionAdapter.toQuestion(questionRequestDTO);
        return questionRepository.save(question)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(response-> System.out.println("Question created successfully: " + response))
                .doOnError(error -> System.out.println("Error creating question: " + error.getMessage()));
    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(response-> {
                    System.out.println("Question get successfully: " + response);
                    ViewCount viewCount = new ViewCount(id,"question",LocalDateTime.now());
                    kafkaEventProducer.publishViewCountEvent(viewCount);
                })
                .doOnError(error -> System.out.println("Error getting question: " + error.getMessage()));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions(String cursor,int size) {
        Pageable pageable=PageRequest.of(0,size);
        if(!CursorUtils.isValidCursor(cursor)){

            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnComplete(()-> System.out.println("Question get successfully: " + cursor))
                    .doOnError(error -> System.out.println("Error getting question: " + error.getMessage()));
        }else{
            LocalDateTime currentDateTime = CursorUtils.parseCursor(cursor);
            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(currentDateTime,pageable)
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnComplete(()-> System.out.println("Question get successfully: " + cursor))
                    .doOnError(error -> System.out.println("Error getting question: " + error.getMessage()));
        }

    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestion(String query, int offset, int page) {
        return questionRepository.findByTitleOrContentContainingIgnoreCase(query, PageRequest.of(offset,page))
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnComplete(() -> System.out.println("Questions fetched successfully"))
                .doOnError(e -> System.out.println("Error getting questions: " + e.getMessage()));
    }
}
