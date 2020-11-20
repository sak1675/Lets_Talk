package com.example.letstalk;

public class user {
    String id;
    String Username;
    String imageUrl;
    String description;

    public user(String id, String username, String imageUrl, String description) {
        this.id = id;
        Username = username;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public user(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
