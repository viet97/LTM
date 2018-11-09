package com.example.macosx.ltm.network.response;

import com.example.macosx.ltm.database.models.Comment;
import com.example.macosx.ltm.database.models.User;

import java.util.ArrayList;

public class CommentResponse {
    String errorCode;
    String msg;
    ArrayList<Comment> comments;

    public CommentResponse(String errorCode, String msg, ArrayList<Comment> comments) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.comments = comments;
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
