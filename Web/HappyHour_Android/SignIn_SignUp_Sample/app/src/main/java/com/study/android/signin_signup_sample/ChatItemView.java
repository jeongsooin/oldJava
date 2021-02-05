package com.study.android.signin_signup_sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    LinearLayout layout;

    LayoutInflater inflater;

    public ChatItemView(Context context){
        super(context);

        inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setChatView(Boolean isUser, String message, String time) {


        if(isUser==true){ // 본인 글이면 오른쪽 정렬
            inflater.inflate(R.layout.chat_item_view_myself, this, true);
            textView1 = findViewById(R.id.message_myself);
            textView2 = findViewById(R.id.time_myself);
            textView1.setText(message);
            textView2.setText(time);
        } else { // 아니면 왼쪽 정렬
            inflater.inflate(R.layout.chat_item_view, this, true);
            textView1 = findViewById(R.id.chat_textView);
            textView2 = findViewById(R.id.chat_textView_time);
            textView1.setText(message);
            textView2.setText(time);
        }

    }

}
