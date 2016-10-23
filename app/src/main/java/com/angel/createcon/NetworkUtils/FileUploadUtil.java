package com.angel.createcon.NetworkUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angel.createcon.Consignment;
import com.angel.createcon.Listeners.GetTrackingListener;
import com.angel.createcon.POJO.Pickup;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.Tracking.TrackAdapter;
import com.angel.createcon.app.AppController;
import com.google.gson.Gson;
import com.stormpath.sdk.Stormpath;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Angel on 10/23/2016.
 */

public class FileUploadUtil {
    Gson gson;
    Type listType;

    AppController appController;
    TrackAdapter adapter;
    Context context;
    GetTrackingListener getTrackingListener;
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    public FileUploadUtil(Context context) {
        this.context = context;
        appController = AppController.getInstance();
        gson = new Gson();
    }




    public void uploadImage(Bitmap image,String name, Consignment con){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority("ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000")
                .appendPath("api")
                .appendPath("cons")
                .appendPath(String.valueOf(con.getId()))
                .appendPath("image")
                .appendPath("upload");
        Uri uri = builder.build();
        String json = gson.toJson(con);
        JSONObject jsonObject = null;
        String stringImage = getStringImage(image);
        try {
            jsonObject = new JSONObject(json);
            jsonObject.put(KEY_NAME, name);
            jsonObject.put(KEY_IMAGE, stringImage);
            jsonObject.put("cid", con.getId());
            jsonObject.put("conid", con.getConid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("park.jsonob", jsonObject.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, uri.toString(),jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Err.Res.UnsendOp.Unpark", error.toString());
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept","application/json");
                headers.put("Authorization", "Bearer " + Stormpath.accessToken());
                return headers;

            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    private String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}





