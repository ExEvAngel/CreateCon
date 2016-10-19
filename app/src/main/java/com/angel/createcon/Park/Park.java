package com.angel.createcon.Park;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.angel.createcon.Consignment;
import com.angel.createcon.GetConsActivity;
import com.angel.createcon.NetworkUtils.UnsenableOperations;
import com.angel.createcon.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Park extends AppCompatActivity {
    Consignment con;
    Gson gson;
    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        if(getIntent().hasExtra("CON")){
            con =  getIntent().getParcelableExtra("CON");
            gson = new Gson();
            String json = gson.toJson(con);
            Log.d("PARK",json);

            if(con.isParked()) {
                unParkCon();
            }else{
                ParkConFragment parkConFragment = new ParkConFragment();
                Bundle args = new Bundle();
                args.putParcelable("CON", con);
                parkConFragment.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, parkConFragment);
                fragmentTransaction.commit();
            }
        }

    }

    public void unParkCon(){
        UnsenableOperations unsenableOperations = new UnsenableOperations(this);
        JSONObject parkDetails = new JSONObject();
        Date today = Calendar.getInstance().getTime();
        String date = today.toString();
        try {
            parkDetails.put("date", date);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        unsenableOperations.unParkCon(con, parkDetails);
        Intent intent = new Intent(Park.this, GetConsActivity.class);
        startActivity(intent);
    }
}
