package com.angel.createcon;

import android.app.backup.BackupAgent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GetConsActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Consignment> arrayList;
    TextView txtView;
    BackgroundTask backgroundTask;
    BackgroundTask.VolleyCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cons);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        backgroundTask = new BackgroundTask(GetConsActivity.this);
        backgroundTask.getAllCons(new BackgroundTask.VolleyCallback() {
            @Override
            public void onGetAllConsSuccess(ArrayList<Consignment> cons) {
                arrayList = cons;
            }
        });
        //Toast.makeText(this, "Consignments:"+arrayList.size(), Toast.LENGTH_LONG).show();
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);

    }
}
