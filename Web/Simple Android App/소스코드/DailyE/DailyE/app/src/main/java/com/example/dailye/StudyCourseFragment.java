package com.example.dailye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StudyCourseFragment extends Fragment {

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    ViewGroup rootView;

    public static StudyCourseFragment getInstance() {
        return new StudyCourseFragment();
    }

    public StudyCourseFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final MainActivity activity = (MainActivity) getActivity();
        rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_study_course, container, false);

        button1 = (Button) rootView.findViewById(R.id.word_level1);
        button2 = (Button) rootView.findViewById(R.id.word_level2);
        button3 = (Button) rootView.findViewById(R.id.word_level3);
        button4 = (Button) rootView.findViewById(R.id.word_level4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.replaceFragment(DailyCourseFragment.getInstance());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.replaceFragment(DailyCourseFragment.getInstance());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.replaceFragment(DailyCourseFragment.getInstance());
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.replaceFragment(DailyCourseFragment.getInstance());
            }
        });

        return rootView;

    }

}
