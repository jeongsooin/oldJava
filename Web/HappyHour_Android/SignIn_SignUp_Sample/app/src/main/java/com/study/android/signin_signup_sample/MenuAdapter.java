package com.study.android.signin_signup_sample;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    ArrayList<MenuItem> items = new ArrayList<>();
    Context context;

    public MenuAdapter(Context context) {
        this.context = context;
    }

    public void addItem(MenuItem item){
        items.add(item);
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        MenuItemView view = null;
        view = new MenuItemView(this.context);

        MenuItem item = items.get(position);
        view.setTv_menu_name(item.getMenu_name());
        view.setTv_menu_description(item.getMenu_description());
        view.setTv_menu_qty(item.getMenu_qty());

        return view;
    }

}
