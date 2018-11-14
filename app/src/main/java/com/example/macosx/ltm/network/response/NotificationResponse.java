package com.example.macosx.ltm.network.response;

import com.example.macosx.ltm.database.models.Notification;

import java.util.ArrayList;

public class NotificationResponse {
    String errorCode;
    String msg;
    ArrayList<Notification> notification;

    public NotificationResponse(String errorCode, String msg, ArrayList<Notification> notification) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.notification = notification;
    }

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
}
