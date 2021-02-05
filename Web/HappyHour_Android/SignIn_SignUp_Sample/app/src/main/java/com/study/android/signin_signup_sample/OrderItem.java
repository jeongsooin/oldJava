package com.study.android.signin_signup_sample;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderItem implements Comparable<OrderItem> {
    private static final String TAG = "lecture";

    private int sequence;
    private int food_table;
    private String food_name;
    private int food_amount;
    private int food_status;
    private String expd_time;

    public OrderItem(){}

    public OrderItem( int food_table, String food_name, int food_amount, int food_status, String expd_time) {
        this.food_table = food_table;
        this.food_name = food_name;
        this.food_amount = food_amount;
        this.food_status = food_status;
        this.expd_time = expd_time;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getFood_table() {
        return food_table;
    }

    public void setFood_table(int food_table) {
        this.food_table = food_table;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getFood_amount() {
        return food_amount;
    }

    public void setFood_amount(int food_amount) {
        this.food_amount = food_amount;
    }

    public int getFood_status() {
        return food_status;
    }

    public void setFood_status(int food_status) {
        this.food_status = food_status;
    }

    public String getExpd_time() {
        return expd_time;
    }

    public void setExpd_time(String expd_time) {
        this.expd_time = expd_time;
    }


    // 가장 빠른 시간이 리스트 위로 오도록
    @Override
    public int compareTo(OrderItem item) {
        DateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(expd_time);
            date2 = format.parse(item.getExpd_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date1.after(date2)) {
            return 1;
        } else if(date1.before(date2)){
            return -1;
        }
        return 0;
    }
}
