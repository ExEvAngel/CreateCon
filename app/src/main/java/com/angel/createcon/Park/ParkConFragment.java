package com.angel.createcon.Park;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.angel.createcon.BackgroundTask;
import com.angel.createcon.Consignment;
import com.angel.createcon.CreateConActivity;
import com.angel.createcon.GetConsActivity;
import com.angel.createcon.MainActivity;
import com.angel.createcon.NetworkUtils.TrackingUtil;
import com.angel.createcon.NetworkUtils.UnsenableOperations;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.R;
import com.stormpath.sdk.Stormpath;
import com.stormpath.sdk.StormpathCallback;
import com.stormpath.sdk.models.StormpathError;
import com.stormpath.sdk.models.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

import static com.angel.createcon.R.string.conId;

/**
 * Created by Angel on 10/19/2016.
 */

public class ParkConFragment extends Fragment {
    Spinner parkReason;
    TextView parkRemarks, conId;
    Button park;
    Consignment con;
    ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.park_con_fragment,container,false);


        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        parkReason = (Spinner) view.findViewById(R.id.park_reason);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.park_reason, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        parkReason.setAdapter(adapter);

        parkRemarks = (TextView) view.findViewById(R.id.park_remark);

        conId = (TextView) view.findViewById(R.id.con_id);

        park = (Button) view.findViewById(R.id.park_btn);

        park.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                //showProgressDialog();
                Stormpath.getUserProfile(new StormpathCallback<UserProfile>() {
                    @Override
                    public void onSuccess(UserProfile userProfile) {
                        //showProgressDialog();
                        parkCon(userProfile.getEmail());
                       // updateParkTracking(userProfile.getEmail());

                        Intent intent = new Intent(getActivity(), GetConsActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(StormpathError error) {
                        // Show login view
                    }
                });

            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();

        if (args!= null) {
            // Set article based on argument passed in
            con = args.getParcelable("CON");
            conId.setText(String.valueOf(con.getConid()));
        }
    }

    public void parkCon(String userid){
        UnsenableOperations unsenableOperations = new UnsenableOperations(getActivity());
        String reason = parkReason.getSelectedItem().toString();
        String remarks = String.valueOf(parkRemarks.getText());
        Date today = Calendar.getInstance().getTime();
        Tracking tracking = new Tracking("UP", reason+"."+remarks, userid, "SYD",today, 0, con.getId(), con.getConid());
        unsenableOperations.parkCon(con, tracking);
    }


    /*
    public void updateParkTracking(String userid){
        //JSONObject parkTracking = new JSONObject();
        String reason = parkReason.getSelectedItem().toString();
        String remarks = String.valueOf(parkRemarks.getText());
        Date today = Calendar.getInstance().getTime();
        Tracking tracking = new Tracking("UP", reason+"."+remarks, userid, "SYD",today, 0, con.getId(), con.getConid());
        /*
        try {
            parkTracking.put("status", "UP");
            parkTracking.put("remarks", reason+"."+remarks);
            parkTracking.put("depot", "SYD");
            parkTracking.put("date", date);
            parkTracking.put("userid", email);
            parkTracking.put("cid", con.getId());
            parkTracking.put("conid",)

        } catch (JSONException e) {
            e.printStackTrace();
        }*//*
        TrackingUtil trackingUtil  = new TrackingUtil(getActivity());
        trackingUtil.updateTracking(tracking);

    }*/
    private void showProgressDialog() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }
}
