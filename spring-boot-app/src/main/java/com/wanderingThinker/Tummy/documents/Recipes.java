package com.wanderingThinker.Tummy.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wanderingThinker.Tummy.supportingdocuments.Comments;
import com.wanderingThinker.Tummy.supportingdocuments.Ingrident;
import com.wanderingThinker.Tummy.supportingdocuments.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "tummy-recipes")
public class Recipes {

    @Id
    private String id;

    private String username;
    private String title;
    private Long cookingTime;
    private Integer likes;
    private Integer dislikes;
    private Boolean isAbusive;
    private List<Comments> comments;
    private List<Ingrident> ingridents;
    private Float avgRatings;
    private List<Rating> ratings;
    private List<String> steps;
    private String cuisine;
    private Date posted_date;
    private Date updated_date;

    public Recipes() {
    }

    public Recipes(String id, String username, String title, Long cookingTime, Integer likes, Integer dislikes,
                   Boolean isAbusive, List<Comments> comments, List<Ingrident> ingridents, Float avgRatings,
                   List<Rating> ratings, List<String> steps, String cuisine, Date posted_date, Date updated_date) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.cookingTime = cookingTime;
        this.likes = likes;
        this.dislikes = dislikes;
        this.isAbusive = isAbusive;
        this.comments = comments;
        this.ingridents = ingridents;
        this.avgRatings = avgRatings;
        this.ratings = ratings;
        this.steps = steps;
        this.cuisine = cuisine;
        this.posted_date = posted_date;
        this.updated_date = updated_date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Long cookingTime) {
        this.cookingTime = cookingTime;
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

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Ingrident> getIngridents() {
        return ingridents;
    }

    public void setIngridents(List<Ingrident> ingridents) {
        this.ingridents = ingridents;
    }

    public Float getAvgRatings() {
        return avgRatings;
    }

    public void setAvgRatings(Float avgRatings) {
        this.avgRatings = avgRatings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Date getPosted_date() {
        return posted_date;
    }

    public void setPosted_date(Date posted_date) {
        this.posted_date = posted_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }
}
