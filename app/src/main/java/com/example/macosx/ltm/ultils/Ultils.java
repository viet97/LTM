package com.example.macosx.ltm.ultils;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.User;

import org.w3c.dom.Text;

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
    public void setImageText(ImageView imageView, char character){
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(character).toUpperCase(), Color.BLUE);
        imageView.setImageDrawable(drawable);
    }
}
