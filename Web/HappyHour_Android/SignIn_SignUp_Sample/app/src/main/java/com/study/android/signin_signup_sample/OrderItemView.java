package com.study.android.signin_signup_sample;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Button button;

    public OrderItemView(Context context){
        super(context);



        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.order_item_view, this, true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        button = findViewById(R.id.button1);
    }

    public void setFood_table(int food_table){
        textView1.setText(food_table+"번 테이블");
    }

    public void setFood_name(String food_name){
        textView2.setText(food_name);
    }

    public void setFood_amount(int food_amount){
        textView3.setText(food_amount+"개");
    }

    public void setExpd_time(String expd_time) {
        textView4.setText(expd_time);
    }


    public void setStatus(int food_status) {
        String status = "";
        switch (food_status) {
            case 1:
                status = "주문";
                break;
            case 2:
                status = "요리중";
                break;
            case 3:
                status = "요리완료";
        }

        button.setText(status);
    }
}
