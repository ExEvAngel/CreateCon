package com.angel.createcon.NetworkUtils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angel.createcon.app.AppController;
import com.google.gson.Gson;
import com.stormpath.sdk.Stormpath;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Angel on 10/22/2016.
 */

public class FcmService {
    Context context;
    AppController appController;
    Gson gson;
    public FcmService(Context context) {
        this.context = context;
        appController = AppController.getInstance();
        gson = new Gson();
    }

    public void putToken(String token) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("user")
                .appendPath("fcmtoken");
        Uri uri = builder.build();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("token",token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //showProgressDialog();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, uri.toString(),jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Err.Res.UnsendOp.Unpark", error.toString());
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept","application/json");
                headers.put("Authorization", "Bearer " + Stormpath.accessToken());
                return headers;

            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void postToken(String email, String token) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("user")
                .appendPath("fcmtoken");
        Uri uri = builder.build();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("token",token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //showProgressDialog();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, uri.toString(),jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept","application/json");
                headers.put("Authorization", "Bearer " + Stormpath.accessToken());
                return headers;

            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
