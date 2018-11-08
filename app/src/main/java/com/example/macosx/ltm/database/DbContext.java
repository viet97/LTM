package com.example.macosx.ltm.database;

import com.example.macosx.ltm.database.models.Post;
import com.example.macosx.ltm.database.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DbContext {
    public static final DbContext instance = new DbContext();
    private ArrayList<User> listFriends;
    private ArrayList<Post> listPosts;
    private User currentUser;
    DbContext(){
            listFriends = new ArrayList<>();
            currentUser = new User();
            listPosts = new ArrayList<>();
    }

    public static DbContext getInstance() {
        return instance;
    }

    public ArrayList<User> getListFriends() {
        return listFriends;
    }

    public void setListFriends(ArrayList<User> listFriends) {
        this.listFriends = listFriends;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Post> getListPosts() {
        return listPosts;
    }

    public void setListPosts(ArrayList<Post> listPosts) {
        this.listPosts = listPosts;
    }
}
