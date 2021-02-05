package com.study.android.signin_signup_sample;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendFCM extends AsyncTask<String, Void, String> {

    private final static String TAG = "lecture";

    String ApiKey = "AIzaSyBFRevBtRq0gjSdfKn_QbTXiQWimaOy0nU";
    String fcmURL = "https://fcm.googleapis.com/fcm/send";

    String receiveMsg;

    @Override
    protected String doInBackground(String... strings) {

        receiveMsg = "";

        try {

            URL url = new URL(fcmURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + ApiKey);
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject json = new JSONObject();

            JSONObject noti = new JSONObject();
            noti.put("title", strings[1]);
            noti.put("body", strings[2]);

            JSONObject data = new JSONObject();
            data.put("message", strings[3]);

            json.put("to", strings[0]);
            json.put("notification", noti);
            json.put("data", data);

            try {
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(json.toString());
                wr.flush();

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;
                System.out.println("Output from Server .... \n");
                while((output=br.readLine()) != null) {
                    System.out.println(output);
                    receiveMsg = receiveMsg + output;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return receiveMsg;
    }
}