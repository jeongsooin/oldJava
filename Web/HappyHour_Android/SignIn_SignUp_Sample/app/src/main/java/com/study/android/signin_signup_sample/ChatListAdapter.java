package com.study.android.signin_signup_sample;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ChatListAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Activity activity;
    ArrayList<ChatDTO> items = new ArrayList<>();

    String user_name;

    public ChatListAdapter(Activity activity){
        this.activity = activity;
    }

    public void addItem(ChatDTO item) {
        items.add(item);
        Collections.sort(items);
    }

    // 리스트에서 제거
    public void removeItem(String ID) {
        Iterator it = items.iterator();
        for(int i=0;it.hasNext();i++){
            ChatDTO item = (ChatDTO) it.next(); // items 안에 있는 item
            if(item.getRoomName().equals(ID)){
                items.remove(i);
                break;
            }
        }
    }

    @Override
    public int getCount() {return items.size();}

    @Override
    public Object getItem(int position) {return items.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ChatListItemView view = null;
        if(convertView == null){
            view = new ChatListItemView(activity.getApplicationContext());
        }else {
            view = (ChatListItemView) convertView;
        }
        ChatDTO item = items.get(position);

        view.setChatView(item.getCustomerName(),
                item.getMessage(), item.getChatTime());

        view.setReply_status(item.getUserName().equals(item.getCustomerName()));

        return view;
    }
}