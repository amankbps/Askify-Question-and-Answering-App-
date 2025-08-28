package org.aman.quora.services;

import lombok.RequiredArgsConstructor;
import org.aman.quora.models.Question;
import org.aman.quora.models.QuestionElasticDocument;
import org.aman.quora.repositories.QuestionDocumentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService{

    private final QuestionDocumentRepository questionDocumentRepository;

    @Override
    public void createQuestionIndex(Question question) {
           QuestionElasticDocument document=QuestionElasticDocument.builder()
                   .id(question.getId())
                   .title(question.getTitle())
                   .content(question.getContent())
                   .build();
           questionDocumentRepository.save(document);
    }
}
