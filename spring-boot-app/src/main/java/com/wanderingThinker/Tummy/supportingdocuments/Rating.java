package com.wanderingThinker.Tummy.supportingdocuments;

public class Rating {
    private String username;
    private Float rating;

    public Rating() {
    }

    public Rating(String username, Float rating) {
        this.username = username;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
