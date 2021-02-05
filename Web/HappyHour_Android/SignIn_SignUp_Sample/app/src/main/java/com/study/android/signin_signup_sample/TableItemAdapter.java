package com.study.android.signin_signup_sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class TableItemAdapter extends BaseAdapter {

    Context context;
    ArrayList<TableItem> items = new ArrayList<>();

    public TableItemAdapter(Context context) { this.context = context; }

    public void addItem(TableItem item) { items.add(item); }

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

        TableListItemView view = null;
        view = new TableListItemView(context);

        TableItem item = items.get(position);
        view.setSelectableTableText("테이블 " + item.getTableNumber());

        return view;
    }
}
