package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Notifications;
import com.wanderingThinker.Tummy.repositories.NotificationRepository;
import com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.NotificationStatus;
import com.wanderingThinker.Tummy.supportingdocuments.TummyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.NotificationStatus.REQUESTED;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

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
            //Create for actions for creating circles
        }
        throw new TummyException("notification is not present");
    }

}
