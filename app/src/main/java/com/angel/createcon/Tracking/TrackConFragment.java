package com.angel.createcon.Tracking;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angel.createcon.BackgroundTask;
import com.angel.createcon.ConsAdapter;
import com.angel.createcon.Consignment;
import com.angel.createcon.Decoration.SimpleDividerItemDecoration;
import com.angel.createcon.R;

import java.util.ArrayList;

/**
 * Created by Angel on 10/20/2016.
 */

public class TrackConFragment extends Fragment {
Consignment con;

ConsAdapter adapter;
//RecyclerView containing showing all cons
RecyclerView recyclerView;

RecyclerView.LayoutManager layoutManager;
ArrayList<Consignment> arrayList = new ArrayList<>();
TextView txtView;
BackgroundTask backgroundTask;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.track_con_fragment_layout,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();

        if (args!= null) {
            // Set article based on argument passed in
            con = args.getParcelable("CON");

        }
    }
}
