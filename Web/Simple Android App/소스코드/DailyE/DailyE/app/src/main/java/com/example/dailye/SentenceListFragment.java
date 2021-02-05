package com.example.dailye;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SentenceListFragment extends Fragment {

    SpeechItemAdapter speechItemAdapter;

    public static SentenceListFragment getInstance() {
        return new SentenceListFragment();
    }

    public SentenceListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_sentence_list, container, false);
        final MainActivity activity = (MainActivity) getActivity();
        speechItemAdapter = new SpeechItemAdapter(getContext());
        activity.makeSentenceItemList(speechItemAdapter);
        ListView listView = rootView.findViewById(R.id.sentence_list);
        listView.setAdapter(speechItemAdapter);
        return rootView;
    }

}
