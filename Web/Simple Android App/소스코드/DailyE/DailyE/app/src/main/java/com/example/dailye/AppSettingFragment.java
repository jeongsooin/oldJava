package com.example.dailye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AppSettingFragment extends Fragment {

    public static AppSettingFragment getInstance() {
        return new AppSettingFragment();
    }

    public AppSettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_app_setting, container, false);
        Button button = rootView.findViewById(R.id.settings_study_location);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.replaceFragment(MapSettingFragment.getInstance());
            }
        });
        return rootView;
    }
}
