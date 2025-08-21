package org.aman.quora.repositories;

import org.aman.quora.models.Question;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;


@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {

    Flux<Question> findByCreatedAtGreaterThanOrderByCreatedAtAsc(LocalDateTime cursorTimestamp,
                                                                 Pageable pageable);
    @Query("{ '$or': [ {'title':  { $regex: ?0, $options: 'i' }}, {'content': { $regex: ?0, $options: 'i' }} ] }")
    Flux<Question> findByTitleOrContentContainingIgnoreCase(String query, Pageable pageable);

    Flux<Question>findTop10ByOrderByCreatedAtAsc();

}
