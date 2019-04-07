package com.example.macosx.ltm.database.models;

public class Message {
    public int id;
    public int type;
    public String content;
    public String name;
    public String time;

    public Message(int id, int type, String content, String name, String time) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.name = name;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
