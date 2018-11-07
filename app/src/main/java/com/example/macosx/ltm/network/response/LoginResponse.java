package com.example.macosx.ltm.network.response;

import com.example.macosx.ltm.database.models.User;

public class LoginResponse {
    String errorCode;
    String msg;
    User user;

    public LoginResponse(String errorCode, String msg, User user) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
