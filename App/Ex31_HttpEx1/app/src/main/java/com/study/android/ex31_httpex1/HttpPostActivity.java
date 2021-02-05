package com.study.android.ex31_httpex1;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HttpPostActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView tvHtml2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_post);

        tvHtml2 = findViewById(R.id.tvHtml2);
    }

    public void onBtnFinish(View v) { finish(); }

    public void onBtnPost(View v) {
        tvHtml2.setText("");

        String sUrl = getString(R.string.server_addr) + "/JspInServer/loginOk.jsp";

        try {
            ContentValues values = new ContentValues();
            values.put("userid", "abcde");
            values.put("userpwd", "1234");

            NetworkTask networkTask = new NetworkTask(sUrl, values);
            networkTask.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvHtml2.setText(s);
        }
    }
}
