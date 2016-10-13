package com.angel.createcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.angel.createcon.app.GetAllConsListener;

import java.util.ArrayList;

public class GetConsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ConsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Consignment> arrayList;
    TextView txtView;
    BackgroundTask backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cons);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ConsAdapter(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        BackgroundTask backgroundTask = new BackgroundTask();
        arrayList = backgroundTask.getAllCons();
        //Toast.makeText(this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();

        adapter.setConsignments(arrayList);


    }

}
