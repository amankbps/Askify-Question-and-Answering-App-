package org.aman.quora.repositories;

import org.aman.quora.models.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {

      Flux<Question> findByAuthorId(String authorId);
      Mono<Long> countByAuthorId(String authorId);
}
