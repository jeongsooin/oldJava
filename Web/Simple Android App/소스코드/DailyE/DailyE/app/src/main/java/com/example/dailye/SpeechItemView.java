package com.example.dailye;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SpeechItemView extends LinearLayout {

    private static final String TAG = "MainActivity";
    TextView textView1;
    TextView textView2;
    TextView textView3;

    ImageButton imageButton;

    SpeechRecognizer mRecognizer;

    public SpeechItemView(final Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sentence_item_view, this, true);

        textView1 = (TextView) findViewById(R.id.sentence_text);
        textView2 = (TextView) findViewById(R.id.sentence_meaning);
        textView3 = (TextView) findViewById(R.id.voice_text);
        imageButton = (ImageButton) findViewById(R.id.imageButton);

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        mRecognizer.setRecognitionListener(recognitionListener);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "음성 검색");
                    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                    mRecognizer.startListening(intent);
                } catch (ActivityNotFoundException e) {
                    Log.d(TAG, "ActivityNotFoundException");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setSentence(String sentence) { textView1.setText(sentence); }

    public void setMeaning(String meaning) { textView2.setText(meaning); }

    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float v) {
        }

        @Override
        public void onBufferReceived(byte[] bytes) {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onError(int i) {
            textView3.setText("너무 늦게 말하면 오류가 발생합니다!");
        }

        @Override
        public void onResults(Bundle bundle) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = bundle.getStringArrayList(key);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            textView3.setText(rs[0]);
        }

        @Override
        public void onPartialResults(Bundle bundle) {
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
        }
    };

}
