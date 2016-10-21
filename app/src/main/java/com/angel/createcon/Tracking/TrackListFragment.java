package com.angel.createcon.Tracking;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angel.createcon.BackgroundTask;
import com.angel.createcon.Consignment;
import com.angel.createcon.Decoration.SimpleDividerItemDecoration;
import com.angel.createcon.Listeners.GetTrackingListener;
import com.angel.createcon.NetworkUtils.TrackingUtil;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Angel on 10/20/2016.
 */

public class TrackListFragment extends Fragment{

    TrackAdapter adapter;
    //RecyclerView containing showing all cons
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<Tracking> arrayList = new ArrayList<>();
    TextView txt_track, txt_con;
    BackgroundTask backgroundTask;
    Consignment con;
    Gson gson;
    int conNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.track_list_fragment_layout,container,false);
        gson = new Gson();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        txt_con = (TextView) view.findViewById(R.id.txt_con);
        txt_track= (TextView) view.findViewById(R.id.txt_track);
        Bundle args = getArguments();
        if (args.containsKey("CON")) {
            // Set article based on argument passed in

            con = args.getParcelable("CON");
            TrackingUtil trackingUtil = new TrackingUtil(getActivity());

            txt_con.setText(String.valueOf(con.getConid()));
            trackingUtil.getTracking(con,new GetTrackingListener() {
                @Override
                public void onSuccess(String response) {

                    Log.d("onSuccess", "Response: "+response);
                    ArrayList<Tracking> list = parseResponse(response);
                    if(list.size()==0) {
                        txt_track.setText("No tracking found on ");
                    }else {
                        updateUI(list);
                    }
                    //Toast.makeText(GetConsActivity.this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();

                    Log.d("ONSUCCESS", "ARRAYSIZE: "+arrayList.size());
                }
            });
        } else{

            conNumber = args.getInt("CONNUMBER");
            TrackingUtil trackingUtil = new TrackingUtil(getActivity());

            trackingUtil.getTracking(conNumber,new GetTrackingListener() {
                @Override
                public void onSuccess(String response) {

                    Log.d("onSuccess", "Response: "+response);
                    ArrayList<Tracking> list = parseResponse(response);
                    txt_con.setText(String.valueOf(conNumber));
                    if(list.size()==0) {
                        txt_track.setText("No tracking found on ");
                    }else{
                        updateUI(list);

                    }
                    //Toast.makeText(GetConsActivity.this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();

                    Log.d("ONSUCCESS", "ARRAYSIZE: "+arrayList.size());
                }
            });
        }

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();

        if (args!= null) {
            // Set article based on argument passed in
            con = args.getParcelable("CON");
        }
    }
    public ArrayList<Tracking> parseResponse(String response){

        return gson.fromJson(response,new TypeToken<ArrayList<Tracking>>(){}.getType());
    }


    public void updateUI(ArrayList<Tracking> tracking){

            this.arrayList = tracking;
            txt_track.setText("Tracking");
            String jsonArray = gson.toJson(tracking, new TypeToken<ArrayList<Tracking>>() {
            }.getType());
            Log.d("UPDATE", "back2Json: " + jsonArray);
            Log.d("UPDATE", "tracksize: " + tracking.size());
            if (adapter == null) {
                adapter = new TrackAdapter(tracking, getActivity().getApplicationContext());
                recyclerView.setAdapter(adapter);
            } else {
                adapter.setTracking(tracking);
                adapter.notifyDataSetChanged();
            }


        Log.d("UPDATE", "localArrayList: "+arrayList.size());
    }
}
