package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Notifications;
import com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes;
import com.wanderingThinker.Tummy.supportingdocuments.TummyException;

import java.util.List;

public interface NotificationService {
    void notifyUsers(Notifications notifications);
    List<Notifications> getAllNotifications(String username);
    Notifications getNotificationById(String id) throws TummyException;
    void saveNotification(Notifications notifications);
    Notifications actionNotification(String id, TummyDatatypes.NotificationStatus notificationStatus) throws TummyException;
}
