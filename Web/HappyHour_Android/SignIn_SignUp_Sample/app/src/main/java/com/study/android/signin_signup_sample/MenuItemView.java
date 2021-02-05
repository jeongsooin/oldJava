package com.study.android.signin_signup_sample;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuItemView extends LinearLayout {

    TextView tv_menu_name;
    TextView tv_menu_description;
    TextView tv_menu_qty;
    TextView tv_select_qty;
    ImageButton minusButton;
    ImageButton plusButton;

    int num = 0;

    public MenuItemView(Context context) {
        super(context);
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_item_view, this, true);

        tv_menu_name = findViewById(R.id.tv_menu_name);
        tv_menu_description = findViewById(R.id.tv_menu_description);
        tv_menu_qty = findViewById(R.id.tv_menu_qty);
        tv_select_qty = findViewById(R.id.tv_select_qty);
        minusButton = findViewById(R.id.minusButton);
        plusButton = findViewById(R.id.plustButton);

        minusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 0)
                    tv_select_qty.setText((num - 1));
            }
        });

        plusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_select_qty.setText((num + 1));
            }
        });
    }

    public void setTv_menu_name(String menu_name) { tv_menu_name.setText(menu_name); }
    public void setTv_menu_description(String description) { tv_menu_description.setText(description); }
    public void setTv_menu_qty(int qty) { tv_menu_qty.setText(qty +" 인"); }
    public int getNum() { return this.num; }
}
