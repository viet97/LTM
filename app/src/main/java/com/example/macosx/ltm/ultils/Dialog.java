package com.example.macosx.ltm.ultils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

public class Dialog {
    AlertDialog alertDialog ;
    public static final Dialog instance = new Dialog();
    Dialog(){

    }

    public void showMessageDialog(Context context,String title,String message){
        alertDialog =  new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }



}
