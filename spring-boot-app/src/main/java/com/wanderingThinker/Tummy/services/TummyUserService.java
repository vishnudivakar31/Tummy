package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.TummyUser;
import org.springframework.data.domain.Page;

public interface TummyUserService {
    TummyUser findUserByUsername(String username);

    Page<TummyUser> findAllUsers(Integer page);
}
