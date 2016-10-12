package com.angel.createcon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    Button createCon, getCon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().hasExtra("NEW_CON")){
            Consignment con =  getIntent().getParcelableExtra("NEW_CON");
            Gson gson = new Gson();
            String json = gson.toJson(con);
            Log.d("STATE",json);
            TextView txt = (TextView) findViewById(R.id.main_textview);
            txt.setText(json);
        }
        createCon = (Button) findViewById(R.id.create_con);
        createCon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreateConActivity.class));
            }
        });

        getCon = (Button) findViewById(R.id.get_con);
        getCon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GetConsActivity.class));
            }
        });
    }

}
