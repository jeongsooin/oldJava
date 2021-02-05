package com.study.android.ex53_admobex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 초기화
        String bannerid = getResources().getString(R.string.ad_unit_id_1);
        MobileAds.initialize(this, bannerid);

        // 테스트 광고 부르기
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice("B3A9C538F74E7B29F901509F22BD5A6B") // 실제 광고 넣을 때는 여기 주석처리
                .build();
        mAdView.loadAd(adRequest);
    }

    public void hideAd(View v) {
        if (mAdView.isEnabled())
            mAdView.setEnabled(false);
        if (mAdView.getVisibility() != View.INVISIBLE )
            mAdView.setVisibility(View.INVISIBLE);
    }

    public void showAd(View v) {
        if (!mAdView.isEnabled())
            mAdView.setEnabled(true);
        if (mAdView.getVisibility() != View.VISIBLE )
            mAdView.setVisibility(View.VISIBLE);
    }
}
