package com.study.android.signin_signup_sample;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectDB_JSP extends AsyncTask<String, Void, String> {

    String sendMsg, receiveMsg, jsp, serverip;
    public static String ip= "192.168.0.5";
    //public static String ip ="192.168.219.122";

    ConnectDB_JSP(String sendMsg) { this.sendMsg = sendMsg; }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (sendMsg.equals("updateInfo")) {
            this.jsp = "updateInfo";
        } else if (sendMsg.equals("signIn")) {
            this.jsp = "signIn";
        } else if (sendMsg.equals("signUp")) {
            this.jsp = "signUp";
        } else if (sendMsg.equals("updatePassword")) {
            this.jsp = "updatePassword";
        } else if (sendMsg.equals("getReservationOptions")) {
            this.jsp = "getReservationOptions";
        } else if (sendMsg.equals("updateDeviceId")) {
            this.jsp = "updateDeviceId";
        } else if (sendMsg.equals("getDeviceId")) {
            this.jsp = "getDeviceId";
        } else if (sendMsg.equals("insertReservationData")) {
            this.jsp = "insertReservationData";
        } else if (sendMsg.equals("sendFCM")) {
            this.jsp = "sendFCM";
        } else if (sendMsg.equals("getMenuOptions")) {
            this.jsp = "getMenuOptions";
        }

        //serverip = "http://192.168.219.122:8081/" + jsp;
        serverip = "http://" + ip + ":8081/" + jsp;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;
            URL url = new URL(serverip);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            sendMsg = strings[0];

            osw.write(sendMsg);
            osw.flush();

            if(conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            } else {
                Log.i("*** 통신 결과 ", conn.getResponseCode() + " 에러 ***");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}
