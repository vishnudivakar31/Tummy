package com.wanderingThinker.Tummy.repositories;

import com.wanderingThinker.Tummy.documents.Notifications;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notifications, String> {
    List<Notifications> findByUsername(String username, Pageable pageable);
}
