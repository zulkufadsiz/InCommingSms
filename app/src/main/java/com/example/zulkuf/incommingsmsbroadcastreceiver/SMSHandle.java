package com.example.zulkuf.incommingsmsbroadcastreceiver;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by zulkuf on 3.08.2017.
 */

public class SMSHandle extends Activity  {

    public static String getGetSms(Context context) {
        //Create Inbox uri
        Uri inboxUri = Uri.parse("content://sms/inbox");

        //List required coloumns
        String[] reqColoumns  = {"_id","address","body"};

        //Get content resolver object which will deal with content;
        //Provider

        ContentResolver contentResolver = context.getContentResolver() ;

        //Fetch inbox sms message, from Built-in Content provider
        Cursor cursor = contentResolver.query(inboxUri,reqColoumns,null,null,null);
        String inboxSms = "";
        if (cursor != null){
            while(cursor.moveToNext()){
                inboxSms += cursor.getString(0);
                inboxSms += "#";
                inboxSms += cursor.getString(1);
                inboxSms += "#";
                inboxSms += cursor.getString(2);
                inboxSms += "~";
            }
        }
       // Log.d("WESTEROS",inboxSms);
        return  inboxSms;
    }

    private String getSms;


}
