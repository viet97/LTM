package com.example.macosx.ltm.network.response;

import com.example.macosx.ltm.database.models.Message;

import java.util.ArrayList;

public class ChatResponse {
    String errorCode;
    String msg;
    ArrayList<Message> messages;

    public ChatResponse(String errorCode, String msg, ArrayList<Message> messages) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.messages = messages;
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

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
