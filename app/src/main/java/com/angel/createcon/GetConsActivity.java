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
        registerForContextMenu(recyclerView);

        gson = new Gson();
        /*
        new TaskLoadConsignments(GetConsActivity.this).execute();
        if (adapter==null) {
            adapter = new ConsAdapter(arrayList,GetConsActivity.this);
            recyclerView.setAdapter(adapter);
        }*/

        BackgroundTask backgroundTask = new BackgroundTask(GetConsActivity.this);
        backgroundTask.getAllCons(new GetAllConsListener() {
            @Override
            public void onSuccess(String response) {

                Log.d("onSuccess", "Response: "+response);
                ArrayList<Consignment> list = parseResponse(response);
                updateUI(list);
                //Toast.makeText(GetConsActivity.this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();

                Log.d("ONSUCCESS", "ARRAYSIZE: "+arrayList.size());

            }
        });

        if(adapter==null){
            adapter = new ConsAdapter(arrayList,GetConsActivity.this);
            recyclerView.setAdapter(adapter);
        }




    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_cons_contextual_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //ContextMenuRecyclerView.RecyclerViewContextMenuInfo info = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Toast.makeText(this, " User selected something ", Toast.LENGTH_LONG).show();
        return super.onContextItemSelected(item);
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();

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
    }*/

    public ArrayList<Consignment> parseResponse(String response){
        /*
        ArrayList<Consignment> list = new ArrayList<>();
        try {
            Consignment con = null;
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObjectCon = jsonArray.getJSONObject(i);
                con = gson.fromJson(jsonObjectCon.toString(), Consignment.class);
                Log.d("PARSE", "jsonObjectConString: "+jsonObjectCon.toString());

            /* Log.d("PARSE", "parseResponse: "+jsonArray.length());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int i = 0; i<jsonArray.length();i++){

                Consignment con = null;

                Log.d("PARSE", "parseID: "+jsonObject.getInt("conid"));
                con.setConid(jsonObject.getInt("conid"));

                Log.d("PARSE", "conID: "+con.getConid());
                con.setDescription(jsonObject.getString("description"));

                list.add(con);
                this.arrayList = list;
                Log.d("PARSED", "localarrayList: "+list.size());
            }
            Log.d("PARSED", "parsedResponse: "+list.size());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //arrayList =gson.fromJson(response,new TypeToken<ArrayList<Consignment>>(){}.getType());
        //updateUI(arrayList);
        return list;*/

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
            registerForContextMenu(recyclerView);
        }else{
            adapter.setConsignments(consignments);
            adapter.notifyDataSetChanged();
            registerForContextMenu(recyclerView);
        }

        Log.d("UPDATE", "localArrayList: "+arrayList.size());
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
