package com.example.dailye;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DailyCourseFragment extends Fragment {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    WordItemAdapter adapter1;
    WordItemAdapter adapter2;
    WordItemAdapter adapter3;
    WordItemAdapter adapter4;

    public static DailyCourseFragment getInstance() {
        return new DailyCourseFragment();
    }

    public DailyCourseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_daily_course, container, false);
        final MainActivity activity = (MainActivity) getActivity();
        button1 = rootView.findViewById(R.id.button_day1);
        button2 = rootView.findViewById(R.id.button_day2);
        button3 = rootView.findViewById(R.id.button_day3);
        button4 = rootView.findViewById(R.id.button_day4);

        adapter1 = new WordItemAdapter(getContext());
        adapter2 = new WordItemAdapter(getContext());
        adapter3 = new WordItemAdapter(getContext());
        adapter4 = new WordItemAdapter(getContext());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.makeDailyWordItemList(1, 25, adapter1);
                WordListFragment wordListFragment = WordListFragment.getInstance();
                wordListFragment.adapter = adapter1;
                activity.replaceFragment(wordListFragment);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.makeDailyWordItemList(26, 50, adapter2);
                WordListFragment wordListFragment = WordListFragment.getInstance();
                wordListFragment.adapter = adapter2;
                activity.replaceFragment(wordListFragment);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.makeDailyWordItemList(51, 75, adapter3);
                WordListFragment wordListFragment = WordListFragment.getInstance();
                wordListFragment.adapter = adapter3;
                activity.replaceFragment(wordListFragment);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.makeDailyWordItemList(76, 100, adapter4);
                WordListFragment wordListFragment = WordListFragment.getInstance();
                wordListFragment.adapter = adapter4;
                activity.replaceFragment(wordListFragment);
            }
        });
        return rootView;
    }
}
