package com.angel.createcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

public class ConsignmentDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consignment_detail);

        if(getIntent().hasExtra("CON")){
            Consignment con =  getIntent().getParcelableExtra("CON");
            Gson gson = new Gson();
            String json = gson.toJson(con);
            Log.d("DETAILS",json);
            TextView txt = (TextView) findViewById(R.id.details_con);
            txt.setText(json);
        }
    }
}
