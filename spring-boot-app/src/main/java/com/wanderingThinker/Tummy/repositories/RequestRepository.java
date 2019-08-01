package com.wanderingThinker.Tummy.repositories;

import com.wanderingThinker.Tummy.documents.Request;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.function.Predicate;

public interface RequestRepository extends MongoRepository<Request, String> {
    List<Request> findByUsername(Predicate<Request> predicate, Pageable pageable);
}
