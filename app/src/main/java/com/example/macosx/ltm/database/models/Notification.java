package com.example.macosx.ltm.database.models;

public class Notification {
    private String imageUrl;
    private String content;
    private String time;

    public Notification(String imageUrl, String content, String time) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
