package com.wanderingThinker.Tummy.controllers;

import com.wanderingThinker.Tummy.documents.TummyUser;
import com.wanderingThinker.Tummy.services.TummyCircleService;
import com.wanderingThinker.Tummy.supportingdocuments.Friend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/tummy/friends")
public class CircleController {

    Logger logger = LoggerFactory.getLogger(CircleController.class);

    @Autowired
    private TummyCircleService tummyCircleService;

    @GetMapping
    public List<Friend> findAllFriends(Principal principal) {
        logger.debug("Find all friends started.");
        return tummyCircleService.findAllFriends(principal.getName());
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findFriendByUsername(Principal principal, @RequestParam("friend-name") String friendName) {
        logger.debug("Finding friend by username started.");
        Map<String, String> msg = new HashMap<>();
        try {
            Friend friend = tummyCircleService.findFriendByUsername(principal.getName(), friendName);
            return new ResponseEntity<>(friend, HttpStatus.OK);
        } catch (Exception e) {
            msg.put("msg", e.getMessage());
            logger.error("Exception in CircleController::findFriendByUsername(): " + e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unfriend")
    public ResponseEntity<Object> unFriend(Principal principal, @RequestParam("friend-name") String friendName) {
        logger.debug("un-friend request initialized.");
        Map<String, String> msg = new HashMap<>();
        try {
            List<Friend> friends = tummyCircleService.unfriend(principal.getName(), friendName);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        } catch (Exception e) {
            msg.put("msg", e.getMessage());
            logger.error("Exception in CircleController::unFriend(): " + e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/block")
    public ResponseEntity<Object> block(Principal principal, @RequestParam("friend-name") String friendName) {
        logger.debug("block request initialized.");
        Map<String, String> msg = new HashMap<>();
        try {
            Boolean status = tummyCircleService.setBlock(principal.getName(), friendName, true);
            msg.put("msg", "blocked");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            msg.put("msg", e.getMessage());
            logger.error("Exception in CircleController::block(): " + e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unblock")
    public ResponseEntity<Object> unblock(Principal principal, @RequestParam("friend-name") String friendName) {
        logger.debug("unblock request initialized");
        Map<String, String> msg = new HashMap<>();
        try {
            Boolean status = tummyCircleService.setBlock(principal.getName(), friendName, false);
            msg.put("msg", "un-blocked");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            msg.put("msg", e.getMessage());
            logger.error("Exception in CircleController::unblock(): " + e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/new")
    public ResponseEntity<Object> findUsers(Principal principal, @RequestParam Integer page) {
        Map<String, String> msg = new HashMap<>();
        try {
            List<TummyUser> users = tummyCircleService.findUsers(principal.getName(), page);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            msg.put("msg", e.getMessage());
            logger.error("Exception in CircleController::findUsers(): " + e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
