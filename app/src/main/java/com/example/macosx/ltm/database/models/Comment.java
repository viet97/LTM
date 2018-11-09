package com.example.macosx.ltm.database.models;

public class Comment {
    private int id;
    private int user_comment_id;
    private String content;
    private String create_time;
    private String noti_content;

    public Comment(int id, int user_comment_id, String content, String create_time, String noti_content) {
        this.id = id;
        this.user_comment_id = user_comment_id;
        this.content = content;
        this.create_time = create_time;
        this.noti_content = noti_content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_comment_id() {
        return user_comment_id;
    }

    public void setUser_comment_id(int user_comment_id) {
        this.user_comment_id = user_comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getNoti_content() {
        return noti_content;
    }

    public void setNoti_content(String noti_content) {
        this.noti_content = noti_content;
    }
}
