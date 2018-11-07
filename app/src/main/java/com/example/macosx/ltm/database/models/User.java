package com.example.macosx.ltm.database.models;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String country;
    private String avatarUrl;
    private int status;
    private String lastUpdateTime;

    public User(int id, String username, String password, String name, String country, String avatarUrl, int status, String lastUpdateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.country = country;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.lastUpdateTime = lastUpdateTime;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
