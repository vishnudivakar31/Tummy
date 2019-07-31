package com.wanderingThinker.Tummy.supportingdocuments;

import java.util.Date;

public class Comments {

    private String username;
    private String comment;
    private Date commentedTime;

    public Comments() {
    }

    public Comments(String username, String comment, Date commentedTime) {
        this.username = username;
        this.comment = comment;
        this.commentedTime = commentedTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentedTime() {
        return commentedTime;
    }

    public void setCommentedTime(Date commentedTime) {
        this.commentedTime = commentedTime;
    }
    
}
