package com.angel.createcon.NetworkUtils;

import android.app.ProgressDialog;
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
import com.angel.createcon.Listeners.GetAllConsListener;
import com.angel.createcon.Listeners.GetTrackingListener;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.Tracking.TrackAdapter;
import com.angel.createcon.app.AppController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stormpath.sdk.Stormpath;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Angel on 10/20/2016.
 */

public class TrackingUtil {
    Gson gson;
    Type listType;

    AppController appController;
    TrackAdapter adapter;
    Context context;
    GetTrackingListener getTrackingListener;

    public TrackingUtil(Context context) {
        this.context = context;
        appController = AppController.getInstance();
        gson = new Gson();
    }

    public void getTracking( Consignment con, final GetTrackingListener callBack) {
        Uri.Builder builder = new Uri.Builder();
        Log.d("TRACK.URI", String.valueOf(con.getConid()));
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons")
                .appendPath(String.valueOf(con.getId()))
                .appendPath("tracking");
        Uri uri = builder.build();
        Log.d("TRACK.URI", uri.toString());
        //showProgressDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, uri.toString(), null,
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
    public void getTracking( int con, final GetTrackingListener callBack) {
        Uri.Builder builder = new Uri.Builder();
        Log.d("TRACK.URI", String.valueOf(con));
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons")
                .appendPath("num")
                .appendPath(String.valueOf(con))
                .appendPath("tracking");
        Uri uri = builder.build();
        Log.d("TRACK.URI", uri.toString());
        //showProgressDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, uri.toString(), null,
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

    public void updateTracking(Tracking tracking) {
        Uri.Builder builder = new Uri.Builder();
        Log.d("TRACK.URI", String.valueOf(tracking.getCid()));
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons")
                .appendPath(String.valueOf(tracking.getCid()))
                .appendPath("tracking");
        Uri uri = builder.build();
        Log.d("TRACK.URI", uri.toString());

        String jsonString = gson.toJson(tracking);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //showProgressDialog();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, uri.toString(), jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

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
        appController.addToRequestQueue(jsonObjectRequest);
    }
}
