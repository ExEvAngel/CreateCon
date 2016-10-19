package com.angel.createcon.Park;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.angel.createcon.BackgroundTask;
import com.angel.createcon.Consignment;
import com.angel.createcon.CreateConActivity;
import com.angel.createcon.GetConsActivity;
import com.angel.createcon.MainActivity;
import com.angel.createcon.NetworkUtils.UnsenableOperations;
import com.angel.createcon.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

import static com.angel.createcon.R.string.conId;

/**
 * Created by Angel on 10/19/2016.
 */

public class ParkConFragment extends Fragment {
    Spinner parkReason;
    TextView parkRemarks, conId;
    Button park;
    Consignment con;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.park_con_fragment,container,false);

        parkReason = (Spinner) view.findViewById(R.id.park_reason);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.park_reason, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        parkReason.setAdapter(adapter);

        parkRemarks = (TextView) view.findViewById(R.id.park_remark);

        conId = (TextView) view.findViewById(R.id.con_id);

        park = (Button) view.findViewById(R.id.park_btn);

        park.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                parkCon();
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();

        if (args!= null) {
            // Set article based on argument passed in
            con = args.getParcelable("CON");
            conId.setText(String.valueOf(con.getConid()));
        }
    }

    public void parkCon(){
        UnsenableOperations unsenableOperations = new UnsenableOperations(getActivity());
        JSONObject parkDetails = new JSONObject();
        String reason = parkReason.getSelectedItem().toString();
        String remarks = String.valueOf(parkRemarks.getText());
        Date today = Calendar.getInstance().getTime();
        String date = today.toString();
        try {
            parkDetails.put("reason", reason);
            parkDetails.put("remarks", remarks);
            parkDetails.put("date", date);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        unsenableOperations.parkCon(con, parkDetails);
        Intent intent = new Intent(getActivity(), GetConsActivity.class);
        startActivity(intent);
    }
}
