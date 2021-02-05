package com.study.android.signin_signup_sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class BoardContentActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    ArrayList<BoardItem> items;

    BoardDao dao;

    int bId;

    String ID, name, is_admin;

    LinearLayout userLayout;
    LinearLayout adminLayout;

    String board;

    Bitmap bitmap;

    String language = "한국어";

    BoardContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_content);

        dao = new BoardDao();

        Intent intent = getIntent();
        bId = intent.getExtras().getInt("bId");
        ID = intent.getExtras().getString("ID");
        name = intent.getExtras().getString("name");
        is_admin = intent.getExtras().getString("is_admin");
    }

    @Override
    public void onResume() {
        super.onResume();

        BoardDao dao = new BoardDao();
        adapter = dao.getContent(this, bId, "read");
        adapter.setUserInfo(ID, name, is_admin);
        board = adapter.items.get(0).getbMenu();
        adapter.setBoard(board);
        ListView listView = findViewById(R.id.board_content_listview);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 10) {
            bId = data.getIntExtra("bId", 0);
            dao = new BoardDao();
            adapter = dao.getContent(this, bId,"modify");
        }
    }

    public void boardContentTranslation(View v) {
        final int[] selectedItem = {0};
        final String[] languages = {"한국어","English", "汉语", "日本語"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("번역")
                .setCancelable(true)
                .setSingleChoiceItems(languages, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = which;
                    }
                })
                .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 번역

                        try {
                            // 리스트 뷰 안에 내용들 번역
                            adapter.setLanguage(languages[selectedItem[0]]);
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
