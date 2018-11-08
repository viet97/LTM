package com.example.macosx.ltm.database.models;

public class Post {
    private int id;
    private String image_url;
    private String noti_content;
    private String create_time;
    private String content;
    private int like;
    private int comment;
    private int user_id_send;
    private int user_id_receive;

    public Post(int id, String image_url, String noti_content, String create_time, String content, int like, int comment, int user_id_send, int user_id_receive) {
        this.id = id;
        this.image_url = image_url;
        this.noti_content = noti_content;
        this.create_time = create_time;
        this.content = content;
        this.like = like;
        this.comment = comment;
        this.user_id_send = user_id_send;
        this.user_id_receive = user_id_receive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getNoti_content() {
        return noti_content;
    }

    public void setNoti_content(String noti_content) {
        this.noti_content = noti_content;
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

    public int getUser_id_send() {
        return user_id_send;
    }

    public void setUser_id_send(int user_id_send) {
        this.user_id_send = user_id_send;
    }

    public int getUser_id_receive() {
        return user_id_receive;
    }

    public void setUser_id_receive(int user_id_receive) {
        this.user_id_receive = user_id_receive;
    }
}
