package org.aman.quora.services;

import org.aman.quora.dto.AnswerRequestDTO;
import org.aman.quora.dto.AnswerResponseDTO;
import reactor.core.publisher.Mono;

public interface IAnswerService {
    Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);
    Mono<AnswerResponseDTO>getAnswer(String id);
}
