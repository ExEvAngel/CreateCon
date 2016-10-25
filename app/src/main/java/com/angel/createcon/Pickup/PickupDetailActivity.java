package com.angel.createcon.Pickup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.angel.createcon.MainActivity;
import com.angel.createcon.NetworkUtils.PickupUtil;
import com.angel.createcon.NetworkUtils.UnsenableOperations;
import com.angel.createcon.POJO.Pickup;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.Park.Park;
import com.angel.createcon.R;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

public class PickupDetailActivity extends AppCompatActivity {
    Gson gson;
    Pickup pickup;
    LinearLayout dgdeclayout,lithDeclayout,fdaDeclayout;
    RadioGroup dgGroup,dgDecCheck,lithCheck,lithDecCheck, fdaCheck, fdaDecCheck;
    RadioButton dgDec_yes, dgDec_no, lith_yes, lith_no, lithDec_yes,lithDec_no, fda_yes, fda_no,fdaDec_yes,fdaDec_no;
    boolean dgPpwkMissing, lithDecMissing, fdaDecMissing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_detail);
        TextView connote, name, address, city, postcode, description, nopiece,dg;
        Button acceptPickup, rejectPickup;
        gson = new Gson();


        if(getIntent().hasExtra("PICKUP")) {
            pickup = getIntent().getParcelableExtra("PICKUP");


            connote = (TextView) findViewById(R.id.connote);
            name = (TextView) findViewById(R.id.name);
            address = (TextView) findViewById(R.id.address);
            city = (TextView) findViewById(R.id.city);
            postcode = (TextView) findViewById(R.id.postcode);
            description = (TextView) findViewById(R.id.description);
            nopiece = (TextView) findViewById(R.id.nopiece);
            dg = (TextView) findViewById(R.id.dg);

            dgdeclayout = (LinearLayout) findViewById(R.id.dgDec_check_layout);
            // For radio buttons if goods is DG
            if (pickup.isDg()) {
                dg.setText("Yes");
                dgdeclayout.setVisibility(View.VISIBLE);
                dgRadioButtons();
            } else {
                dg.setText("No");
            }
            connote.setText(String.valueOf(pickup.getConid()));
            name.setText(pickup.getSendname());
            address.setText(pickup.getSendaddress());
            city.setText(pickup.getSendcity());
            postcode.setText(pickup.getSendpostcode());
            description.setText(pickup.getDescription());
            nopiece.setText(String.valueOf(pickup.getNopiece()));


            //Radio Buttons for checking lithium battery
            lithDeclayout = (LinearLayout) findViewById(R.id.lithDec_check_layout);
            lithCheck = (RadioGroup) findViewById(R.id.lith_check);
            lith_yes = (RadioButton) findViewById(R.id.lith_yes);
            lith_no = (RadioButton) findViewById(R.id.lith_no);
            lithDec_yes = (RadioButton) findViewById(R.id.lithDec_yes);
            lithDec_no = (RadioButton) findViewById(R.id.lithDec_no);

            lithDecMissing = false;
            lithCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (lith_yes.isChecked()){
                        lithBatteryRadioButtons();
                        if (lithDec_yes.isChecked()){
                            lithDecMissing = false;
                        } else{
                            lithDecMissing = true;
                        }
                    } else{
                        lithDeclayout.setVisibility(View.GONE);
                        lithDecMissing = false;
                    }

                }
            });
            lithCheck.check(R.id.lith_no);

            //Radio Buttons for any Food Drugs and alcohol
            fdaDeclayout = (LinearLayout) findViewById(R.id.fdaDec_check_layout);
            fdaCheck = (RadioGroup) findViewById(R.id.fda_check);
            fda_yes = (RadioButton) findViewById(R.id.fda_yes);
            fda_no = (RadioButton) findViewById(R.id.fda_no);
            fdaDec_yes = (RadioButton) findViewById(R.id.fdaDec_yes);
            fdaDec_no = (RadioButton) findViewById(R.id.fdaDec_no);
            fdaCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (fda_yes.isChecked()){
                        fdaRadioButtons();
                        if (fdaDec_yes.isChecked()){
                            fdaDecMissing = false;
                        } else{
                            fdaDecMissing =true;
                        }
                    } else{
                        fdaDeclayout.setVisibility(View.GONE);
                        fdaDecMissing =false;
                    }
                }
            });
            fdaCheck.check(R.id.fda_no);


            acceptPickup = (Button) findViewById(R.id.acceptPickup);
            acceptPickup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    completePickup();
                }
            });
            rejectPickup = (Button) findViewById(R.id.rejectPickup);
            rejectPickup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelPickup();
                }
            });

        }
    }

    public void completePickup(){

        Date pickupDate = Calendar.getInstance().getTime();
        Tracking tracking = new Tracking("PU","Picked up by driver", pickup.getDriver(),"SYD",pickupDate,0,pickup.getCid(),pickup.getConid());
        PickupUtil pickupUtil = new PickupUtil(PickupDetailActivity.this);
        pickupUtil.completePickup(pickup, tracking);


        if (dgPpwkMissing||lithDecMissing||fdaDecMissing) {
            StringBuilder stringBuilder = new StringBuilder();
            if (dgPpwkMissing) {
                stringBuilder.append("Dangerous goods documentation not provided. ");
            }
            if (lithDecMissing) {
                stringBuilder.append("Lithium battery declaration not provided. ");
            }
            if (fdaDecMissing) {
                stringBuilder.append("FDA declaration not provided");
            }
            UnsenableOperations unsenableOperations = new UnsenableOperations(PickupDetailActivity.this);
            Tracking unsendTracking = new Tracking("UP",stringBuilder.toString(), pickup.getDriver(),"SYD",pickupDate,0,pickup.getCid(),pickup.getConid());
            unsenableOperations.parkPickupCon(pickup, unsendTracking);
        }
        startActivity(new Intent(PickupDetailActivity.this,MainActivity.class));
    }
    public void cancelPickup(){
        PickupUtil pickupUtil = new PickupUtil(PickupDetailActivity.this);
        pickupUtil.rejectPickup(pickup);
        startActivity(new Intent(PickupDetailActivity.this,MainActivity.class));
    }


    public void dgRadioButtons(){
        dgDecCheck = (RadioGroup) findViewById(R.id.dgDec_Check);
        dgDec_yes = (RadioButton) findViewById(R.id.dgDec_yes);
        dgDec_no = (RadioButton) findViewById(R.id.dgDec_no);
        dgDecCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(dgDec_yes.isChecked()){
                    dgPpwkMissing = false;
                }else{
                    dgPpwkMissing = true;
                }
            }
        });
        dgDecCheck.check(R.id.dgDec_no);
    }

    public void lithBatteryRadioButtons(){
        lithDeclayout.setVisibility(View.VISIBLE);
        lithDecCheck = (RadioGroup) findViewById(R.id.lithDec_check);
        lithDecCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (lithDec_yes.isChecked()){
                    lithDecMissing = false;
                } else{
                    lithDecMissing = true;
                }
            }
        });
        lithDecCheck.check(R.id.lithDec_no);
    }



    public void fdaRadioButtons(){
        fdaDeclayout.setVisibility(View.VISIBLE);
        fdaDecCheck = (RadioGroup) findViewById(R.id.fdaDec_check);
        fdaDecCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (fdaDec_yes.isChecked()== true){
                    fdaDecMissing = false;
                } else{
                    fdaDecMissing =true;
                }
            }
        });
        fdaDecCheck.check(R.id.fda_no);

    }
}
