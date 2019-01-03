package com.houarizegai.gestioncommercial;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySignleton {
    private static MySignleton mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private MySignleton(Context context) {
        mContext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySignleton getmInstance(Context context) {
        if(mInstance == null) {
            mInstance = new MySignleton(context);
        }
        return mInstance;
    }

    public <T>void addToRequestQueue(Request<T> request) {
        requestQueue.add(request);
    }
}
