package com.wanderingThinker.Tummy.documents;

import com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.NotificationStatus;
import com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.RequestType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "tummy-notifications")
public class Notifications {

    @Id
    private String id;

    private String username;
    private String notifiedBy;
    private Date notifiedTime;
    private Date updatedTime;
    private RequestType notificationType;
    private NotificationStatus noticationStatus;
    private String message;
    private String recipeId;

    public Notifications() {
    }

    public Notifications(String id, String username, String notifiedBy, Date notifiedTime, Date updatedTime,
                         RequestType notificationType, NotificationStatus noticationStatus, String message, String recipeId) {
        this.id = id;
        this.username = username;
        this.notifiedBy = notifiedBy;
        this.notifiedTime = notifiedTime;
        this.updatedTime = updatedTime;
        this.notificationType = notificationType;
        this.noticationStatus = noticationStatus;
        this.message = message;
        this.recipeId = recipeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotifiedBy() {
        return notifiedBy;
    }

    public void setNotifiedBy(String notifiedBy) {
        this.notifiedBy = notifiedBy;
    }

    public Date getNotifiedTime() {
        return notifiedTime;
    }

    public void setNotifiedTime(Date notifiedTime) {
        this.notifiedTime = notifiedTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public RequestType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(RequestType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationStatus getNoticationStatus() {
        return noticationStatus;
    }

    public void setNoticationStatus(NotificationStatus noticationStatus) {
        this.noticationStatus = noticationStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

}
