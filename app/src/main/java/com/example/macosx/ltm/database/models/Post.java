package com.example.macosx.ltm.database.models;

public class Post {
    private int postID;
    private String avatar;
    private String name;
    private String time;
    private String content;
    private int like;
    private int comment;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public Post(int postID, String avatar, String name, String time, String content, int like, int comment) {
        this.postID = postID;
        this.avatar = avatar;
        this.name = name;
        this.time = time;
        this.content = content;
        this.like = like;
        this.comment = comment;
    }
}
