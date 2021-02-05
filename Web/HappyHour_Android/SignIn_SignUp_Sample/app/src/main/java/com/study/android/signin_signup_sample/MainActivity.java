package com.study.android.signin_signup_sample;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static MainActivity mainActivity;
    public static MainActivity getInstance() { return mainActivity; };
    private static final String TAG = "MainActivity";

    public static MemberDTO memberDTO = MemberDTO.getInstance();
    public static List<String> deviceIdList = new ArrayList<>();

    public static TextView nav_header_userName;
    public static TextView nav_header_userEmail;
    public static Menu menu;
    public static NavigationView navigationView;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static String loginStatus = "NN";
    public static String isAdmin = "NO";

    String resultString;

    public static GPSListener gpsListener = GPSListener.getInstance();
    public static LocationManager locationManager;
    public static NotificationManager notificationManager;

    // NN(로그인 X), YN(회원가입 로그인), YS(SNS 로그인)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* -------------------------------------- */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = MainActivity.this;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // NavigationView
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        setNavigationMenu(isAdmin);
        // Navigation Header
        View nav_header_view = navigationView.getHeaderView(0);
        nav_header_userName = (TextView) nav_header_view.findViewById(R.id.nav_header_userName);
        nav_header_userEmail = (TextView) nav_header_view.findViewById(R.id.nav_header_userEmail);

