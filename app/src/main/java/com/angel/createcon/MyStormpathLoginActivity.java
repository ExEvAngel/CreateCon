package com.angel.createcon;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.angel.createcon.NetworkUtils.FcmService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.stormpath.sdk.ui.StormpathLoginActivity;

/**
 * Created by Angel on 10/22/2016.
 */

public class MyStormpathLoginActivity extends StormpathLoginActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void onLoginSuccess() {
        super.onLoginSuccess();
        sendFcmToken();

    }

    public void  sendFcmToken(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("LOGINTOKEN", "Refreshed token: " + refreshedToken);
        FcmService fcmService = new FcmService(getApplicationContext());
        fcmService.putToken(FirebaseInstanceId.getInstance().getToken());
    }
}
