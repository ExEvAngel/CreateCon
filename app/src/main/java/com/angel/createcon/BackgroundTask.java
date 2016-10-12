package com.angel.createcon;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angel.createcon.app.AppController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BackgroundTask {
    Context context;
    ArrayList<Consignment> arrayList = new ArrayList<>();
    String json_url = "http://ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000/api/cons";
    Gson gson = new Gson();
    Type listType = new TypeToken<List<Consignment>>(){}.getType();
    ProgressDialog pDialog;
    VolleyCallback callBack;

    public BackgroundTask(Context context){
        this.context = context;

    }

    public interface VolleyCallback{
        void onSuccess(ArrayList<Consignment>con);
    }

    public void getAllCons(final VolleyCallback callback){
        String tag_json_obj = "json_obj_req";

        showProgressDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrayList = gson.fromJson(response.toString(),listType);
                        callback.onSuccess(arrayList);
                        hideProgressDialog();
                        //Toast.makeText(context, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();
                        //((TextView)((Activity)context).getWindow().getDecorView().findViewById(R.id.txt_my_con)).setText(arrayList.get(0).getSendname());
                        /*int count = 0;
                            while(count<response.length()){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(count);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Consignment con;


                                arrayList.add(con);
                                count++;
                            }*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                hideProgressDialog();
            }
        });

        //MyVolleySingleton.getmInstance(context).addToRequestQueue(jsonArrayRequest);

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

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
        String url = "http://ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000/api/cons";
        String json = gson.toJson(consignment);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,jsonObject,
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
        );
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
