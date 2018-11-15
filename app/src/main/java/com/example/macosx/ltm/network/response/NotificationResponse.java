package com.example.macosx.ltm.network.response;

import com.example.macosx.ltm.database.models.Notification;
import com.example.macosx.ltm.database.models.Post;

import java.util.ArrayList;

public class NotificationResponse {
    String errorCode;
    String msg;
    ArrayList<Notification> notification;
    ArrayList<Post> post;
    ArrayList<Integer> isLike;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<Notification> getNotification() {
        return notification;
    }

    public void setNotification(ArrayList<Notification> notification) {
        this.notification = notification;
    }

    public ArrayList<Post> getPost() {
        return post;
    }

    public void setPost(ArrayList<Post> post) {
        this.post = post;
    }

    public ArrayList<Integer> getIsLike() {
        return isLike;
    }

    public void setIsLike(ArrayList<Integer> isLike) {
        this.isLike = isLike;
    }
}

