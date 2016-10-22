package com.angel.createcon.Park;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.angel.createcon.Consignment;
import com.angel.createcon.GetConsActivity;
import com.angel.createcon.MainActivity;
import com.angel.createcon.NetworkUtils.UnsenableOperations;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.R;
import com.google.gson.Gson;
import com.stormpath.sdk.Stormpath;
import com.stormpath.sdk.StormpathCallback;
import com.stormpath.sdk.models.StormpathError;
import com.stormpath.sdk.models.UserProfile;

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

                Stormpath.getUserProfile(new StormpathCallback<UserProfile>() {
                    @Override
                    public void onSuccess(UserProfile userProfile) {
                        //showProgressDialog();
                        unParkCon(userProfile.getEmail());

                        Intent intent = new Intent(Park.this, GetConsActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(StormpathError error) {
                        // Show login view
                    }
                });
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

    public void unParkCon(String username){
        UnsenableOperations unsenableOperations = new UnsenableOperations(this);
        Date today = Calendar.getInstance().getTime();
        Tracking tracking = new Tracking("UP", "Unparked. Good to Go", username, "SYD",today, 0, con.getId(), con.getConid());
        unsenableOperations.unParkCon(con, tracking);

    }
}
