package com.wanderingThinker.Tummy.repositories;

import com.wanderingThinker.Tummy.supportingdocuments.Friend;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendRepository extends MongoRepository<Friend, String> {
}
