package com.example.macosx.ltm.database.models;

public class Notification {
    private int id;
    private int user_id;
    private String create_time;
    private String content;

    public Notification(int id, int user_id, String create_time, String content) {
        this.id = id;
        this.user_id = user_id;
        this.create_time = create_time;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
