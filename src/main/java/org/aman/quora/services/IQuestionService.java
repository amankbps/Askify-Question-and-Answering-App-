package org.aman.quora.services;

import org.aman.quora.dto.QuestionRequestDTO;
import org.aman.quora.dto.QuestionResponseDTO;
import org.aman.quora.models.QuestionElasticDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IQuestionService {

     Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
     Mono<QuestionResponseDTO> getQuestionById(String id);
     Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size);
     Flux<QuestionResponseDTO> searchQuestion(String query,int offset,int page);

    List<QuestionElasticDocument> searchQuestionByElasticSearch(String query);
}
