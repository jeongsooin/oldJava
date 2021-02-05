package com.study.android.signin_signup_sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab1Fragment tab1Fragment = Tab1Fragment.getInstance();
                return tab1Fragment;
            case 1:
                Tab2Fragment tab2Fragment = Tab2Fragment.getInstance();
                return tab2Fragment;
            case 2:
                MapSettingFragment mapSettingFragment = MapSettingFragment.getInstance();
                return mapSettingFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
