package com.study.android.phonebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    SingerAdapter adapter;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new SingerAdapter(this);

        SingerItem item1 = new SingerItem("정수인", "010-3827-8520", R.drawable.face01, true);
        adapter.addItem(item1);

        SingerItem item2 = new SingerItem("오건우", "010-4388-5250", R.drawable.face02, false);
        adapter.addItem(item2);

        SingerItem item3 = new SingerItem("권다연", "010-5678-4321", R.drawable.face03, true);
        adapter.addItem(item3);

        SingerItem item4 = new SingerItem("김남연", "010-1234-5678", R.drawable.face04, false);
        adapter.addItem(item4);

        SingerItem item5 = new SingerItem("김승욱", "010-4321-9876", R.drawable.face05, false);
        adapter.addItem(item5);

        SingerItem item6 = new SingerItem("김용재", "010-5678-4321", R.drawable.face00, false);
        adapter.addItem(item6);

        SingerItem item7 = new SingerItem("김태윤", "010-1234-5678", R.drawable.face00, false);
        adapter.addItem(item7);

        SingerItem item8 = new SingerItem("김혜진", "010-4321-9876", R.drawable.face08, true);
        adapter.addItem(item8);

        SingerItem item9 = new SingerItem("박상원", "010-5678-4321", R.drawable.face09, false);
        adapter.addItem(item9);

        SingerItem item10 = new SingerItem("박아름", "010-1234-5678", R.drawable.face10, true);
        adapter.addItem(item10);

        SingerItem item11 = new SingerItem("백지선", "010-4321-9876", R.drawable.face00, false);
        adapter.addItem(item11);

        SingerItem item12 = new SingerItem("양승준", "010-5678-4321", R.drawable.face12, false);
        adapter.addItem(item12);

        SingerItem item13 = new SingerItem("유재남", "010-1234-5678", R.drawable.face13, false);
        adapter.addItem(item13);

        SingerItem item14 = new SingerItem("윤영로", "010-4321-9876", R.drawable.face14, false);
        adapter.addItem(item14);

        SingerItem item15 = new SingerItem("이병헌", "010-5678-4321", R.drawable.face15, false);
        adapter.addItem(item15);

        SingerItem item16 = new SingerItem("이서현", "010-1234-5678", R.drawable.face16, true);
        adapter.addItem(item16);

        SingerItem item17 = new SingerItem("이인회", "010-4321-9876", R.drawable.face17, false);
        adapter.addItem(item17);

        SingerItem item18 = new SingerItem("이재환", "010-5142-3800", R.drawable.face18, false);
        adapter.addItem(item18);

        SingerItem item19 = new SingerItem("이철연", "010-1234-5678", R.drawable.face19, false);
        adapter.addItem(item19);

        SingerItem item20 = new SingerItem("장희준", "010-4321-9876", R.drawable.face20, false);
        adapter.addItem(item20);

        SingerItem item21 = new SingerItem("정의만", "010-5678-4321", R.drawable.face21, false);
        adapter.addItem(item21);

        SingerItem item22 = new SingerItem("조준근", "010-1234-5678", R.drawable.face22, false);
        adapter.addItem(item22);

        SingerItem item23 = new SingerItem("지동원", "010-4321-9876", R.drawable.face23, false);
        adapter.addItem(item23);

        SingerItem item24 = new SingerItem("황병운", "010-5678-4321", R.drawable.face24, false);
        adapter.addItem(item24);



        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),
                        "selected : " + item.getIsFemale(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
