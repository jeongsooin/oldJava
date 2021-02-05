package com.study.android.ex31_httpex1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView tvhtml;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_get);

        tvhtml = findViewById(R.id.tvHtml1);
        webView = findViewById(R.id.webView1);
    }

    public void onBtnGet (View v) {
        String sUrl = getString(R.string.server_addr) + "/JspInServer/login.jsp";
        GetAction myGetAct = new GetAction();
        myGetAct.execute(sUrl);
    }

    public void onBtnFinish(View v) { finish(); }

    class GetAction extends AsyncTask<String, Integer, String> {

        protected void onPreExecute() { }

        protected String doInBackground(String... value) {
            String sOutput = "";

            StringBuilder output = new StringBuilder();
            try {
                URL url = new URL(value[0].toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                Log.d(TAG, "sUrl:"+value[0].toString());

                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    int resCode = conn.getResponseCode();
                    if( resCode == HttpURLConnection.HTTP_OK) {
                        Log.d(TAG, "aaa");
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        String line = null;
                        while (true) {
                            line = reader.readLine();
                            if (line == null) { break;}
                            output.append(line + "\n");
                        }
                        reader.close();
                        conn.disconnect();

                        Log.d(TAG, output.toString());
                        sOutput = output.toString();
                    } else {
                        Log.d(TAG, "bbb - resCode : " + resCode);
                    }
                } else {
                    Log.d(TAG, "ccc");
                }

            } catch (Exception ex) {
                Log.d(TAG, "Exception in processing response.", ex);
            }
            return sOutput;
        }

        protected void onProgressUpdate(Integer... value) {
        }

        protected void onPostExecute(String result) {
            tvhtml.setText(result);
            webView.loadData(result, "text/html; charset=UTF-8", null);
        }
    }
}
