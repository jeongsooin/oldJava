package com.study.android.signin_signup_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyPageFragment extends Fragment {

    public static MyPageFragment getInstance() {
        return new MyPageFragment();
    }

    public MyPageFragment() { }

    public static Button buttonRSVInfo;
    public static Button buttonMyInfo;
    public static Button buttonSignIn;
    public static Button buttonSignOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_my_page, container, false);

        buttonRSVInfo = rootView.findViewById(R.id.buttonRSVInfo);
        buttonMyInfo = rootView.findViewById(R.id.buttonMyInfo);
        buttonSignOut = rootView.findViewById(R.id.buttonSignOut);
        buttonSignIn = rootView.findViewById(R.id.buttonSignIn);

        if (MainActivity.loginStatus.equals("NN")) {
            buttonRSVInfo.setVisibility(View.GONE);
            buttonMyInfo.setVisibility(View.GONE);
            buttonSignIn.setVisibility(View.VISIBLE);
            buttonSignOut.setVisibility(View.GONE);
        } else if (MainActivity.loginStatus.equals("YN")) {
            buttonRSVInfo.setVisibility(View.VISIBLE);
            buttonMyInfo.setVisibility(View.VISIBLE);
            buttonSignIn.setVisibility(View.GONE);
            buttonSignOut.setVisibility(View.VISIBLE);
        } else {
            buttonRSVInfo.setVisibility(View.GONE);
            buttonMyInfo.setVisibility(View.GONE);
            buttonSignIn.setVisibility(View.VISIBLE);
            buttonSignOut.setVisibility(View.GONE);
        }

        buttonRSVInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ...
            }
        });

        buttonMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyPageActivity.class);
                startActivity(intent);
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSignIn.setVisibility(View.VISIBLE);
                buttonSignOut.setVisibility(View.GONE);
                MainActivity.memberDTO = null;
                MainActivity.setNavigationHeader("손님", "반갑습니다!");
                replaceFragment(MainFragment.getInstance());
            }
        });

        return rootView;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.myPageFragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}