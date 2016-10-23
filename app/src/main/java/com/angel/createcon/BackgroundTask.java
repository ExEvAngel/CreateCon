package com.angel.createcon;

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
import com.angel.createcon.app.AppController;
import com.angel.createcon.Listeners.GetAllConsListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stormpath.sdk.Stormpath;
import com.stormpath.sdk.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;

public class BackgroundTask{
    String json_url = "http://ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000/api/cons";
    Gson gson;
    Type listType;

    ArrayList<Consignment>  arrayList;
    AppController appController;
    ConsAdapter adapter;
    Context context;
    GetAllConsListener getAllConsListener;
    ProgressDialog pDialog;

    public BackgroundTask(Context context){
        this.context = context;
        listType = new TypeToken<List<Consignment>>(){}.getType();
        appController = AppController.getInstance();
        gson = new Gson();

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
    }

    public void  getAllCons(final GetAllConsListener callBack) {
        String tag_json_obj = "json_obj_req";
        //showProgressDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url,null,
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

    private void showProgressDialog() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }


    public void createCon(Consignment consignment){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons");
        Uri uri = builder.build();
        String json = gson.toJson(consignment);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, uri.toString(),jsonObject,
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
                        // error
                        //Log.d("Error.Response", error);
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
        Log.d("ACCESSTOKEN", Stormpath.accessToken());
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    public void getCon(int id,final GetAllConsListener callBack){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons")
                .appendPath(String.valueOf(id));
        Uri uri = builder.build();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, uri.toString(),null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        callBack.onSuccess(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //Log.d("Error.Response", error);
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
        Log.d("ACCESSTOKEN", Stormpath.accessToken());
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    public void getParkedCons(final GetAllConsListener callBack) {
        String tag_json_obj = "json_obj_req";
        //showProgressDialog();

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons")
                .appendPath("parked");
        Uri uri = builder.build();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, uri.toString(),null,
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
}
