package com.study.android.ex15_list1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    String[] names = {"Item01", "Item02", "Item03", "Item04", "Item05",
                      "Item06", "Item07", "Item08", "Item09", "Item10",
                      "Item11", "Item12", "Item13", "Item14", "Item15",
                      "Item16", "Item17", "Item18", "Item19", "Item20",
                      "Item21", "Item22", "Item23", "Item24", "Item25"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, names);

        ListView listView1 = findViewById(R.id.listView1);

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(),
                        "selected : " + names[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}