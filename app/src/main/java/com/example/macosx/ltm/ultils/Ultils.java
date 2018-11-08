package com.example.macosx.ltm.ultils;

import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.User;

public class Ultils {
    public static final Ultils instance = new Ultils();
    public String getNameOfPost(int id){
        if (DbContext.getInstance().getCurrentUser().getId() == id){
            return DbContext.getInstance().getCurrentUser().getName();
        }
        for (User user : DbContext.getInstance().getListFriends() ) {
            if (user.getId() == id){
                return user.getName();
            }
        }
        return "";
    }
}
