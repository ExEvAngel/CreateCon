package com.angel.createcon.NetworkUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angel.createcon.ConsAdapter;
import com.angel.createcon.Consignment;
import com.angel.createcon.Listeners.GetAllConsListener;
import com.angel.createcon.POJO.Pickup;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.app.AppController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stormpath.sdk.Stormpath;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Angel on 10/18/2016.
 */

public class UnsenableOperations{
    String park_url = "http://ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000/api/cons";
    Gson gson;
    Type listType;
    ProgressDialog pDialog;

    ArrayList<Consignment> arrayList;
    AppController appController;
    ConsAdapter adapter;
    Context context;
    GetAllConsListener getAllConsListener;

    public UnsenableOperations(Context context){
        this.context = context;
        listType = new TypeToken<List<Consignment>>(){}.getType();
        appController = AppController.getInstance();
        gson = new Gson();

    }

    public void parkCon(Consignment consignment, Tracking tracking){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons")
                .appendPath(String.valueOf(consignment.getId()))
                .appendPath("park");
        Uri uri = builder.build();

        String json = gson.toJson(consignment);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            jsonObject.put("cid", consignment.getId());
            jsonObject.put("conid",consignment.getConid());
            jsonObject.put("userid",consignment.getUserid());
            jsonObject.put("remarks",tracking.getRemarks());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("park.jsonob", jsonObject.toString());
        Log.d("PARKCON.URI", uri.toString());
        //Log.d("PARKCON.JSONOBJ", parkDetails.toString());
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
                        Log.d("Err.Res.UnsendOp.Park", error.toString());
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


        TrackingUtil trackingUtil  = new TrackingUtil(context);
        trackingUtil.updateTracking(tracking);
    }

    public void unParkCon(Consignment consignment, Tracking tracking){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons")
                .appendPath(String.valueOf(consignment.getId()))
                .appendPath("unpark");
        Uri uri = builder.build();
        Log.d("UNPARK.URI", uri.toString());
        String json = gson.toJson(consignment);
        Log.d("unparkcon.jsonob", json.toString());
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            jsonObject.put("cid", consignment.getId());
            jsonObject.put("conid",consignment.getConid());
            jsonObject.put("userid",consignment.getUserid());
            jsonObject.put("remarks",tracking.getRemarks());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        TrackingUtil trackingUtil  = new TrackingUtil(context);
        trackingUtil.updateTracking(tracking);
    }
    public void parkPickupCon(Pickup pickup, Tracking tracking){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons")
                .appendPath(String.valueOf(pickup.getCid()))
                .appendPath("park");
        Uri uri = builder.build();
        String json = gson.toJson(pickup);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            jsonObject.put("cid", pickup.getCid());
            jsonObject.put("conid",pickup.getConid());
            jsonObject.put("userid", tracking.getUserId());
            jsonObject.put("remarks", tracking.getRemarks());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("park.jsonob", jsonObject.toString());
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
        TrackingUtil trackingUtil  = new TrackingUtil(context);
        trackingUtil.updateTracking(tracking);
    }
}
