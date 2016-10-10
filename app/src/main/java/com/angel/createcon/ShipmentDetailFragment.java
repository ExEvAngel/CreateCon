package com.angel.createcon;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Currency;
import java.util.Set;

import static java.util.Currency.getAvailableCurrencies;

/**
 * Created by Angel on 10/10/2016.
 */

public class ShipmentDetailFragment extends Fragment{
    int nopiece;
    double value;
    String payterm, service, opt, description,currency;
    boolean dg;
    TextView noPiece, Value;
    Spinner payTerm, Service, option, Currency;
    RadioGroup dgGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipment_detail_fragment_layout,container,false);

        noPiece = (TextView) view.findViewById(R.id.no_piece);
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

        return view;
    }
}
