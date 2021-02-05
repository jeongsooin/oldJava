package com.study.android.ex29_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    ProgressBar mProgress1;
    int mProgressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress1 = findViewById(R.id.progressBar1);
    }

    public void onBtnClicked(View v) {
        new CounterTask().execute(0);
    }

    class CounterTask extends AsyncTask<Integer, Integer, Integer> {
        protected void onPreExecute() { }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while (mProgressStatus < 100) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                mProgressStatus++;
                publishProgress(mProgressStatus);
            }
            return mProgressStatus;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgress1.setProgress(mProgressStatus);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            mProgress1.setProgress(mProgressStatus);
        }
    }
}
