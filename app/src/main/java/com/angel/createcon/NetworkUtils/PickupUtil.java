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
import com.angel.createcon.Consignment;
import com.angel.createcon.Listeners.GetPickupListener;
import com.angel.createcon.Listeners.GetTrackingListener;
import com.angel.createcon.POJO.Pickup;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.Tracking.TrackAdapter;
import com.angel.createcon.app.AppController;
import com.google.gson.Gson;
import com.stormpath.sdk.Stormpath;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Angel on 10/21/2016.
 */

public class PickupUtil {
    Gson gson;
    Type listType;

    AppController appController;
    TrackAdapter adapter;
    Context context;
    GetTrackingListener getTrackingListener;

    public PickupUtil(Context context) {
        this.context = context;
        appController = AppController.getInstance();
        gson = new Gson();
    }

    public void getPickups(String driver,final GetPickupListener callBack) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("pickup")
                .appendPath("driver");
        Uri uri = builder.build();
        StringBuilder url = new StringBuilder();
        url.append(uri.toString());
        url.append("/");
        url.append(driver);
        Log.d("TRACK.URI", uri.toString());
        //showProgressDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url.toString(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callBack.onSuccess(response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                //hideProgressDialog();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept","application/json");
                headers.put("Authorization", "Bearer " + Stormpath.accessToken());
                return headers;

            }
        };
        appController.addToRequestQueue(jsonArrayRequest);
    }
    public void completePickup(Pickup pickup, Tracking tracking) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("pickup")
                .appendPath(String.valueOf(pickup.getCid()))
                .appendPath("driver");
        Uri uri = builder.build();
        StringBuilder url = new StringBuilder();
        url.append(uri.toString());
        url.append("/");
        url.append(pickup.getDriver());
        url.append("/");
        url.append("complete");
        Log.d("TRACK.URI", uri.toString());
        //showProgressDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.PUT, url.toString(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                //hideProgressDialog();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept","application/json");
                headers.put("Authorization", "Bearer " + Stormpath.accessToken());
                return headers;

            }
        };
        appController.addToRequestQueue(jsonArrayRequest);
        TrackingUtil trackingUtil  = new TrackingUtil(context);
        trackingUtil.updateTracking(tracking);
    }
    public void rejectPickup(Pickup pickup) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("pickup")
                .appendPath(String.valueOf(pickup.getCid()))
                .appendPath("driver");
        Uri uri = builder.build();
        StringBuilder url = new StringBuilder();
        url.append(uri.toString());
        url.append("/");
        url.append(pickup.getDriver());
        url.append("/");
        url.append("reject");
        Log.d("TRACK.URI", uri.toString());
        //showProgressDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.PUT, url.toString(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                //hideProgressDialog();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept","application/json");
                headers.put("Authorization", "Bearer " + Stormpath.accessToken());
                return headers;

            }
        };
        appController.addToRequestQueue(jsonArrayRequest);
    }


}

