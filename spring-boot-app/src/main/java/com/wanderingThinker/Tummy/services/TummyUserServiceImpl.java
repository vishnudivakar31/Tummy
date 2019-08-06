package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.TummyUser;
import com.wanderingThinker.Tummy.repositories.TummyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TummyUserServiceImpl implements TummyUserService {

    @Autowired
    private TummyUserRepository tummyUserRepository;

    @Override
    public TummyUser findUserByUsername(String username) {
        return tummyUserRepository.findByUsername(username);
    }

    @Override
    public Page<TummyUser> findAllUsers(Integer page) {
        return tummyUserRepository.findAll(PageRequest.of(page,30, Sort.Direction.ASC, "firstName"));
    }
}
