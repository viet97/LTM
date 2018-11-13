package com.example.macosx.ltm.network.response;

import com.example.macosx.ltm.database.models.User;

import java.util.ArrayList;

public class LikeCountResponse {
    String errorCode;
    String msg;
    int like_count;

    public LikeCountResponse(String errorCode, String msg, int like_count) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.like_count = like_count;
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

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }
}
