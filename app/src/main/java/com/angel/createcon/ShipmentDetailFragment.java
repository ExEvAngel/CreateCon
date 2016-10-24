package com.angel.createcon;

import android.app.Activity;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by Angel on 10/10/2016.
 */

public class ShipmentDetailFragment extends Fragment{
    int nopiece;
    String payterm, service, opt, description,currency;
    boolean dg;
    double value;

    TextView noPiece, Value, desc;
    Spinner payTerm, Service, option, Currency;
    RadioGroup dgGroup;
    AlertDialog.Builder builder;
    Consignment con;

    Button submit;

    OnCompleteShipmentDetails onCompleteShipmentDetails;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onCompleteShipmentDetails = (OnCompleteShipmentDetails) context;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface OnCompleteShipmentDetails{
        public void shipNoPiece(int piece);
        public void shipPayTerm(String payTerm);
        public void shipService(String service);
        public void shipOpt(String opt);
        public void shipDescription(String description);
        public void shipCurrency(String currency);
        public void shipValue(double value);
        public void shipDg(boolean dg);
        public void submit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipment_detail_fragment_layout,container,false);

        noPiece = (TextView) view.findViewById(R.id.no_piece);
        desc = (TextView) view.findViewById(R.id.description);
        Value = (TextView) view.findViewById(R.id.value);

        payTerm = (Spinner) view.findViewById(R.id.pay_term);
        ArrayAdapter<CharSequence> payAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.pay_term, android.R.layout.simple_spinner_item);
        payAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payTerm.setAdapter(payAdapter);


        Service = (Spinner) view.findViewById(R.id.service) ;
        ArrayAdapter<CharSequence> serviceAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.service, android.R.layout.simple_spinner_item);
        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Service.setAdapter(serviceAdapter);

        option = (Spinner) view.findViewById(R.id.opt);
        ArrayAdapter<CharSequence> optAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.opt, android.R.layout.simple_spinner_item);
        optAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        option.setAdapter(optAdapter);

        Currency = (Spinner) view.findViewById(R.id.currency);
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.currency, android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Currency.setAdapter(currencyAdapter);

        dgGroup = (RadioGroup) view.findViewById(R.id.dg_group);
        dgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.dg_yes:
                        dg = true;
                    case R.id.dg_no:
                        dg = false;
                }
            }
        });
        dgGroup.check(R.id.dg_no);

        Bundle args = getArguments();
        if (args.containsKey("CON")) {
            con = args.getParcelable("CON");
            int spinnerPosition = payAdapter.getPosition(con.getPayterm());
            payTerm.setSelection(spinnerPosition);
            spinnerPosition = serviceAdapter.getPosition(con.getService());
            Service.setSelection(spinnerPosition);
            spinnerPosition = optAdapter.getPosition(con.getOpt());
            option.setSelection(spinnerPosition);
            spinnerPosition = currencyAdapter.getPosition(con.getCurrency());
            Currency.setSelection(spinnerPosition);

            if(con.isDg()){
                dgGroup.check(R.id.dg_yes);
            }else{
                dgGroup.check(R.id.dg_no);
            }
            noPiece.setText(String.valueOf(con.getNopiece()));
            desc.setText(con.getDescription());
            Value.setText(String.valueOf(con.getValue()));
        }

        builder = new AlertDialog.Builder(getActivity());

        submit = (Button) view.findViewById(R.id.submit_con);
        submit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                description = desc.getText().toString();
                String nopiece_string = noPiece.getText().toString();
                nopiece = Integer.parseInt(nopiece_string);
                String value_string = Value.getText().toString();
                value = Double.parseDouble(value_string);
                payterm = payTerm.getSelectedItem().toString();
                service = Service.getSelectedItem().toString();
                opt = option.getSelectedItem().toString();
                currency = Currency.getSelectedItem().toString();

                if (value_string.equals("")||nopiece_string.equals("")|| description.equals("")){
                    builder.setTitle("Incomplete Fields");
                    builder.setMessage("Please fill in all the required fields");
                    builder.show();

                } else{
                    onCompleteShipmentDetails.shipPayTerm(payterm);
                    onCompleteShipmentDetails.shipService(service);
                    onCompleteShipmentDetails.shipOpt(opt);
                    onCompleteShipmentDetails.shipDescription(description);
                    onCompleteShipmentDetails.shipNoPiece(nopiece);
                    onCompleteShipmentDetails.shipValue(value);
                    onCompleteShipmentDetails.shipCurrency(currency);
                    onCompleteShipmentDetails.shipDg(dg);
                    onCompleteShipmentDetails.submit();
                }
            }
        });
        return view;
    }
}
