package com.angel.createcon.Tracking;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.angel.createcon.Consignment;
import com.angel.createcon.Park.ParkConFragment;
import com.angel.createcon.R;
import com.google.gson.Gson;

public class Track extends AppCompatActivity implements TrackConFragment.OnCompleteTrackingDetails {
    Consignment con;
    TextView txt_con;
    int conNumber;
    Button btn_track;

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

            TrackListFragment trackListFragment = new TrackListFragment();
            Bundle args = new Bundle();
            args.putParcelable("CON", con);
            trackListFragment.setArguments(args);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, trackListFragment);
            fragmentTransaction.commit();
        }else {
            TrackConFragment trackConFragment = new TrackConFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, trackConFragment);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void conId(int conID) {
        this.conNumber = conID;
        trackCon();
    }

    public void trackCon(){
        Log.d("TRACKINGCONNUMBER",String.valueOf(conNumber));
        TrackListFragment trackListFragment = new TrackListFragment();
        Bundle args = new Bundle();
        args.putInt("CONNUMBER", conNumber);
        trackListFragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, trackListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
