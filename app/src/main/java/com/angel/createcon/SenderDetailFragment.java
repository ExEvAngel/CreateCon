package com.angel.createcon;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Angel on 10/10/2016.
 */

public class SenderDetailFragment extends Fragment {
    EditText accId, custRef, sendName, sendAddr, sendCity,sendPostcode,
    sendCountry, sendCoName, sendCoNo;

    String payterm, custref;
    String sendacc, sendname, sendaddress, sendcity, sendpostcode, sendcountry, sendcontactname, sendcontactno;

    Button complete;
    OnCompleteSendDetails onCompleteSendDetails;
    Consignment con;
    AlertDialog.Builder builder;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onCompleteSendDetails = (OnCompleteSendDetails) context;

        } catch (Exception e){
        }

    }

    public interface OnCompleteSendDetails{
        public void sendAccId (String id);
        public void customerReference (String ref);
        public void sendName(String name);
        public void sendAddr(String addr);
        public void sendCity(String city);
        public void sendPostCode(String postcode);
        public void sendCountry(String country);
        public void sendCoName(String coName);
        public void sendCoNo(String coNo);
        public void getReceiverDetails();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sender_detail_fragment_layout,container,false);

        accId = (EditText) view.findViewById(R.id.acc_id);
        custRef = (EditText) view.findViewById(R.id.cust_ref);
        sendName = (EditText) view.findViewById(R.id.send_name);
        sendAddr = (EditText) view.findViewById(R.id.send_address);
        sendCity = (EditText) view.findViewById(R.id.send_city);
        sendPostcode = (EditText) view.findViewById(R.id.send_postcode);
        sendCountry = (EditText) view.findViewById(R.id.send_country);
        sendCoName = (EditText) view.findViewById(R.id.send_contact_name);
        sendCoNo = (EditText) view.findViewById(R.id.send_contact_no);


        builder = new AlertDialog.Builder(getActivity().getApplicationContext());

        complete = (Button) view.findViewById(R.id.sender_complete);
        complete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {


                sendacc = accId.getText().toString();
                custref = custRef.getText().toString();
                sendname = sendName.getText().toString();
                sendaddress = sendAddr.getText().toString();
                sendcity = sendCity.getText().toString();
                sendpostcode = sendPostcode.getText().toString();
                sendcountry = sendCountry.getText().toString();
                sendcontactname = sendCoName.getText().toString();
                sendcontactno = sendCoNo.getText().toString();

                if (sendname.equals("")||sendaddress.equals("")||sendcity.equals("")||sendpostcode.equals("")||sendcity.equals("")||
                    sendpostcode.equals("")||sendcountry.equals("")){
                    builder.setTitle("Form Incomplete");
                    builder.setMessage("Please complete the necessary fields");
                    builder.show();
                } else{
                    onCompleteSendDetails.sendAccId(sendacc);
                    onCompleteSendDetails.customerReference(custref);
                    onCompleteSendDetails.sendName(sendname);
                    onCompleteSendDetails.sendAddr(sendaddress);
                    onCompleteSendDetails.sendCity(sendcity);
                    onCompleteSendDetails.sendPostCode(sendpostcode);
                    onCompleteSendDetails.sendCountry(sendcountry);
                    onCompleteSendDetails.sendCoName(sendcontactname);
                    onCompleteSendDetails.sendCoNo(sendcontactno);
                    onCompleteSendDetails.getReceiverDetails();
                }


            }
        });

        return view;
    }

    public void displayAlert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input_error")){
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
