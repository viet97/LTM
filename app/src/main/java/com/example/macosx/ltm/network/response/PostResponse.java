package com.example.macosx.ltm.network.response;

import com.example.macosx.ltm.database.models.Post;
import com.example.macosx.ltm.database.models.User;

import java.util.ArrayList;

public class PostResponse {
    String errorCode;
    String msg;
    ArrayList<Post> posts;

    public PostResponse(String errorCode, String msg, ArrayList<Post> posts) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.posts = posts;
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

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
