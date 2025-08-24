package org.aman.quora.repositories;

import org.aman.quora.models.Like;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LikeRepository extends ReactiveMongoRepository<Like, String> {
}
