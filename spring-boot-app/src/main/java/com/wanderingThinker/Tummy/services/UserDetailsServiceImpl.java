package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.TummyUser;
import com.wanderingThinker.Tummy.repositories.TummyUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private TummyUserRepository tummyUserRepository;

    public UserDetailsServiceImpl(TummyUserRepository tummyUserRepository) {
        this.tummyUserRepository = tummyUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TummyUser user = tummyUserRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
