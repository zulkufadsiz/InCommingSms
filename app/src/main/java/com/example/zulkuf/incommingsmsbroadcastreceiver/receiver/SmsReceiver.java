package com.example.zulkuf.incommingsmsbroadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.zulkuf.incommingsmsbroadcastreceiver.helper.ConnectionManager;

/**
 * Created by zulkuf on 3.08.2017.
 */

public class SmsReceiver extends BroadcastReceiver {

    private static final String SMS_SERVICE = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_SERVICE)){
            boolean isConnected = ConnectionManager.isConnectedToInternet(context);
            if (isConnected){
                Toast.makeText(context,"Mesaj geldi telefona bak.",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(context,"İnternet yok ama mesajlar.",Toast.LENGTH_SHORT).show();
            }


        }
    }
}
