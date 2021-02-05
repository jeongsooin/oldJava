package com.example.dailye;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.model.LatLng;

import java.io.InputStream;
import java.util.Set;
import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private NotesDbAdapter dbAdapter;
    private SpeechDbAdapter speechDbAdapter;
    public static FavoriteListDbAdapter favoriteListDbAdapter;
    public static FavoriteItemAdapter favoriteItemAdapter;
    WordItemAdapter wordItemAdapter;
    SpeechItemAdapter speechItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isPermissionAllowed = isNotiPermissionAllowed();

       if(!isPermissionAllowed) {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
           startActivity(intent);
      }

        this.dbAdapter = new NotesDbAdapter(this);
        this.wordItemAdapter = new WordItemAdapter(this);
        this.speechItemAdapter = new SpeechItemAdapter(this);
        this.speechDbAdapter = new SpeechDbAdapter(this);

        favoriteListDbAdapter = new FavoriteListDbAdapter(this);
        favoriteItemAdapter = new FavoriteItemAdapter(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_main, MainFragment.getInstance()).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String bannerid = "ca-app-pub-6337194123340322/1807641895";
        MobileAds.initialize(getApplicationContext(), bannerid);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice("29A3A694F6A820E9115B00F23FEF5CD6")
                .build();
        mAdView.loadAd(adRequest);
        copyExcelDatabase("CONFUSED_386.xls");
        copyExcelDatabase2("SampleSpeech.xls");
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

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_main, MainFragment.getInstance()).commit();

        if (id == R.id.study_word) {
            replaceFragment(StudyCourseFragment.getInstance());
        } else if (id == R.id.study_sentence) {
            replaceFragment(SentenceListFragment.getInstance());
        } else if (id == R.id.favorite_word) {
            replaceFragment(FavoriteListFragment.getInstance());
        } else if (id == R.id.study_course) {
            replaceFragment(StudyCourseFragment.getInstance());
        } else if (id == R.id.study_today) {

        } else if (id == R.id.app_settings) {
            replaceFragment(AppSettingFragment.getInstance());
        } else if (id == R.id.about_info) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void startLocationService(LatLng location) {

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        GPSListener gpsListener = new GPSListener(location, getApplicationContext());
        long minTime = 60000;
        float minDistance = 0;
        try {
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, gpsListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "startLocationService() called ... ");
    }

    public void stopLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        GPSListener gpsListener = new GPSListener();
        manager.removeUpdates(gpsListener);

        Log.d(TAG, "stopLocationService() called ... ");
    }

    private boolean isNotiPermissionAllowed() {
        Set<String> notiListenerSet = NotificationManagerCompat.getEnabledListenerPackages(this);
        String myPackageName = getPackageName();

        for(String packageName : notiListenerSet) {
            if(packageName == null) {
                continue;
            }
            if(packageName.equals(myPackageName)) {
                return true;
            }
        }

        return false;
    }

    public void makeWordItemList() {
        dbAdapter.open();
        Cursor result = dbAdapter.fetchAllNotes();
        result.moveToFirst();
        while (!result.isAfterLast()) {
            String word = result.getString(1);
            String pronunciation  = result.getString(2);
            String meaning  = result.getString(3);
            WordItem item = new WordItem(word, pronunciation, meaning);
            wordItemAdapter.addItem(item);
            result.moveToNext();
        }

        result.close();
        dbAdapter.close();
    }

    public void makeSentenceItemList(SpeechItemAdapter speechItemAdapter1) {
        speechDbAdapter.open();
        Cursor result = speechDbAdapter.fetchAllNotes();
        result.moveToFirst();
        while (!result.isAfterLast()) {
            String word = result.getString(1);
            String meaning  = result.getString(2);
            SpeechItem item = new SpeechItem(word, meaning);
            speechItemAdapter1.addItem(item);
            result.moveToNext();
        }

        result.close();
        speechDbAdapter.close();
    }

    public void makeDailyWordItemList(int startIndex, int endIndex, WordItemAdapter wordItemAdapter) {
        dbAdapter.open();
        Cursor result = dbAdapter.fetchAllNotes();
        result.move(startIndex);
        Log.d(TAG, "makeDailyWordItemList() called ... ");
        Log.d(TAG, "Start 커서 위치 : " + result.getPosition());
        int end = startIndex;
        while (!result.isAfterLast()) {
            if(end > endIndex)
                break;
            String word = result.getString(1);
            String pronunciation  = result.getString(2);
            String meaning  = result.getString(3);
            WordItem item = new WordItem(word, pronunciation, meaning);
            wordItemAdapter.addItem(item);
            result.moveToNext();
            end++;
        }

        Log.d(TAG, "End 커서 위치 : " + result.getPosition());
        result.close();
        dbAdapter.close();
    }

    public void copyExcelDatabase(String xlsName) {

        Workbook workbook = null;
        Sheet sheet = null;
        try {
            InputStream inputStream = getBaseContext().getResources().getAssets().open(xlsName);
            workbook = Workbook.getWorkbook(inputStream);

            if (workbook != null) {
                sheet = workbook.getSheet(0);
                if (sheet != null) {
                    int nMaxColumn = 2;
                    int nRowStartIndex = 1;
                    int nRowEndIndex = sheet.getColumn(nMaxColumn - 1).length - 1;
                    int nColumnStartIndex = 0;

                    dbAdapter.open();
                    for (int nRow = nRowStartIndex; nRow <= nRowEndIndex; nRow++) {
                        String word = sheet.getCell(nColumnStartIndex, nRow).getContents();
                        String pronunciation = sheet.getCell(nColumnStartIndex + 1, nRow).getContents();
                        String meaning = sheet.getCell(nColumnStartIndex + 2, nRow).getContents();
                        dbAdapter.createNote(word,pronunciation, meaning);
                    }
                    dbAdapter.close();
                } else {
                    System.out.println("Sheet is null!!");
                }
            } else {
                System.out.println("WorkBook is null!!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null)
                workbook.close();
        }
    }




    public void copyExcelDatabase2(String xlsName) {
        Log.w("ExcelToDatabase", "copyExcelDataToDatabase()");

        Workbook workbook = null;
        Sheet sheet = null;

        try {
            InputStream inputStream = getBaseContext().getResources().getAssets().open(xlsName);
            workbook = Workbook.getWorkbook(inputStream);

            if (workbook != null) {
                sheet = workbook.getSheet(0);

                if (sheet != null) {

                    int nMaxColumn = 2;
                    int nRowStartIndex = 1;
                    int nRowEndIndex = sheet.getColumn(nMaxColumn - 1).length - 1;
                    int nColumnStartIndex = 0;
                    int nColumnEndIndex = sheet.getRow(2).length - 1;

                    speechDbAdapter.open();
                    for (int nRow = nRowStartIndex; nRow <= nRowEndIndex; nRow++) {
                        String word = sheet.getCell(nColumnStartIndex, nRow).getContents();
                        String meaning = sheet.getCell(nColumnStartIndex + 2, nRow).getContents();
                        speechDbAdapter.createNote(word, meaning);
                    }
                    speechDbAdapter.close();
                } else {
                    System.out.println("Sheet is null!!");
                }
            } else {
                System.out.println("WorkBook is null!!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null)
                workbook.close();
        }
    }

    public void onLoginClick(View v) {
        Intent intent = new Intent(
                this,
                LoginActivity.class);
        startActivity(intent);
    }
}

