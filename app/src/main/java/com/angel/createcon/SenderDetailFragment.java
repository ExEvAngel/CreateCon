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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Angel on 10/10/2016.
 */

public class SenderDetailFragment extends Fragment {
    EditText conId, sendAcc, custRef, sendName, sendAddr, sendCity,sendPostcode,sendCoName, sendCoNo;

    int conid;

    String payterm, custref;
    String sendacc, sendname, sendaddress, sendcity, sendpostcode, sendcountry, sendcontactname, sendcontactno;

    Button complete;
    Spinner sendCountry;
    OnCompleteSendDetails onCompleteSendDetails;
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
        public void sendConId(int conid);
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

        sendCountry = (Spinner) view.findViewById(R.id.sender_country_spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.countries_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
        sendCountry.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition("Australia");
        sendCountry.setSelection(spinnerPosition);
        conId = (EditText) view.findViewById(R.id.con_id);
        sendAcc = (EditText) view.findViewById(R.id.send_acc);
        custRef = (EditText) view.findViewById(R.id.cust_ref);
        sendName = (EditText) view.findViewById(R.id.send_name);
        sendAddr = (EditText) view.findViewById(R.id.send_address);
        sendCity = (EditText) view.findViewById(R.id.send_city);
        sendPostcode = (EditText) view.findViewById(R.id.send_postcode);
        sendCoName = (EditText) view.findViewById(R.id.send_contact_name);
        sendCoNo = (EditText) view.findViewById(R.id.send_contact_no);


        builder = new AlertDialog.Builder(getActivity());

        complete = (Button) view.findViewById(R.id.sender_complete);
        complete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                String con_id_string = conId.getText().toString();
                conid = Integer.parseInt(con_id_string);
                sendacc = sendAcc.getText().toString();
                custref = custRef.getText().toString();
                sendname = sendName.getText().toString();
                sendaddress = sendAddr.getText().toString();
                sendcity = sendCity.getText().toString();
                sendpostcode = sendPostcode.getText().toString();
                sendcountry = sendCountry.getSelectedItem().toString();
                sendcontactname = sendCoName.getText().toString();
                sendcontactno = sendCoNo.getText().toString();

                if (con_id_string.equals("")||sendname.equals("")||sendaddress.equals("")||sendcity.equals("")||sendpostcode.equals("")||sendcountry.equals("")){
                    builder.setTitle("Incomplete Fields");
                    builder.setMessage("Please fill in all the required fields");
                    builder.show();
                } else{
                    onCompleteSendDetails.sendConId(conid);
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
