package com.example.letstalk;

public class post {
    String post_id;
    String status;
    String postauthor;
    String postName;

    public post(String post_id, String status, String postauthor, String postName) {
        this.post_id = post_id;
        this.status = status;
        this.postauthor = postauthor;
        this.postName = postName;
    }

    public post() {

    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPostauthor() {
        return postauthor;
    }

    public void setPostauthor(String postauthor) {
        this.postauthor = postauthor;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}

