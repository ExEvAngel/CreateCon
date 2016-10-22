package com.angel.createcon;

import android.os.AsyncTask;
import android.util.Log;

import com.angel.createcon.Listeners.ConsignmentLoadListener;
import com.angel.createcon.app.AppController;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import okhttp3.OkHttpClient;
import static java.util.Calendar.DATE;

/**
 * Created by Angel on 10/16/2016.
 */

public class TaskLoadConsignments extends AsyncTask<Void, Void, ArrayList<Consignment>>{

    private ConsignmentLoadListener myComponent;
    private AppController appController;
    ArrayList<Consignment> arrayList = new ArrayList<>();
    Gson gson;

    int  nopiece,conid;
    double value;
    int id;
    String payterm, custref, service, opt, description,currency, userid;
    String sendacc, sendname, sendaddress, sendcity, sendpostcode, sendcountry, sendcontactname, sendcontactno;
    String recacc, recname, recaddress, reccity, recpostcode, reccountry, reccontactname, reccontactno;
    boolean dg, parked;
    Date creationdate;

    String json_url = "http://ec2-52-64-220-153.ap-southeast-2.compute.amazonaws.com:3000/api/cons";


    public TaskLoadConsignments(ConsignmentLoadListener myComponent){
        this.myComponent = myComponent;
        appController  = AppController.getInstance();

    }
    @Override
    protected ArrayList<Consignment> doInBackground(Void... params) {
        String tag_json_obj = "json_obj_req";
        String serverData = null;

        try {
            OkHttpClient client = new OkHttpClient();
            URL url  = new URL(json_url);
            okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();
            okhttp3.Response responses = null;

            try {
                responses = client.newCall(request).execute();
                serverData = responses.body().string();
            }  catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e){
            e.printStackTrace();
        }


        // Http Request Code end
        // Json Parsing Code Start
        try{

            JSONArray jsonArray = new JSONArray(serverData);
            Log.d("PARSE", "JSONARRAY: "+jsonArray.toString());
            for (int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObjectCon = jsonArray.getJSONObject(i);
                String jsonString = jsonObjectCon.toString();
                Log.d("PARSE", "jsonObjectCon: "+jsonString);

                id =jsonObjectCon.getInt("id");
                conid =jsonObjectCon.getInt("conid");
                payterm =jsonObjectCon.getString("payterm");
                custref = jsonObjectCon.getString("custref");
                sendacc =jsonObjectCon.getString("sendacc");
                sendname =jsonObjectCon.getString("sendname");
                sendaddress =jsonObjectCon.getString("sendaddress");
                sendcity =jsonObjectCon.getString("sendcity");
                sendpostcode =jsonObjectCon.getString("sendpostcode");
                sendcountry=jsonObjectCon.getString("sendcountry");
                sendcontactname =jsonObjectCon.getString("sendcontactname");
                sendcontactno=jsonObjectCon.getString("sendcontactno");
                recacc=jsonObjectCon.getString("recacc");
                recname=jsonObjectCon.getString("recname");
                recaddress =jsonObjectCon.getString("recaddress");
                reccity= jsonObjectCon.getString("reccity");
                recpostcode=jsonObjectCon.getString("recpostcode");
                reccountry=jsonObjectCon.getString("reccountry");
                reccontactname=jsonObjectCon.getString("reccontactname");
                reccontactno=jsonObjectCon.getString("reccontactno");
                service=jsonObjectCon.getString("service");
                opt=jsonObjectCon.getString("opt");
                dg=jsonObjectCon.getBoolean("dg");
                nopiece=jsonObjectCon.getInt("nopiece");
                description=jsonObjectCon.getString("description");
                value=jsonObjectCon.getDouble("value");
                currency=jsonObjectCon.getString("currency");
                userid=jsonObjectCon.getString("userid");
                parked=jsonObjectCon.getBoolean("parked");
                SimpleDateFormat ft = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a",Locale.ENGLISH);
                creationdate =ft.parse(jsonObjectCon.getString("creationdate"));


                Consignment con = new Consignment(id,conid, payterm, custref,
                        sendacc, sendname, sendaddress, sendcity, sendpostcode, sendcountry, sendcontactname, sendcontactno,
                        recacc, recname, recaddress, reccity, recpostcode, reccountry, reccontactname, reccontactno,
                        service, opt, dg, nopiece, description,  value, currency, userid, parked,creationdate );

                arrayList.add(con);
                Log.d("PARSE", "Name: "+jsonObjectCon.getString("sendname"));

                Log.d("PARSE", "ObjectName: "+con.getSendname());
            }
        } catch (JSONException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
        Log.d("CREATE", "Name: "+arrayList.get(0).getSendname());

        Log.d("CREATE", "SIZE: "+arrayList.size());*/
        return arrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Consignment> arrayList) {
        if(myComponent!=null){
            myComponent.onConsignmentLoaded(arrayList);
        }
    }
}
