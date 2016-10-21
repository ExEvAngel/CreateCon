package com.angel.createcon.Pickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.angel.createcon.Decoration.SimpleDividerItemDecoration;
import com.angel.createcon.Listeners.GetPickupListener;
import com.angel.createcon.NetworkUtils.PickupUtil;
import com.angel.createcon.POJO.Pickup;
import com.angel.createcon.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stormpath.sdk.Stormpath;
import com.stormpath.sdk.StormpathCallback;
import com.stormpath.sdk.models.StormpathError;
import com.stormpath.sdk.models.UserProfile;

import java.sql.Driver;
import java.util.ArrayList;

import static android.support.v7.recyclerview.R.attr.layoutManager;

public class DriverPickup extends AppCompatActivity {
    PickupAdapter adapter;
    //RecyclerView containing showing all cons
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Pickup> arrayList = new ArrayList<>();
    TextView txtView;
    PickupUtil pickupUtil;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_pickup);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));


        gson = new Gson();
        Stormpath.getUserProfile(new StormpathCallback<UserProfile>() {
            @Override
            public void onSuccess(UserProfile userProfile) {

                makeRequest(userProfile.getEmail());
            }

            @Override
            public void onFailure(StormpathError error) {
                // Show login view
            }
        });

    }

    private void makeRequest(String email) {
        PickupUtil pickupUtil = new PickupUtil(getApplicationContext());
        pickupUtil.getPickups(email, new GetPickupListener() {
            @Override
            public void onSuccess(String response) {

                Log.d("onSuccess", "Response: "+response);
                ArrayList<Pickup> list = parseResponse(response);
                if(list.size()==0) {
                    //txt_track.setText("No Pickup ");
                }else {
                    updateUI(list);
                }
                //Toast.makeText(GetConsActivity.this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();

                Log.d("ONSUCCESS", "ARRAYSIZE: "+arrayList.size());
            }
        });
    }

    public ArrayList<Pickup> parseResponse(String response){

        return gson.fromJson(response,new TypeToken<ArrayList<Pickup>>(){}.getType());
    }


    public void updateUI(ArrayList<Pickup> pickup){
        this.arrayList = pickup;
        String jsonArray = gson.toJson(pickup, new TypeToken<ArrayList<Pickup>>() {
        }.getType());
        //Log.d("UPDATE", "back2Json: " + jsonArray);
        //Log.d("UPDATE", "tracksize: " + tracking.size());
        if (adapter == null) {
            adapter = new PickupAdapter(pickup,DriverPickup.this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setPickups(pickup);
            adapter.notifyDataSetChanged();
        }


        Log.d("UPDATE", "localArrayList: "+arrayList.size());
    }
}
