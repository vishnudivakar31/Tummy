package com.wanderingThinker.Tummy.supportingdocuments;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Friend {

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date circleDate;

    Boolean isBlocked;

    public Friend() {
    }

    public Friend(String username, Date circleDate, Boolean isBlocked) {
        this.username = username;
        this.circleDate = circleDate;
        this.isBlocked = isBlocked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCircleDate() {
        return circleDate;
    }

    public void setCircleDate(Date circleDate) {
        this.circleDate = circleDate;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}
