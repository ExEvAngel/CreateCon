package com.angel.createcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.angel.createcon.app.GetAllConsListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetConsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ConsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Consignment> arrayList = new ArrayList<>();
    TextView txtView;
    BackgroundTask backgroundTask;
    Type listType;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cons);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        gson = new Gson();
        listType = new TypeToken<List<Consignment>>(){}.getType();

        BackgroundTask backgroundTask = new BackgroundTask(GetConsActivity.this);
        backgroundTask.getAllCons(new GetAllConsListener() {
            @Override
            public void onSuccess(String response) {
                arrayList = gson.fromJson(response,listType);
                parseResponse(response);
                //Toast.makeText(GetConsActivity.this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();

            }
        });

        Toast.makeText(GetConsActivity.this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();

        adapter = new ConsAdapter(arrayList);

        recyclerView.setAdapter(adapter);
        //Toast.makeText(this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();



    }

    public void parseResponse(String response){
        arrayList = gson.fromJson(response,listType);
    }

    private void  getAllCons() {
        String tag_json_obj = "json_obj_req";
        //showProgressDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseResponse(response);
                        //hideProgressDialog();
                        Toast.makeText(context, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();
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

        //MyVolleySingleton.getmInstance(context).addToRequestQueue(jsonArrayRequest);

        appController.addToRequestQueue(jsonArrayRequest);
    }

}
