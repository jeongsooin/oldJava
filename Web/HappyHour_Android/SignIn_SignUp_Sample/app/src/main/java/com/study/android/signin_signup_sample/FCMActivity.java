package com.study.android.signin_signup_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class FCMActivity extends AppCompatActivity {

    List<String> deviceIdList = MainActivity.deviceIdList;

    EditText etNotiTitle;
    EditText etNotibody;

    String notiTitle;
    String notiBody;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm);

        etNotiTitle = findViewById(R.id.etNotiTitle);
        etNotibody = findViewById(R.id.etNotibody);

        Button button = findViewById(R.id.sendFCM);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){

                String title = etNotiTitle.getText().toString();
                if(title.equals("") || title==null) {
                    Toast.makeText(getApplicationContext(), "알림 제목을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String body = etNotibody.getText().toString();
                if(body.equals("") || body ==null) {
                    Toast.makeText(getApplicationContext(), "알림 내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                notiTitle = etNotiTitle.getText().toString();
                notiBody = etNotibody.getText().toString();

                for (int i = 0; i < deviceIdList.size(); i++) {
                    sendFCM(deviceIdList.get(i), notiTitle, notiBody);
                }
            }
        });
    }

    public void sendFCM(String deviceId, String title, String body) {
        try {
            String sendInfo = "sendFCM";

            String sendMsg = "to=" + deviceId + "&title=" + title + "&body=" + body;

            resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

            if (resultString != null) {
                String[] splitResult = resultString.split(" ");

                if (splitResult[0].contains("SUCCESS")) {
                    Toast.makeText(getApplicationContext(), "알림을 전송하였습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("##ERROR##" , splitResult[1] +  " " + splitResult[2]);
                }
            } else {
                Log.i("Server Connection", "ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
