package com.study.android.signin_signup_sample;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Timestamp;

public class BoardContentItemView extends LinearLayout {
    private static final String TAG = "lecture";

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    TextView replyText;

    ImageView imageView;
    Bitmap bitmap;

    LayoutInflater inflater;

    public BoardContentItemView(Context context){
        super(context);
        inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setbTitle(String bTitle, String language){

        if(language.equals("한국어")){
            textView1.setText(bTitle);
        } else {
            Translation translation = new Translation();
            try {
                textView1.setText(translation.execute(bTitle,language).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setbName(String bName, String language){

        if(language.equals("한국어")){
            textView2.setText(bName);
        } else {
            Translation translation = new Translation();
            try {
                textView2.setText(translation.execute(bName,language).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setbDate(Timestamp bDate){
        textView3.setText(bDate+"");
    }

    public void setbContent(String bContent, String language) {
        if(language.equals("한국어")){
            textView4.setText(bContent);
        } else {
            Translation translation = new Translation();
            try {
                textView4.setText(translation.execute(bContent,language).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setImage(){ // 답글 화살표 이미지 삽입
        imageView.setImageResource(R.drawable.rightarrow);
    }



    public void setImageView(String bExtension) {
//        if(bExtension != null || !bExtension.equals("")){
//            String ip ="192.168.219.127"; //자신의 IP번호
//            String serverip = "http://"+ip+":8081/upload/"+ bExtension;
//
//            // 콘솔에 오류나와서 if문에 넣음
//            // java.io.FileNotFoundException: http://192.168.219.127:8081/upload/null
//            if(!serverip.equals("http://192.168.219.127:8081/upload/null")){
//                new ReviewItemView.LoadImage().execute(serverip);
//            }
//
//        } else {
//
//        }
    }

    public void setContent() {
        inflater.inflate(R.layout.activity_board_content_item_view, this, true);
        textView1 = findViewById(R.id.title_TextView);
        textView2 = findViewById(R.id.name_TextView);
        textView3 = findViewById(R.id.date_TextView);
        textView4 = findViewById(R.id.content_TextView);
        replyText = findViewById(R.id.reply_text);
    }
}
