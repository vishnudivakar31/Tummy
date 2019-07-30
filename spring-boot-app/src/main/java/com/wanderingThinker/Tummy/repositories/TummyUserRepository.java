package com.wanderingThinker.Tummy.repositories;

import com.wanderingThinker.Tummy.documents.TummyUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TummyUserRepository extends MongoRepository<TummyUser, String> {
    TummyUser findByUsername(String username);
}
