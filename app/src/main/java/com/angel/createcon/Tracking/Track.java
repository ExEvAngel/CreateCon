package com.angel.createcon.Tracking;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.angel.createcon.Consignment;
import com.angel.createcon.Park.ParkConFragment;
import com.angel.createcon.R;
import com.google.gson.Gson;

public class Track extends AppCompatActivity {
    Consignment con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        Gson gson = new Gson();
        if(getIntent().hasExtra("CON")){
            con =  getIntent().getParcelableExtra("CON");
            gson = new Gson();
            String json = gson.toJson(con);
            Log.d("TRACKINGCON",json);

            TrackConFragment trackConFragment = new TrackConFragment();
            Bundle args = new Bundle();
            args.putParcelable("CON", con);
            trackConFragment.setArguments(args);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, trackConFragment);
            fragmentTransaction.commit();
        }
    }
}
