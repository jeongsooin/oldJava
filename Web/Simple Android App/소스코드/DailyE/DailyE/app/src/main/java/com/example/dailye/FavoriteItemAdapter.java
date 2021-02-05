package com.example.dailye;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class FavoriteItemAdapter extends BaseAdapter {

    Context context;
    ArrayList<WordItem> items = new ArrayList<>();

    public FavoriteItemAdapter(Context context) {
        this.context = context;
    }

    public void addItem(WordItem item) {
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
        WordItemView view = null;
        if (convertView == null) {
            view = new WordItemView(context);
        } else {
            view = (WordItemView) convertView;
        }

        final WordItem item = items.get(position);
        view.setWord(item.getWord());
        view.setPronunciation(item.getPronunciation());
        view.setMeaning(item.getMeaning());
        return view;
    }
}
