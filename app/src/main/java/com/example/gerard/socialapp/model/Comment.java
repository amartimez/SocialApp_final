package com.example.gerard.socialapp.model;

public class Comment {
    public Comment(String comment, String uidFire) {
        this.comment = comment;
        this.uidFire = uidFire;
    }

    public Comment() {
    }

    private String comment;
    public String uidFire;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUidFire() {
        return uidFire;
    }
}

