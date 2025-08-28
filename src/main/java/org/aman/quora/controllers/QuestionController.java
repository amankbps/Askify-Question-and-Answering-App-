package org.aman.quora.controllers;


import lombok.RequiredArgsConstructor;
import org.aman.quora.dto.QuestionRequestDTO;
import org.aman.quora.dto.QuestionResponseDTO;
import org.aman.quora.models.QuestionElasticDocument;
import org.aman.quora.services.IQuestionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

    private final IQuestionService questionService;
    @PostMapping
    public Mono<QuestionResponseDTO> addQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
         return questionService.createQuestion(questionRequestDTO)
                 .doOnSuccess(response-> System.out.println("Question created successfully: " + response))
                 .doOnError(error -> System.out.println("Error creating question: " + error.getMessage()));
    }


    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id)
                .doOnSuccess(response-> System.out.println("Question get successfully: " + response))
                .doOnError(error -> System.out.println("Error getting question: " + error.getMessage()));
    }

    @GetMapping
    public Flux<QuestionResponseDTO> getAllQuestions(
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "10") int size
    ) {
        return questionService.getAllQuestions(cursor, size)
                .doOnComplete(() -> System.out.println("Questions fetched successfully"))
                .doOnError(e -> System.out.println("Error getting questions: " + e.getMessage()));
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteQuestionById(@PathVariable String id) {
        return null;
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestion(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return questionService.searchQuestion(query, page, size);
    }

    @GetMapping("/tag/{tag}")
    public Flux<QuestionResponseDTO> searchQuestionByTag(
            @PathVariable String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return null;
    }

    @GetMapping("/elastic/search")
    public List<QuestionElasticDocument> searchQuestionByElasticSearch(@RequestParam String query)
    {
         return questionService.searchQuestionByElasticSearch(query);
    }

}
