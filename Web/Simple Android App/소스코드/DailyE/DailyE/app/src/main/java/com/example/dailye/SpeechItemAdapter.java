package com.example.dailye;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class SpeechItemAdapter extends BaseAdapter {

    Context context;
    ArrayList<SpeechItem> items = new ArrayList<>();

    public SpeechItemAdapter(Context context) {
        this.context = context;
    }

    public void addItem(SpeechItem item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpeechItemView view = null;
        if (convertView == null) {
            view = new SpeechItemView(context);
        } else {
            view = (SpeechItemView) convertView;
        }

        final SpeechItem item = items.get(position);
        view.setSentence(item.getSentence());
        view.setMeaning(item.getMeaning());
        return view;
    }
}
