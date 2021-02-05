package com.study.android.signin_signup_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ModifyReplyActivity extends AppCompatActivity {

    String board;
    Intent intent;

    BoardItem item = new BoardItem();


    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_reply);

        editText1 = findViewById(R.id.reply_title_editText_modify);

        editText2 = findViewById(R.id.reply_content_editText_modify);

        Intent intent = getIntent();
        board = intent.getStringExtra("board");
        item.setbMenu(board);
        item.setbId(intent.getIntExtra("bId", 0));

        if(board.equals("리뷰")) {
            editText1.setVisibility(View.GONE);
        }
        editText1.setText(intent.getStringExtra("title"));
        editText2.setText(intent.getStringExtra("content"));
    }


    public void modify_reply_button(View v) {
        item.setbTitle(editText1.getText().toString());
        item.setbContent(editText2.getText().toString());

        BoardDao dao = new BoardDao();
        dao.modifyReplyDao(item);

        finish();
    }
}
