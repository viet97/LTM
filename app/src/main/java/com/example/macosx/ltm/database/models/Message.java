package com.example.macosx.ltm.database.models;

public class Message {
    public int id;
    public int id_receive;
    public int id_send;
    public String content;
    public String time;

    public Message(int id, int id_receive, int id_send, String content, String time) {
        this.id = id;
        this.id_receive = id_receive;
        this.id_send = id_send;
        this.content = content;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_receive() {
        return id_receive;
    }

    public void setId_receive(int id_receive) {
        this.id_receive = id_receive;
    }

    public int getId_send() {
        return id_send;
    }

    public void setId_send(int id_send) {
        this.id_send = id_send;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
