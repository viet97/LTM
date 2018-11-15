package com.example.macosx.ltm.database;

import com.example.macosx.ltm.database.models.Comment;
import com.example.macosx.ltm.database.models.Notification;
import com.example.macosx.ltm.database.models.Post;
import com.example.macosx.ltm.database.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DbContext {
    public static final DbContext instance = new DbContext();
    private ArrayList<User> listFriends;
    private ArrayList<Post> listPosts;
    private ArrayList<Comment> listComments;
    private ArrayList<Notification> listNotifications;
    private ArrayList<Integer> listIsLikes;
    private ArrayList<Post> listNotiPosts;
    private ArrayList<Integer> listNotiIsLike;
    private User currentUser;
    DbContext(){
            listFriends = new ArrayList<>();
            currentUser = new User();
            listPosts = new ArrayList<>();
            listComments = new ArrayList<>();
            listNotifications     = new ArrayList<>();
         listIsLikes     = new ArrayList<>();
         listNotiPosts     = new ArrayList<>();
        listNotiIsLike     = new ArrayList<>();
    }

    public ArrayList<Integer> getListNotiIsLike() {
        return listNotiIsLike;
    }

    public void setListNotiIsLike(ArrayList<Integer> listNotiIsLike) {
        this.listNotiIsLike = listNotiIsLike;
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

    public ArrayList<Notification> getListNotifications() {
        return listNotifications;
    }

    public void setListNotifications(ArrayList<Notification> listNotifications) {
        this.listNotifications = listNotifications;
    }

    public ArrayList<Integer> getListIsLikes() {
        return listIsLikes;
    }

    public void setListIsLikes(ArrayList<Integer> listIsLikes) {
        this.listIsLikes = listIsLikes;
    }

    public ArrayList<Post> getListNotiPosts() {
        return listNotiPosts;
    }

    public void setListNotiPosts(ArrayList<Post> listNotiPosts) {
        this.listNotiPosts = listNotiPosts;
    }

    public ArrayList<Comment> getListComments() {
        return listComments;
    }

    public void setListComments(ArrayList<Comment> listComments) {
        this.listComments = listComments;
    }
}
