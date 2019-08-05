package com.wanderingThinker.Tummy.controllers;

import com.wanderingThinker.Tummy.services.TummyCircleService;
import com.wanderingThinker.Tummy.supportingdocuments.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/tummy/friends")
public class CircleController {

    @Autowired
    private TummyCircleService tummyCircleService;

    @GetMapping
    public List<Friend> findAllFriends(Principal principal) {
        return tummyCircleService.findAllFriends(principal.getName());
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findFriendByUsername(Principal principal, @RequestParam("friend-name") String friendName) {
        Map<String, String> msg = new HashMap<>();
        try {
            Friend friend = tummyCircleService.findFriendByUsername(principal.getName(), friendName);
            return new ResponseEntity<>(friend, HttpStatus.OK);
        } catch (Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unfriend")
    public ResponseEntity<Object> unFriend(Principal principal, @RequestParam("friend-name") String friendName) {
        Map<String, String> msg = new HashMap<>();
        try {
            List<Friend> friends = tummyCircleService.unfriend(principal.getName(), friendName);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        } catch (Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/block")
    public ResponseEntity<Object> block(Principal principal, @RequestParam("friend-name") String friendName) {
        Map<String, String> msg = new HashMap<>();
        try {
            Boolean status = tummyCircleService.setBlock(principal.getName(), friendName, true);
            msg.put("msg", "blocked");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unblock")
    public ResponseEntity<Object> unblock(Principal principal, @RequestParam("friend-name") String friendName) {
        Map<String, String> msg = new HashMap<>();
        try {
            Boolean status = tummyCircleService.setBlock(principal.getName(), friendName, false);
            msg.put("msg", "un-blocked");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
