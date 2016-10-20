package com.angel.createcon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.angel.createcon.Tracking.Track;
import com.google.gson.Gson;
import com.stormpath.sdk.Stormpath;
import com.stormpath.sdk.StormpathCallback;
import com.stormpath.sdk.models.StormpathError;
import com.stormpath.sdk.models.UserProfile;
import com.stormpath.sdk.ui.StormpathLoginActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class MainActivity extends AppCompatActivity {
    Button createCon, getCon, tracking;
    EditText mNote;
    Context context;
    private OkHttpClient okHttpClient;

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
        context = this;

        // Initialize OkHttp library.
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Stormpath.logger().d(message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        this.okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();




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

        tracking = (Button) findViewById(R.id.tracking);
        tracking.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Track.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        Stormpath.getUserProfile(new StormpathCallback<UserProfile>() {
            @Override
            public void onSuccess(UserProfile userProfile) {

            }

            @Override
            public void onFailure(StormpathError error) {
                // Show login view
                startActivity(new Intent(context, StormpathLoginActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            Stormpath.logout();
            startActivity(new Intent(context, StormpathLoginActivity.class));


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