//        String bannerid = "ca-app-pub-6337194123340322/1807641895";
//        MobileAds.initialize(getApplicationContext(), bannerid);
//
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("B3A9C538F74E7B29F901509F22BD5A6B").build();
//        mAdView.loadAd(adRequest);

        /* -------------------------------------- */
        getDeviceId();
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    public static void setNavigationMenu(String isAdmin) {
        if (isAdmin.equals("NO")){
            menu.setGroupVisible(R.id.menu_admin, false);
            menu.setGroupVisible(R.id.menu_user, true);
        } else if(isAdmin.equals("YES")) {
            menu.setGroupVisible(R.id.menu_user, false);
            menu.setGroupVisible(R.id.menu_admin, true);
        } else {
            menu.setGroupVisible(R.id.menu_admin, false);
            menu.setGroupVisible(R.id.menu_user, true);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_myPage) {
            replaceFragment(MyPageFragment.getInstance());
        } else if (id == R.id.nav_myPage_admin) {
            replaceFragment(MyPageFragment.getInstance());
        } else if (id == R.id.nav_kitchen) {
            if (!loginStatus.contains("Y")) {
                Toast.makeText(getApplicationContext(), "로그인 후 이용하실 수 있습니다.", Toast.LENGTH_LONG).show();
                return false;
            }
            replaceFragment(KitchenFragment.getInstance());
        } else if (id == R.id.nav_notice) {
            if (!loginStatus.contains("Y")) {
                Toast.makeText(getApplicationContext(), "로그인 후 이용하실 수 있습니다.", Toast.LENGTH_LONG).show();
                return false;
            }
            Fragment fragment = new BoardFragment();
            Bundle bundle = new Bundle(4); // 파라미터는 전달할 데이터 개수
            bundle.putString("bMenu", "공지사항");
            bundle.putString("ID", memberDTO.getEmail());
            bundle.putString("name", memberDTO.getName());
            bundle.putString("is_admin", memberDTO.getIsadmin());
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        } else if (id == R.id.nav_notice_user) {
            if (!loginStatus.contains("Y")) {
                Toast.makeText(getApplicationContext(), "로그인 후 이용하실 수 있습니다.", Toast.LENGTH_LONG).show();
                return false;
            }
            Fragment fragment = new BoardFragment();
            Bundle bundle = new Bundle(4); // 파라미터는 전달할 데이터 개수
            bundle.putString("bMenu", "공지사항");
            bundle.putString("ID", memberDTO.getEmail());
            bundle.putString("name", memberDTO.getName());
            bundle.putString("is_admin", memberDTO.getIsadmin());
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        }else if (id == R.id.nav_inquiry) {
            if (!loginStatus.contains("Y")) {
                Toast.makeText(getApplicationContext(), "로그인 후 이용하실 수 있습니다.", Toast.LENGTH_LONG).show();
                return false;
            }
            Fragment fragment = new BoardFragment();
            Bundle bundle = new Bundle(4); // 파라미터는 전달할 데이터 개수
            bundle.putString("bMenu", "문의사항");
            bundle.putString("ID", memberDTO.getEmail());
            bundle.putString("name", memberDTO.getName());
            bundle.putString("is_admin", memberDTO.getIsadmin());
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        } else if (id == R.id.nav_inquiry_user) {
            if (!loginStatus.contains("Y")) {
                Toast.makeText(getApplicationContext(), "로그인 후 이용하실 수 있습니다.", Toast.LENGTH_LONG).show();
                return false;
            }
            Fragment fragment = new BoardFragment();
            Bundle bundle = new Bundle(4); // 파라미터는 전달할 데이터 개수
            bundle.putString("bMenu", "문의사항");
            bundle.putString("ID", memberDTO.getEmail());
            bundle.putString("name", memberDTO.getName());
            bundle.putString("is_admin", memberDTO.getIsadmin());
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        } else if (id == R.id.nav_review_user) {
            if (!loginStatus.contains("Y")) {
                Toast.makeText(getApplicationContext(), "로그인 후 이용하실 수 있습니다.", Toast.LENGTH_LONG).show();
                return false;
            }
            Fragment fragment = new ReviewFragment();
            Bundle bundle = new Bundle(3); // 파라미터는 전달할 데이터 개수
            bundle.putString("ID", memberDTO.getEmail());
            bundle.putString("name", memberDTO.getName());
            bundle.putString("is_admin", memberDTO.getIsadmin());
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        } else if (id == R.id.nav_review) {
            if (!loginStatus.contains("Y")) {
                Toast.makeText(getApplicationContext(), "로그인 후 이용하실 수 있습니다.", Toast.LENGTH_LONG).show();
                return false;
            }
            Fragment fragment = new ReviewFragment();
            Bundle bundle = new Bundle(3); // 파라미터는 전달할 데이터 개수
            bundle.putString("ID", memberDTO.getEmail());
            bundle.putString("name", memberDTO.getName());
            bundle.putString("is_admin", memberDTO.getIsadmin());
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        }  else if (id == R.id.nav_view) {
//            Intent intent = new Intent(getApplicationContext(), KitchenFragment.class);
//            startActivity(intent);
        } else if (id == R.id.nav_chat_user) {
            if (!loginStatus.contains("Y")) {
                Toast.makeText(getApplicationContext(), "로그인 후 이용하실 수 있습니다.", Toast.LENGTH_LONG).show();
                return false;
            }

            String ISADMIN = memberDTO.getIsadmin();

            if(ISADMIN.equals("YES")) { // 관리자는 채팅 리스트로
                Fragment fragment = new ChatFragment();
                Bundle bundle = new Bundle(3); // 파라미터는 전달할 데이터 개수
                bundle.putString("chat_name", memberDTO.getEmail());
                bundle.putString("user_name", memberDTO.getName());
                bundle.putString("is_admin", ISADMIN);
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
            else { // 고객은 바로 채팅창으로 (관리자와의 채팅)
                if(loginStatus.equals("NN")){
                    Toast.makeText(getApplicationContext(), "로그인 후 이용할 수있습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                    intent.putExtra("chat_name", memberDTO.getEmail());
                    intent.putExtra("user_name", memberDTO.getName());
                    intent.putExtra("is_admin", ISADMIN);
                    startActivity(intent);
                }

            }
        } else if (id == R.id.nav_sendfcm) {
            if (!loginStatus.contains("Y")) {
                Toast.makeText(getApplicationContext(), "로그인 후 이용하실 수 있습니다.", Toast.LENGTH_LONG).show();
                return false;
            }
            Intent intent = new Intent(this, FCMActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void setNavigationHeader(String userName, String userEmail) {
        nav_header_userName.setText(userName);
        nav_header_userEmail.setText(userEmail);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void getDeviceId() {
        try {
            String sendInfo = "getDeviceId";

            String sendMsg = "";

            resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

            if (resultString != null) {
                String[] splitResult = resultString.split(" ");

                if (splitResult[0].contains("YES")) {
                    Log.i("Device ID ", splitResult[0]);
                    for (int i = 1; i < splitResult.length; i++) {
                        deviceIdList.add(splitResult[i]);
                        Log.i("Device ID " , i + " : " + splitResult[i]);
                    }
                } else {
                    Log.i("Device ID " , splitResult[0]);
                }
            } else {
                Log.i("Server Connection", "ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startLocationService(LatLng location) {

        gpsListener.setManager(locationManager);
        gpsListener.setContext(getApplicationContext());
        gpsListener.setMyLocation(location);
        gpsListener.setNotificationManager(notificationManager);

        long minTime = 60000;
        float minDistance = 0;
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, gpsListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("[SnowFlake] **** ", "startLocationService() called ... ");
    }

    public static void stopLocationService() {
        locationManager.removeUpdates(gpsListener);
        locationManager = null;
        Log.i("[SnowFlake] **** ", "stopLocationService() called ... ");
    }
}
