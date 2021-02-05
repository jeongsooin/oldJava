package com.example.dailye;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class WordListFragment extends Fragment {
    public static WordListFragment getInstance() {
        return new WordListFragment();
    }

    WordItemAdapter adapter;

    public WordListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_word_list, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.word_listView);
        listView.setAdapter(adapter);
        return rootView;
    }
}
