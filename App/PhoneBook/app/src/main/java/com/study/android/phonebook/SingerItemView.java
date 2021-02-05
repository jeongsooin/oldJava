package com.study.android.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    Button button1;
    Button button2;
    ImageView imageView1;
    LayoutInflater inflater;

    public SingerItemView(Context context, Boolean isFemale) {
        super(context);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (isFemale) {
            inflater.inflate(R.layout.singer_item_view2, this, true);
            textView1 = findViewById(R.id.textView1);
            textView2 = findViewById(R.id.textView2);
            button1 = findViewById(R.id.button1);
            button2 = findViewById(R.id.button2);
            imageView1 = findViewById(R.id.imageView);
        } else {
            inflater.inflate(R.layout.singer_item_view1, this, true);
            textView1 = findViewById(R.id.textView1);
            textView2 = findViewById(R.id.textView2);
            button1 = findViewById(R.id.button1);
            button2 = findViewById(R.id.button2);
            imageView1 = findViewById(R.id.imageView);
        }
    }

    public void setName(String name) { textView1.setText(name); }

    public void setTel(String telnum) { textView2.setText(telnum); }

    public void setImage(int imgNum) { imageView1.setImageResource(imgNum); }

}
