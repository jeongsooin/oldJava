package com.study.android.practice_exceltodb;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.io.InputStream;
import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity {

    private NotesDbAdapter dbAdapter;
    private static final String TAG = "lecture";

    WordListAdapter adapter;
    long currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "DatabaseTest :: onCreate()");

        this.dbAdapter = new NotesDbAdapter(this);
        this.adapter = new WordListAdapter(this);

        final ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WordItem item = (WordItem)adapter.getItem(position);
                currentPosition = adapter.getItemId(position);
                Intent intent = new Intent(getApplicationContext(), WordItemView.class);
                intent.putExtra("word", item.getWord());
                intent.putExtra("meaning", item.getMeaning());
                intent.putExtra("pronunciation", item.getPronunciation());
                intent.putExtra("item", (WordItem)adapter.getItem(position));
                startActivity(intent);
            }
        });

        copyExcelDatabase();
        makeWordItemList();
    }

    public void onBtn2Clicked(View v) {
        makeWordItemList();
        adapter.notifyDataSetChanged();
    }

    private void makeWordItemList() {
        dbAdapter.open();
        Cursor result = dbAdapter.fetchAllNotes();
        result.moveToFirst();
        while (!result.isAfterLast()) {
            String word = result.getString(1);
            String pronunciation  = result.getString(2);
            String meaning  = result.getString(3);
            WordItem item = new WordItem(word, pronunciation, meaning);
            adapter.addItem(item);
            result.moveToNext();
        }

        result.close();
        dbAdapter.close();
    }

    private void copyExcelDatabase() {
        Log.w("ExcelToDatabase", "copyExcelDataToDatabase()");

        Workbook workbook = null;
        Sheet sheet = null;

        try {
            InputStream inputStream = getBaseContext().getResources().getAssets().open("CONFUSED_386.xls");
            workbook = Workbook.getWorkbook(inputStream);

            if (workbook != null) {
                sheet = workbook.getSheet(0);

                if (sheet != null) {

                    int nMaxColumn = 2;
                    int nRowStartIndex = 1;
                    int nRowEndIndex = sheet.getColumn(nMaxColumn - 1).length - 1;
                    int nColumnStartIndex = 0;
                    int nColumnEndIndex = sheet.getRow(2).length - 1;

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
}
