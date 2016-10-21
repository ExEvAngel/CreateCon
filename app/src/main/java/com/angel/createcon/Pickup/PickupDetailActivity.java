package com.angel.createcon.Pickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.angel.createcon.POJO.Pickup;
import com.angel.createcon.R;
import com.google.gson.Gson;

public class PickupDetailActivity extends AppCompatActivity {
    Gson gson;
    Pickup pickup;
    LinearLayout dgdeclayout,lithDeclayout,fdaDeclayout;
    RadioGroup dgGroup,dgDecCheck,lithCheck,lithDecCheck, fdaCheck, fdaDecCheck;
    boolean dgPpwkMissing, lithDecMissing, fdaDecMissing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_detail);
        TextView connote, name, address, city, postcode, description, nopiece,dg;
        Button parkUnpark, track;
        gson = new Gson();


        if(getIntent().hasExtra("PICKUP")){
            pickup =  getIntent().getParcelableExtra("PICKUP");


            connote = (TextView) findViewById(R.id.connote);
            name = (TextView) findViewById(R.id.name);
            address = (TextView) findViewById(R.id.address);
            city = (TextView) findViewById(R.id.city);
            postcode = (TextView) findViewById(R.id.postcode);
            description = (TextView) findViewById(R.id.description);
            nopiece = (TextView) findViewById(R.id.nopiece);
            dg = (TextView) findViewById(R.id.dg);

            dgdeclayout = (LinearLayout) findViewById(R.id.dgDec_check_layout);

            if(pickup.isDg()){
                dg.setText("Yes");
                dgdeclayout.setVisibility(View.VISIBLE);
            }else{
                dg.setText("No");
            }

            connote.setText(String.valueOf(pickup.getConid()));
            name.setText(pickup.getSendname());
            address.setText(pickup.getSendaddress());
            city.setText(pickup.getSendcity());
            postcode.setText(pickup.getSendpostcode());
            description.setText(pickup.getDescription());
            nopiece.setText(String.valueOf(pickup.getNopiece()));

            dgDecCheck = (RadioGroup) findViewById(R.id.dgDec_Check);
            dgDecCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId){
                        case R.id.dgDec_yes:
                            dgPpwkMissing = false;
                        case R.id.dgDec_no:
                            dgPpwkMissing = true;
                    }
                }
            });
            dgDecCheck.check(R.id.dgDec_no);


            lithDeclayout = (LinearLayout) findViewById(R.id.lithDec_check_layout);
            lithCheck = (RadioGroup) findViewById(R.id.lith_check);
            lithCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId){
                        case R.id.lith_yes:
                            lithDeclayout.setVisibility(View.VISIBLE);
                        case R.id.lith_no:
                            lithDecMissing =false;
                            //lithDeclayout.setVisibility(View.GONE);
                    }
                }
            });
            lithCheck.check(R.id.lith_no);

            lithDecCheck = (RadioGroup) findViewById(R.id.lithDec_check);
            lithDecCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId){
                        case R.id.lithDec_yes:
                             lithDecMissing = false;
                        case R.id.lithDec_no:
                            lithDecMissing = true;

                    }
                }
            });
            lithDecCheck.check(R.id.lithDec_no);


            fdaDeclayout = (LinearLayout) findViewById(R.id.fdaDec_check_layout);
            fdaCheck = (RadioGroup) findViewById(R.id.fda_check);
            fdaCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId){
                        case R.id.fda_yes:
                            fdaDeclayout.setVisibility(View.VISIBLE);
                        case R.id.fda_no:
                            //fdaDeclayout.setVisibility(View.GONE);
                            fdaDecMissing =false;
                    }
                }
            });
            fdaCheck.check(R.id.fda_no);

            fdaDecCheck = (RadioGroup) findViewById(R.id.fdaDec_check);
            fdaDecCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId){
                        case R.id.fdaDec_yes:
                            fdaDecMissing = false;
                        case R.id.fdaDec_no:
                            fdaDecMissing =true;

                    }
                }
            });
            fdaCheck.check(R.id.fda_no);





        }
    }
}
