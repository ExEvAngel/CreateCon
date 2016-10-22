package com.angel.createcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.angel.createcon.Decoration.SimpleDividerItemDecoration;
import com.angel.createcon.Listeners.ConsignmentLoadListener;
import com.angel.createcon.app.AppController;
import com.angel.createcon.Listeners.GetAllConsListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GetConsActivity extends AppCompatActivity {

    //ConsAdapter adapter for diplaying cons in RecyclerView;
    ConsAdapter adapter;
    //RecyclerView containing showing all cons
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<Consignment> arrayList = new ArrayList<>();
    TextView txtView;
    BackgroundTask backgroundTask;
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
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));


        gson = new Gson();
        BackgroundTask backgroundTask = new BackgroundTask(GetConsActivity.this);
        if(getIntent().hasExtra("PARKCONS")){
            backgroundTask.getParkedCons(new GetAllConsListener() {
                @Override
                public void onSuccess(String response) {

                    Log.d("onSuccess", "Response: " + response);
                    ArrayList<Consignment> list = parseResponse(response);
                    updateUI(list);
                    //Toast.makeText(GetConsActivity.this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();
                    Log.d("ONSUCCESS", "ARRAYSIZE: " + arrayList.size());

                }
            });
        }else {
            backgroundTask.getAllCons(new GetAllConsListener() {
                @Override
                public void onSuccess(String response) {

                    Log.d("onSuccess", "Response: " + response);
                    ArrayList<Consignment> list = parseResponse(response);
                    updateUI(list);
                    //Toast.makeText(GetConsActivity.this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();
                    Log.d("ONSUCCESS", "ARRAYSIZE: " + arrayList.size());

                }
            });
        }

        if(adapter==null){
            adapter = new ConsAdapter(arrayList,GetConsActivity.this);
            recyclerView.setAdapter(adapter);
        }




    }


    public ArrayList<Consignment> parseResponse(String response){
        return gson.fromJson(response,new TypeToken<ArrayList<Consignment>>(){}.getType());
        }

    public void updateUI(ArrayList<Consignment> consignments){
        this.arrayList = consignments;

        String jsonArray = gson.toJson(consignments,new TypeToken<ArrayList<Consignment>>(){}.getType());
        Log.d("UPDATE", "back2json: "+jsonArray);
        Log.d("UPDATE", "parseResponse: "+consignments.size());
        if (adapter==null) {
            adapter = new ConsAdapter(consignments,GetConsActivity.this);
            recyclerView.setAdapter(adapter);
        }else{
            adapter.setConsignments(consignments);
            adapter.notifyDataSetChanged();
        }

        Log.d("UPDATE", "localArrayList: "+arrayList.size());
    }

}
