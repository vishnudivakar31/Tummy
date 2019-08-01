package com.wanderingThinker.Tummy.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.RequestStatus;
import com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.RequestType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "tummy-requests")
public class Request {

    @Id
    private String id;

    private String username;
    private String requestedTo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date requestedTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

    private RequestType requestType;
    private String recipeId;
    private RequestStatus requestStatus;

    public Request() {
    }

    public Request(String id, String username, String requestedTo, Date requestedTime, Date updatedTime,
                   RequestType requestType, String recipeId, RequestStatus requestStatus) {
        this.id = id;
        this.username = username;
        this.requestedTo = requestedTo;
        this.requestedTime = requestedTime;
        this.updatedTime = updatedTime;
        this.requestType = requestType;
        this.recipeId = recipeId;
        this.requestStatus = requestStatus;
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

    public String getRequestedTo() {
        return requestedTo;
    }

    public void setRequestedTo(String requestedTo) {
        this.requestedTo = requestedTo;
    }

    public Date getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(Date requestedTime) {
        this.requestedTime = requestedTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
