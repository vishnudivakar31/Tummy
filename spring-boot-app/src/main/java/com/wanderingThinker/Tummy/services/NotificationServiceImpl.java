package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Notifications;
import com.wanderingThinker.Tummy.documents.Request;
import com.wanderingThinker.Tummy.documents.TummyCircle;
import com.wanderingThinker.Tummy.repositories.NotificationRepository;
import com.wanderingThinker.Tummy.supportingdocuments.Friend;
import com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.NotificationStatus;
import com.wanderingThinker.Tummy.supportingdocuments.TummyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.NotificationStatus.ACCEPTED;
import static com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.NotificationStatus.REQUESTED;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TummyCircleService tummyCircleService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RequestService requestService;

    @Override
    public void notifyUsers(Notifications notifications) {
        if(notifications.getNotifiedTime() == null) {
            notifications.setNotifiedTime(new Date(System.currentTimeMillis()));
        }
        notifications.setUpdatedTime(new Date(System.currentTimeMillis()));
        notificationRepository.save(notifications);
    }

    @Override
    public List<Notifications> getAllNotifications(String username) {
        List<Notifications> notifications = notificationRepository.findByUsername(username,
                PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "updatedTime"));
        return notifications.stream()
                .filter(i -> i.getNoticationStatus().equals(REQUESTED))
                .collect(Collectors.toList());
    }

    @Override
    public Notifications getNotificationById(String id) throws TummyException {
        Optional<Notifications> optionalNotifications = notificationRepository.findById(id);
        if(optionalNotifications.isPresent()) {
            return optionalNotifications.get();
        }
        throw new TummyException("notification is not present");
    }

    @Override
    public void saveNotification(Notifications notifications) {
        notifyUsers(notifications);
    }

    @Override
    public Notifications actionNotification(String id, NotificationStatus notificationStatus) throws TummyException {
        Optional<Notifications> optionalNotifications = notificationRepository.findById(id);
        if(optionalNotifications.isPresent()) {
            Notifications notification = optionalNotifications.get();
            notification.setNoticationStatus(notificationStatus);
            notifyUsers(notification);
            actAndCloseNotification(notification);
            notification = notificationRepository.save(notification);
            Request request = requestService.findRequestById(notification.getNotifiedBy(), id);
            requestService.closeRequest(request);
            return notification;
        }
        throw new TummyException("notification is not present");
    }

    private void actAndCloseNotification(Notifications notification) throws TummyException {
        //Various notification actions
        switch (notification.getNotificationType()) {
            case CIRCLE_ADD: addFriendsInCircle(notification);
                break;
            case RECIPE_ABSUVIE: reportAbusiveRecipe(notification);
                break;
            case RECIPE_CLEAR_ABUSE: clearRecipeAbuse(notification);
                break;
            default: throw new TummyException("invalid notification type");
        }
    }

    private void clearRecipeAbuse(Notifications notification) throws TummyException {
        recipeService.clearAbuse(notification.getUsername(), notification.getRecipeId());
    }

    private void reportAbusiveRecipe(Notifications notification) throws TummyException {
        recipeService.reportAbuse(notification.getNotifiedBy(), notification.getRecipeId());
    }

    private void addFriendsInCircle(Notifications notification) {
        if(notification.getNoticationStatus().equals(ACCEPTED)) {
            Optional<TummyCircle> optionalPrimaryUser = tummyCircleService.findByUsername(notification.getUsername());
            Optional<TummyCircle> optionalSecondaryUser = tummyCircleService.findByUsername(notification.getNotifiedBy());

            TummyCircle primaryUser = optionalPrimaryUser.isPresent() ? optionalPrimaryUser.get() : new TummyCircle(notification.getUsername());
            TummyCircle secondaryUser = optionalSecondaryUser.isPresent() ? optionalSecondaryUser.get() : new TummyCircle(notification.getNotifiedBy());

            if(primaryUser.getFriends() == null) {
                primaryUser.setFriends(new ArrayList<>());
            }

            if(secondaryUser.getFriends() == null) {
                secondaryUser.setFriends(new ArrayList<>());
            }

            Friend primaryFriend = new Friend(notification.getNotifiedBy(), new Date(System.currentTimeMillis()), false);
            Friend secondaryFriend = new Friend(notification.getUsername(), new Date(System.currentTimeMillis()), false);
            primaryUser.getFriends().add(primaryFriend);
            secondaryUser.getFriends().add(secondaryFriend);
            tummyCircleService.saveCircle(primaryUser);
            tummyCircleService.saveCircle(secondaryUser);
        }
    }

}
