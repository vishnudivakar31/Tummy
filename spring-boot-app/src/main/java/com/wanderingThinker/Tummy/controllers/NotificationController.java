package com.wanderingThinker.Tummy.controllers;

import com.wanderingThinker.Tummy.documents.Notifications;
import com.wanderingThinker.Tummy.services.NotificationService;
import com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.NotificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tummy/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping()
    public List<Notifications> getAllNotifications(Principal principal) {
        return notificationService.getAllNotifications(principal.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNotification(Principal principal, @PathVariable String id) {
        try {
            Notifications notifications = notificationService.getNotificationById(id);
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } catch(Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> postNotification(Principal principal, @RequestBody Notifications notifications) {
        Map<String, String> msg = new HashMap<>();
        try {
            notifications.setUsername(principal.getName());
            notificationService.saveNotification(notifications);
            msg.put("msg", "notification saved");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> updateNotification(Principal principal, @RequestBody Notifications notifications) {
        Map<String, String> msg = new HashMap<>();
        try {
            notificationService.saveNotification(notifications);
            msg.put("msg", "notification saved");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/{action}")
    public ResponseEntity<Object> actOnNotification(Principal principal, @PathVariable String id,
                                                    @PathVariable("action") NotificationStatus action) {
        try {
            Notifications notification = notificationService.actionNotification(id, action);
            return new ResponseEntity<>(notification, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
