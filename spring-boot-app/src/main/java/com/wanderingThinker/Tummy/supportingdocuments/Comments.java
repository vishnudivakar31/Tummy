package com.wanderingThinker.Tummy.supportingdocuments;

import java.util.Date;

public class Comments {

    private String username;
    private String comment;
    private Date commentedTime;
    private Integer likes;
    private Integer dislikes;
    private Boolean isAbusive;

    public Comments() {
    }

    public Comments(String username, String comment, Date commentedTime, Integer likes, Integer dislikes, Boolean isAbusive) {
        this.username = username;
        this.comment = comment;
        this.commentedTime = commentedTime;
        this.likes = likes;
        this.dislikes = dislikes;
        this.isAbusive = isAbusive;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Boolean getAbusive() {
        return isAbusive;
    }

    public void setAbusive(Boolean abusive) {
        isAbusive = abusive;
    }
}
