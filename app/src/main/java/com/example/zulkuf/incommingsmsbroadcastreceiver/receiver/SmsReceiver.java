package com.example.zulkuf.incommingsmsbroadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.zulkuf.incommingsmsbroadcastreceiver.SMSHandle;
import com.example.zulkuf.incommingsmsbroadcastreceiver.data.MySingleton;
import com.example.zulkuf.incommingsmsbroadcastreceiver.helper.ConnectionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zulkuf on 3.08.2017.
 */

public class SmsReceiver extends BroadcastReceiver {

    private static final String SMS_SERVICE = "android.provider.Telephony.SMS_RECEIVED";
    boolean isFirstTime = true;

    String server_url = "http://kosanlar.com.tr/postandroid.php";


    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(SMS_SERVICE)){
            boolean isConnected = ConnectionManager.isConnectedToInternet(context);
            if (isConnected){
                if (isFirstTime == true){
                    SMSHandle sms = new SMSHandle();
                    //String getSms =  sms.getGetSms(context);
                    //Log.d("JON",getSms);

                   // final String inbox = getSms;
                    //Log.e("TOMATO",content);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context,"Error..."+ error.toString() ,Toast.LENGTH_SHORT).show();
                        }

                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params =  new HashMap<>();
                            //params.put("inbox",inbox);

                            return params;
                        }
                    };

                    MySingleton.getInstance(context).addToRequestQue(stringRequest);
                }else{
                    Toast.makeText(context,"İlk kez kullanılmıyor.",Toast.LENGTH_SHORT).show();
                }


            }else{
                Toast.makeText(context,"İnternet yok ama mesajlar.",Toast.LENGTH_SHORT).show();
            }


        }
    }
}
