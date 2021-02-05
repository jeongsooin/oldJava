package com.study.android.signin_signup_sample;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Activity activity;
    ArrayList<ChatDTO> items = new ArrayList<>();

    String user_name;

    public ChatAdapter(Activity activity, String user_name){
        this.activity = activity;
        this.user_name = user_name;
    }

    public void addItem(ChatDTO item) {
        items.add(item);
    }

    @Override
    public int getCount() {return items.size();}

    @Override
    public Object getItem(int position) {return items.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ChatItemView view = new ChatItemView(activity.getApplicationContext());
        ChatDTO item = items.get(position);

        view.setChatView(item.getUserName().equals(user_name),
                 item.getMessage(), item.getChatTime());



        return view;
    }
}