package com.angel.createcon.Tracking;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.angel.createcon.BackgroundTask;
import com.angel.createcon.ConsAdapter;
import com.angel.createcon.Consignment;
import com.angel.createcon.Decoration.SimpleDividerItemDecoration;
import com.angel.createcon.GetConsActivity;
import com.angel.createcon.Listeners.GetAllConsListener;
import com.angel.createcon.Listeners.GetTrackingListener;
import com.angel.createcon.NetworkUtils.TrackingUtil;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.R;
import com.angel.createcon.SenderDetailFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Angel on 10/20/2016.
 */

public class TrackConFragment extends Fragment {


    Gson gson;
    EditText edt_txt_con;
    Button txt_track;
    int conNumber;
    OnCompleteTrackingDetails onCompleteTrackingDetails;

    public interface OnCompleteTrackingDetails{
         void conId(int conID);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onCompleteTrackingDetails = (OnCompleteTrackingDetails) context;

        } catch (Exception e){
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.track_menu_fragment_layout, container, false);

        gson = new Gson();

        edt_txt_con = (EditText) view.findViewById(R.id.edt_txt_con);
        txt_track = (Button) view.findViewById(R.id.btn_track);

        txt_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conNumber = Integer.parseInt(edt_txt_con.getText().toString());
                onCompleteTrackingDetails.conId(conNumber);
            }
        });

        return view;

    }

}
