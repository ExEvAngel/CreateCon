package com.angel.createcon;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Angel on 10/12/2016.
 */

public class MyVolleySingleton {
    private static MyVolleySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MyVolleySingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized  MyVolleySingleton getmInstance(Context context){
        if(mInstance==null){
            mInstance = new MyVolleySingleton(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
