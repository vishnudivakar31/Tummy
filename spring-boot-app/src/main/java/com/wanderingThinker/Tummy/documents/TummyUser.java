package com.wanderingThinker.Tummy.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import com.wanderingThinker.Tummy.appdatatypes.TummyDatatypes.Roles;
import java.util.List;

@Document(collection = "tummy-user")
public class TummyUser {

    @Id
    private String userId;

    @Indexed(unique = true)
    private String username;

    private String firstName;
    private String lastName;
    private String password;
    private String nationality;
    private List<Roles> roles;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date joinedDate;

    public TummyUser() {
    }

    public TummyUser(String userId, String username, String firstName, String lastName, String password,
                     String nationality, List<Roles> roles, Date dob, Date joinedDate) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.nationality = nationality;
        this.roles = roles;
        this.dob = dob;
        this.joinedDate = joinedDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
