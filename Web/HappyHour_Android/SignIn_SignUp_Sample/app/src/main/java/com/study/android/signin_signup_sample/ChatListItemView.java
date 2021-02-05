package com.study.android.signin_signup_sample;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatListItemView  extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView reply_status_textView;

    LayoutInflater inflater;

    public ChatListItemView(Context context){
        super(context);

        inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chat_list_view, this, true);
    }

    public void setChatView(String name, String message, String time) {
            textView1 = findViewById(R.id.chat_list_name);
            textView2 = findViewById(R.id.chat_list_content);
            textView3 = findViewById(R.id.chat_list_time);
            textView1.setText(name);
            textView2.setText(message);
            textView3.setText(time);
    }

    public void setReply_status(Boolean reply_status) {
        reply_status_textView = findViewById(R.id.reply_status_textView);
        if(reply_status == true) {
            reply_status_textView.setText("답변 대기");
            reply_status_textView.setBackgroundResource(R.drawable.red_rounded);
            reply_status_textView.setTextColor(Color.WHITE);
        } else {
            reply_status_textView.setText("답변 완료");
        }
    }

}