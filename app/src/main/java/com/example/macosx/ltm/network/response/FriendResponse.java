package com.example.macosx.ltm.network.response;

import com.example.macosx.ltm.database.models.User;

import java.util.ArrayList;

public class FriendResponse {
    String errorCode;
    String msg;
    ArrayList<User> users;

    public FriendResponse(String errorCode, String msg, ArrayList<User> listFriends) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.users = listFriends;
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

    public ArrayList<User> getListFriends() {
        return users;
    }

    public void setListFriends(ArrayList<User> listFriends) {
        this.users = listFriends;
    }
}