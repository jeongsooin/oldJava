package com.study.android.practice_exceltodb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class WordListAdapter extends BaseAdapter  {
    Context context;
    ArrayList<WordItem> items = new ArrayList<>();

    public WordListAdapter(Context context) {
        this.context = context;
    }

    public void addItem(WordItem item) { items.add(item); }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WordListView view = null;
        if (convertView == null) {
            view = new WordListView(context);
        } else {
            view = (WordListView) convertView;
        }

        final WordItem item = items.get(position);
        view.setWord(item.getWord());
        view.setMeaning(item.getMeaning());
        view.setDone(item.getDone());
        view.setFavorite(item.getFavorite());

        return view;
    }
}

