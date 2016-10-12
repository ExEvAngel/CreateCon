package com.angel.createcon;

import android.app.backup.BackupAgent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GetConsActivity extends AppCompatActivity implements BackgroundTask.VolleyCallback{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
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
        recyclerView.setHasFixedSize(true);
        backgroundTask = new BackgroundTask(GetConsActivity.this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundTask.getAllCons(new BackgroundTask.VolleyCallback() {
            @Override
            public void onSuccess(ArrayList<Consignment> con) {
                arrayList=con;
                //Toast.makeText(this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();
                adapter = new RecyclerAdapter(arrayList);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onSuccess(ArrayList<Consignment> con) {
        this.arrayList = con;
    }
}
