package com.angel.createcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.angel.createcon.app.AppController;
import com.angel.createcon.app.GetAllConsListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GetConsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ConsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Consignment> arrayList;
    TextView txtView;
    BackgroundTask backgroundTask;
    Type listType;
    Gson gson;
    String json_url = "http://ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000/api/cons";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_get_cons);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        gson = new Gson();
        listType = new TypeToken<ArrayList<Consignment>>(){}.getType();

        BackgroundTask backgroundTask = new BackgroundTask(GetConsActivity.this);
        backgroundTask.getAllCons(new GetAllConsListener() {
            @Override
            public void onSuccess(String response) {
                ArrayList<Consignment> list = parseResponse(response);
                updateUI(list);
                //Toast.makeText(GetConsActivity.this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();

                Log.d("ONSUCCESS", "ARRAYSIZE: "+arrayList.size());

            }
        });


    }

    public ArrayList<Consignment> parseResponse(String response){
        ArrayList<Consignment> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);

            Log.d("PARSE", "parseResponse: "+jsonArray.length());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int i = 0; i<jsonArray.length();i++){

                Consignment con = null;

                Log.d("PARSE", "parseID: "+jsonObject.getInt("conid"));
                con.setConid(jsonObject.getInt("conid"));

                Log.d("PARSE", "conID: "+con.getConid());
                con.setDescription(jsonObject.getString("description"));

                list.add(con);

            }
            Log.d("PARSED", "parsedResponse: "+list.size());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //arrayList =gson.fromJson(response,new TypeToken<ArrayList<Consignment>>(){}.getType());
        //updateUI(arrayList);
        return list;
    }

    public void updateUI(ArrayList<Consignment> consignments){
        this.arrayList = consignments;
        Log.d("UPDATE", "parseResponse: "+consignments.size());
        if (adapter == null) {
            adapter = new ConsAdapter(consignments,GetConsActivity.this);
            recyclerView.setAdapter(adapter);
        }else{
            adapter.setConsignments(consignments);
            adapter.notifyDataSetChanged();
        }
    }
    private void  getAllCons() {
        String tag_json_obj = "json_obj_req";
        //showProgressDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseResponse(response.toString());
                        //hideProgressDialog();
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
                //Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                //hideProgressDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }



}
