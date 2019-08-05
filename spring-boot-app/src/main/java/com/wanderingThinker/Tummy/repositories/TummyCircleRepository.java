package com.wanderingThinker.Tummy.repositories;

import com.wanderingThinker.Tummy.documents.TummyCircle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TummyCircleRepository extends MongoRepository<TummyCircle, String> {
    Optional<TummyCircle> findByUsername(String username);
}
