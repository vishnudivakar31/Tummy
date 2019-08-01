package com.wanderingThinker.Tummy.documents;

import com.wanderingThinker.Tummy.supportingdocuments.Friend;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tummy-circle")
public class TummyCircle {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private List<Friend> friends;

    public TummyCircle() {
    }

    public TummyCircle(String id, String username, List<Friend> friends) {
        this.id = id;
        this.username = username;
        this.friends = friends;
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

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
