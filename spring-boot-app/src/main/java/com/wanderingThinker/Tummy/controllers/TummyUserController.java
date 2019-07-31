package com.wanderingThinker.Tummy.controllers;

import com.wanderingThinker.Tummy.documents.TummyUser;
import com.wanderingThinker.Tummy.repositories.TummyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class TummyUserController {

    private TummyUserRepository tummyUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final String MESSAGE = "message";

    @Autowired
    public TummyUserController(TummyUserRepository tummyUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tummyUserRepository = tummyUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody TummyUser user) {
        Map<String, String> msg = new HashMap<>();
        if(tummyUserRepository.findByUsername(user.getUsername()) != null) {
            msg.put(MESSAGE, "username already exists.");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setJoinedDate(new Date(System.currentTimeMillis()));
        tummyUserRepository.save(user);
        msg.put(MESSAGE, "Welcome to Tummy.");
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
