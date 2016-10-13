package com.angel.createcon;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateConActivity extends AppCompatActivity implements SenderDetailFragment.OnCompleteSendDetails, ReceiverDetailFragment.OnCompleteReceiverDetails,ShipmentDetailFragment.OnCompleteShipmentDetails {
    int nopiece,conid;
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
    public void sendConId(int conid) {
        this.conid=conid;
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

    @Override
    public void shipNoPiece(int piece) {
        this.nopiece = piece;
    }

    @Override
    public void shipPayTerm(String payTerm) {
        this.payterm=payTerm;
    }

    @Override
    public void shipService(String service) {
        this.service=service;
    }

    @Override
    public void shipOpt(String opt) {
        this.opt=opt;
    }

    @Override
    public void shipDescription(String description) {
        this.description=description;
    }

    @Override
    public void shipCurrency(String currency) {
        this.currency=currency;
    }

    @Override
    public void shipValue(double value) {
        this.value=value;
    }

    @Override
    public void shipDg(boolean dg) {
        this.dg=dg;
    }

    @Override
    public void submit() {

        Date today = Calendar.getInstance().getTime();
        Consignment con = new Consignment(null,conid, payterm, custref,
                sendacc, sendname, sendaddress, sendcity, sendpostcode, sendcountry, sendcontactname, sendcontactno,
                recacc, recname, recaddress, reccity, recpostcode, reccountry, reccontactname, reccontactno,
                service, opt, dg, nopiece, description,  value, currency, userid, false,today );


        //BackgroundTask backgroundTask = new BackgroundTask(CreateConActivity.this);
        //backgroundTask.createCon(con);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("NEW_CON",con);
        startActivity(intent);
        /*con.setId(null);
        con.setConid(conid);
        con.setNopiece(nopiece);
        con.setValue(value);
        con.setPayterm(payterm);
        con.setCustref(custref);
        con.setService(service);
        con.setOpt(opt);
        con.setDescription(description);
        con.setCurrency(currency);
        con.setUserid(null);

        con.setSendacc(sendacc);
        con.setSendname(sendname);
        con.setSendaddress(sendaddress);
        con.setSendcity(sendcity);
        con.setSendpostcode(sendpostcode);
        con.setSendcountry(sendcountry);
        con.setSendcontactname(sendcontactname);
        con.setSendcontactno(sendcontactno);
        con.setRecacc(recacc);
        con.setRecname(recname);
        con.setRecaddress(recaddress);
        con.setReccity(reccity);
        con.setRecpostcode(recpostcode);
        con.setReccountry(reccountry);
        con.setReccontactname(reccontactname);
        con.setReccontactno(reccontactno);
        */
    }
}
