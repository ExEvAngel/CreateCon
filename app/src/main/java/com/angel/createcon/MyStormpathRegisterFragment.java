package com.angel.createcon;

import android.os.Build;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;

import com.angel.createcon.NetworkUtils.FcmService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.stormpath.sdk.Stormpath;
import com.stormpath.sdk.StormpathCallback;
import com.stormpath.sdk.models.RegisterParams;
import com.stormpath.sdk.models.StormpathError;
import com.stormpath.sdk.ui.StormpathRegisterFragment;

/**
 * Created by Angel on 10/22/2016.
 */

public class MyStormpathRegisterFragment extends StormpathRegisterFragment {

    @Override
    protected void onRegisterButtonClicked() {
        if (TextUtils.isEmpty(firstNameEditText.getText().toString())
                || TextUtils.isEmpty(surnameEditText.getText().toString())
                || TextUtils.isEmpty(emailEditText.getText().toString())
                || TextUtils.isEmpty(passwordEditText.getText().toString())) {
            Snackbar.make(registerButton, com.stormpath.sdk.ui.R.string.stormpath_all_fields_mandatory, Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
                Snackbar.make(registerButton, com.stormpath.sdk.ui.R.string.stormpath_requires_valid_email, Snackbar.LENGTH_SHORT).show();
            }
        }

        Stormpath.register(new RegisterParams(firstNameEditText.getText().toString(), surnameEditText.getText().toString(),
                emailEditText.getText().toString(), passwordEditText.getText().toString()), new StormpathCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                hideProgress();
                registerNewUserFcm(emailEditText.getText().toString());
            }

            @Override
            public void onFailure(StormpathError error) {
                hideProgress();
                Snackbar.make(registerButton, error.message(), Snackbar.LENGTH_LONG).show();
            }
        });

        showProgress();
    }
    public void registerNewUserFcm(String email){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("ONREGISTERTOKEN", "Refreshed token: " + refreshedToken);
        FcmService fcmService = new FcmService(getActivity());
        fcmService.putToken(email,FirebaseInstanceId.getInstance().getToken());
    }
}
