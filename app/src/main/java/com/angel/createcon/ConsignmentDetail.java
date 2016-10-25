package com.angel.createcon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.angel.createcon.FileHandler.FileUpload;
import com.angel.createcon.Listeners.GetAllConsListener;
import com.angel.createcon.Park.Park;
import com.angel.createcon.Tracking.Track;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ConsignmentDetail extends AppCompatActivity {
    Consignment con;
    Gson gson;
    TextView conId, sendacc, sendName, sendAddr, sendCity, sendPc, sendCo, sendContactName,sendContactNo;
    TextView recAcc, recName, recAddr, recCity, recPc, recCo, recContactName, recContactNo;
    TextView service, opt, dg, description, noPiece, value,payTerm,custRef;
    Button parkUnpark, track, upload,editCon;
    String cid, conid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consignment_detail);

        gson = new Gson();
        Bundle args  =  getIntent().getExtras();
        Log.d("ARGS", args.toString());
        if(getIntent().hasExtra("id")){
            args  =  getIntent().getExtras();
            Log.d("CONDETARGS", args.toString());
            String cid = args.getString("id");
            if(cid==null){
                cid = String.valueOf(args.getInt("id"));
            }
            BackgroundTask backgroundTask = new BackgroundTask(ConsignmentDetail.this);
            backgroundTask.getCon(Integer.parseInt(cid), new GetAllConsListener() {
                @Override
                public void onSuccess(String response) {
                    con = parseResponse(response);
                    senderDetails();
                    receiverDetails();
                    shipmentDetails();
                }
            });
        }else if(getIntent().hasExtra("CON")){
            con =  getIntent().getParcelableExtra("CON");
            senderDetails();
            receiverDetails();
            shipmentDetails();

        }
    }
    public void receiverDetails(){
        recAcc = (TextView) findViewById(R.id.rec_acc);
        recName = (TextView) findViewById(R.id.rec_name);
        recAddr = (TextView) findViewById(R.id.rec_address);
        recCity = (TextView) findViewById(R.id.rec_city);
        recPc = (TextView) findViewById(R.id.rec_postcode);
        recCo = (TextView) findViewById(R.id.rec_country);
        recContactName = (TextView) findViewById(R.id.rec_contact_name);
        recContactNo = (TextView) findViewById(R.id.rec_contact_no);

        recAcc.setText(con.getRecacc());
        recName.setText(con.getRecname());
        recAddr.setText(con.getRecaddress());
        recCity.setText(con.getReccity());
        recPc.setText(con.getRecpostcode());
        recCo.setText(con.getReccountry());
        recContactName.setText(con.getReccontactname());
        recContactNo.setText(con.getReccontactno());
    }
    public void shipmentDetails(){
        payTerm = (TextView) findViewById(R.id.pay_term);
        custRef = (TextView) findViewById(R.id.cust_ref);
        service = (TextView) findViewById(R.id.service);
        opt = (TextView) findViewById(R.id.opt);
        dg = (TextView) findViewById(R.id.dg);
        description = (TextView) findViewById(R.id.description);
        noPiece = (TextView) findViewById(R.id.no_piece);
        value = (TextView) findViewById(R.id.value);

        payTerm.setText(con.getPayterm());
        custRef.setText(con.getCustref());
        service.setText(con.getService());
        opt.setText(con.getOpt());
        if(con.isDg()){
            dg.setText("Yes");
        }else{
            dg.setText("No");
        }
        description.setText(con.getDescription());
        noPiece.setText(String.valueOf(con.getNopiece()));
        value.setText(String.valueOf(con.getValue())+" "+con.getCurrency());

    }

    public void senderDetails(){

        String json = gson.toJson(con);
        Log.d("DETAILS",json);

        conId = (TextView) findViewById(R.id.con_id);
        sendacc = (TextView) findViewById(R.id.send_acc);
        sendName = (TextView) findViewById(R.id.send_name);
        sendAddr = (TextView) findViewById(R.id.send_address);
        sendCity = (TextView) findViewById(R.id.send_city);
        sendPc = (TextView) findViewById(R.id.send_postcode);
        sendCo = (TextView) findViewById(R.id.sender_country);
        sendContactName = (TextView) findViewById(R.id.send_contact_name);
        sendContactNo = (TextView) findViewById(R.id.send_contact_no);

        String sacc = con.getSendacc();
        String sname = con.getSendname();
        String saddr= con.getSendaddress();
        String scity= con.getSendcity();
        String spc = con.getSendpostcode();
        String sco= con.getSendcountry();
        String sconame= con.getSendcontactname();
        String scono= con.getSendcontactno();

        sendacc.setText(sacc);
        conId.setText(String.valueOf(con.getConid()));
        sendName.setText(sname);
        sendAddr.setText(saddr);
        sendCity.setText(scity);
        sendPc.setText(spc);
        sendCo.setText(sco);
        sendContactName.setText(sconame);
        sendContactNo.setText(scono);

        editCon = (Button) findViewById(R.id.edit_con);
        editCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsignmentDetail.this, CreateConActivity.class);
                intent.putExtra("EDIT",con);
                String json = gson.toJson(con);
                Log.d("CON2TRACK",json);
                startActivity(intent);
            }
        });

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
        track = (Button) findViewById(R.id.track_btn);
        track.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                track();
            }
        });

        upload = (Button) findViewById(R.id.file_handler);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsignmentDetail.this, FileUpload.class);
                intent.putExtra("CON",con);
                String json = gson.toJson(con);
                Log.d("CON2TRACK",json);
                startActivity(intent);
            }
        });
    }

    public Consignment parseResponse(String response){
        return gson.fromJson(response, Consignment.class);
    }
    private void getCon(){
        BackgroundTask backgroundTask= new BackgroundTask(ConsignmentDetail.this);
        backgroundTask.getCon(con.getId(), new GetAllConsListener() {
            @Override
            public void onSuccess(String response) {

            }
        });
    }

    public void parkUnparkActivity(){
        Intent intent = new Intent(ConsignmentDetail.this, Park.class);
        intent.putExtra("CON",con);
        String json = gson.toJson(con);
        Log.d("CON2PARK",json);
        startActivity(intent);
    }

    public void track(){
        Intent intent = new Intent(ConsignmentDetail.this, Track.class);
        intent.putExtra("CON",con);
        String json = gson.toJson(con);
        Log.d("CON2TRACK",json);
        startActivity(intent);
    }

}
