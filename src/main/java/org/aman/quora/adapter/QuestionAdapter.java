package org.aman.quora.adapter;

import org.aman.quora.dto.QuestionRequestDTO;
import org.aman.quora.dto.QuestionResponseDTO;
import org.aman.quora.models.Question;

import java.time.LocalDateTime;

public class QuestionAdapter {

    public static QuestionResponseDTO toQuestionResponseDTO(Question question) {
        return QuestionResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }
    public static Question toQuestion(QuestionRequestDTO questionRequestDTO) {

        return Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

    }
}
