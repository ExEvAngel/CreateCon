package com.angel.createcon;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Angel on 10/10/2016.
 */

public class ReceiverDetailFragment extends Fragment {
    EditText recAcc, recName, recAddr, recCity,recPostcode, recCoName, recCoNo;
    String recacc, recname, recaddress, reccity, recpostcode, reccountry, reccontactname, reccontactno;

    Button complete;
    Spinner recCountry;
    ReceiverDetailFragment.OnCompleteReceiverDetails onCompleteReceiverDetails;
    AlertDialog.Builder builder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onCompleteReceiverDetails = (ReceiverDetailFragment.OnCompleteReceiverDetails) context;

        } catch (Exception e){
        }

    }

    public interface OnCompleteReceiverDetails{
        public void recAccId (String id);
        public void recName(String name);
        public void recAddr(String addr);
        public void recCity(String city);
        public void recPostCode(String postcode);
        public void recCountry(String country);
        public void recCoName(String coName);
        public void recCoNo(String coNo);
        public void getShipmentDetails();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.receiver_detail_fragment_layout, container, false);

        recCountry = (Spinner) view.findViewById(R.id.rec_country_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout

        // Populate the spinner using a customized ArrayAdapter that hides the first (dummy) entry

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.countries_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        recCountry.setAdapter(adapter);

        recAcc = (EditText) view.findViewById(R.id.rec_acc);
        recName = (EditText) view.findViewById(R.id.rec_name);
        recAddr = (EditText) view.findViewById(R.id.rec_address);
        recCity = (EditText) view.findViewById(R.id.rec_city);
        recPostcode = (EditText) view.findViewById(R.id.rec_postcode);
        recCoName = (EditText) view.findViewById(R.id.rec_contact_name);
        recCoNo = (EditText) view.findViewById(R.id.rec_contact_no);

        builder = new AlertDialog.Builder(getActivity());

        complete = (Button) view.findViewById(R.id.receiver_complete);
        complete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {


                recacc = recAcc.getText().toString();
                recname = recName.getText().toString();
                recaddress = recAddr.getText().toString();
                reccity = recCity.getText().toString();
                recpostcode = recPostcode.getText().toString();
                reccountry = recCountry.getSelectedItem().toString();
                reccontactname = recCoName.getText().toString();
                reccontactno = recCoNo.getText().toString();

                if (recname.equals("")||recaddress.equals("")||reccity.equals("")||recpostcode.equals("")||reccountry.equals("")){
                    builder.setTitle("Incomplete Fields");
                    builder.setMessage("Please fill in all the required fields");
                    builder.show();

                } else{
                    onCompleteReceiverDetails.recAccId(recacc);
                    onCompleteReceiverDetails.recName(recname);
                    onCompleteReceiverDetails.recAddr(recaddress);
                    onCompleteReceiverDetails.recCity(reccity);
                    onCompleteReceiverDetails.recPostCode(recpostcode);
                    onCompleteReceiverDetails.recCountry(reccountry);
                    onCompleteReceiverDetails.recCoName(reccontactname);
                    onCompleteReceiverDetails.recCoNo(reccontactno);
                    onCompleteReceiverDetails.getShipmentDetails();
                }


            }
        });
        return view;
    }
}
