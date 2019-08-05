package com.wanderingThinker.Tummy.controllers;

import com.wanderingThinker.Tummy.documents.Request;
import com.wanderingThinker.Tummy.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tummy/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping()
    public List<Request> findAllRequests(Principal principal) {
        return requestService.findAllRequests(principal.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findRequest(Principal principal, @PathVariable String id) {
        try {
            Request request = requestService.findRequestById(principal.getName(), id);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch(Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> postRequest(Principal principal, @RequestBody Request request) {
        try {
            request.setUsername(principal.getName());
            requestService.postRequest(request);
            return new ResponseEntity<>(request, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> updateRequest(@RequestBody Request request) {
        try {
            requestService.updateRequest(request);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteRequest(@RequestBody Request request) {
        try {
            requestService.closeRequest(request);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
