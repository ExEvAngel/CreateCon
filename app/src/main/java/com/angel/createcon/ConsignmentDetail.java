package com.angel.createcon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.angel.createcon.Park.Park;
import com.google.gson.Gson;

public class ConsignmentDetail extends AppCompatActivity {
    Consignment con;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consignment_detail);
        TextView conId, sendacc, sendName, sendAddr, sendCity, sendPc, sendCo, sendContactName,sendContactNo;
        Button parkUnpark;
        gson = new Gson();

        if(getIntent().hasExtra("CON")){
            con =  getIntent().getParcelableExtra("CON");

            String json = gson.toJson(con);
            Log.d("DETAILS",json);
            TextView txt = (TextView) findViewById(R.id.details_con);

            conId = (TextView) findViewById(R.id.con_id);
            sendacc = (TextView) findViewById(R.id.send_acc);
            sendName = (TextView) findViewById(R.id.send_name);
            sendAddr = (TextView) findViewById(R.id.send_address);
            sendCity = (TextView) findViewById(R.id.send_city);
            sendPc = (TextView) findViewById(R.id.send_postcode);
            sendCo = (TextView) findViewById(R.id.sender_country);
            sendContactName = (TextView) findViewById(R.id.send_contact_name);
            sendContactNo = (TextView) findViewById(R.id.send_contact_no);

            String sacc = "Send Acc:"+con.getSendacc();
            String sname = "Name:"+con.getSendname();
            String saddr= "Address"+con.getSendaddress();
            String scity= "City:"+con.getSendcity();
            String spc = "PostCode:"+con.getSendpostcode();
            String sco= "Country"+con.getSendcountry();
            String sconame= "Name:"+con.getSendcontactname();
            String scono= "ContactNo:"+con.getSendcontactno();

            sendacc.setText(sacc);
            conId.setText(String.valueOf(con.getConid()));
            sendName.setText(sname);
            sendAddr.setText(saddr);
            sendCity.setText(scity);
            sendPc.setText(spc);
            sendCo.setText(sco);
            sendContactName.setText(sconame);
            sendContactNo.setText(scono);

            parkUnpark = (Button) findViewById(R.id.park_unpark);
            if(con.isParked()){
                parkUnpark.setText("Unpark Consignment");
            } else {
                parkUnpark.setText("Park Consignment");
            }
            parkUnpark.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    parkUnparkActivity();
                }
            });
        }
    }

    public void parkUnparkActivity(){
        Intent intent = new Intent(ConsignmentDetail.this, Park.class);
        intent.putExtra("CON",con);
        String json = gson.toJson(con);
        Log.d("CONDETAIL",json);
        startActivity(intent);
    }

}
