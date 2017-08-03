package com.example.zulkuf.incommingsmsbroadcastreceiver.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;

/**
 * Created by zulkuf on 3.08.2017.
 */

public class MySingleton {

    private static MySingleton mySingleton;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private MySingleton(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    private static synchronized MySingleton getInstance(Context context){
        if (mySingleton == null){
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }
    private RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return  mRequestQueue;
    }

    public <T> void addToRequestQue(Request<T> request){ mRequestQueue.add(request);}


}
