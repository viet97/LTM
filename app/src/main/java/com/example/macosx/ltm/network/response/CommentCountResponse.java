package com.example.macosx.ltm.network.response;

public class CommentCountResponse {
    String errorCode;
    String msg;
    int comment_count;

    public CommentCountResponse(String errorCode, String msg, int comment_count) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.comment_count = comment_count;
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

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }
}
