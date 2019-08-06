package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.TummyUser;
import com.wanderingThinker.Tummy.repositories.TummyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TummyUserServiceImpl implements TummyUserService {

    @Autowired
    private TummyUserRepository tummyUserRepository;

    @Override
    public TummyUser findUserByUsername(String username) {
        return tummyUserRepository.findByUsername(username);
    }
}
