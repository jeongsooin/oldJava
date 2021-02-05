package com.study.android.signin_signup_sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardListItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    LayoutInflater inflater;

    public BoardListItemView(Context context){
        super(context);

        inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setbName(String bName, String language){
        if(language.equals("한국어")){
            textView1.setText(bName);
        } else {
            Translation translation = new Translation();
            try {
                textView1.setText(translation.execute(bName,language).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setbDate(Timestamp bDate){
        String date = new SimpleDateFormat("yyyy/MM/dd").format(bDate);
        textView2.setText(date);
    }

    public void setbTitle(String bTitle, String language) {
        if(language.equals("한국어")){
            textView3.setText(bTitle);
        } else {
            Translation translation = new Translation();
            try {
                textView3.setText(translation.execute(bTitle, language).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void setReplyCheck(String replyStatus, String language) {
        textView4.setVisibility(VISIBLE);

        if(language.equals("한국어")){
            textView4.setText(replyStatus);
        } else {
            Translation translation = new Translation();
            try {
                textView4.setText(translation.execute(replyStatus, language).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setItemView() {
        inflater.inflate(R.layout.board_list_item_view, this, true);
        textView1 = findViewById(R.id.ID_textView);
        textView2 = findViewById(R.id.bDate_textView);
        textView3 = findViewById(R.id.bTitle_textView);
        textView4 = findViewById(R.id.reply_check);
    }

    public void setSecretItemView(String language) {
        inflater.inflate(R.layout.board_list_secret_item_view, this, true);
        textView1 = findViewById(R.id.secret_textView);

        if(language.equals("한국어")){
            textView1.setText("비밀 글입니다.");
        } else {
            Translation translation = new Translation();
            try {
                textView1.setText(translation.execute("비밀 글입니다.",language).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
