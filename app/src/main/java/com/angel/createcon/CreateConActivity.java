package com.angel.createcon;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class CreateConActivity extends AppCompatActivity implements SenderDetailFragment.OnCompleteSendDetails, ReceiverDetailFragment.OnCompleteReceiverDetails {
    int id, conid, nopiece;
    double value;
    String payterm, custref, service, opt, description,currency, userid;
    String sendacc, sendname, sendaddress, sendcity, sendpostcode, sendcountry, sendcontactname, sendcontactno;
    String recacc, recname, recaddress, reccity, recpostcode, reccountry, reccontactname, reccontactno;
    boolean dg, parked;
    Date creationdate;

    TextView textView;
    Button btn;
    Consignment consignment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_con);
        textView = (TextView) findViewById(R.id.txt_create_con);

        SenderDetailFragment senderDetailFragment = new SenderDetailFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, senderDetailFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void sendAccId(String id) {
        this.sendacc=id;

    }

    @Override
    public void customerReference(String ref) {
        this.custref = ref;
    }

    @Override
    public void sendName(String name) {
        this.sendname=name;
        textView.setText(sendname);

    }

    @Override
    public void sendAddr(String addr) {
        this.sendaddress= addr;
    }

    @Override
    public void sendCity(String city) {
        this.sendcity=city;
    }

    @Override
    public void sendPostCode(String postcode) {
        this.sendpostcode=postcode;
    }

    @Override
    public void sendCountry(String country) {
        this.sendcountry = country;
    }

    @Override
    public void sendCoName(String coName) {
        this.sendcontactname = coName;
    }

    @Override
    public void sendCoNo(String coNo) {
        this.sendcontactno = coNo;
    }

    @Override
    public void getReceiverDetails() {

        ReceiverDetailFragment receiverDetailFragment = new ReceiverDetailFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, receiverDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void recAccId(String id) {
        this.recacc=id;
    }

    @Override
    public void recName(String name) {
        this.recname=name;
    }

    @Override
    public void recAddr(String addr) {
        this.recaddress=addr;
    }

    @Override
    public void recCity(String city) {
        this.reccity=city;
    }

    @Override
    public void recPostCode(String postcode) {
        this.recpostcode=postcode;
    }

    @Override
    public void recCountry(String country) {
        this.reccountry=country;
    }

    @Override
    public void recCoName(String coName) {
        this.reccontactname=coName;
    }

    @Override
    public void recCoNo(String coNo) {
        this.reccontactno=coNo;
    }

    @Override
    public void getShipmentDetails() {

        textView.setText(sendname+recname+sendcountry);
        ShipmentDetailFragment shipmentDetailFragment = new ShipmentDetailFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, shipmentDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
