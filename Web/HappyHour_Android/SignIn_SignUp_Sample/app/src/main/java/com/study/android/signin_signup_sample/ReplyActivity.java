package com.study.android.signin_signup_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ReplyActivity extends AppCompatActivity {

    String board;
    Intent intent;

    BoardItem item = new BoardItem();

    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        editText1 = findViewById(R.id.reply_title_editText);
        editText2 = findViewById(R.id.reply_content_editText);

        intent = getIntent();
        board = intent.getExtras().getString("board");
        item.setbGroup(intent.getExtras().getInt("bGroup"));
        item.setbStep(intent.getExtras().getInt("bStep"));
        item.setbIndent(intent.getExtras().getInt("bIndent"));
        item.setID(intent.getExtras().getString("ID"));
        item.setbName(intent.getExtras().getString("name"));

        if(board.equals("리뷰")) {
            editText1.setVisibility(View.GONE);
        } else {
            editText1.setVisibility(View.VISIBLE);
        }


    }

    public void reply(View v) {
        item.setbMenu(board);

        item.setbContent(editText2.getText().toString());

        if(board.equals("리뷰")){

        } else {
            item.setbTitle(editText1.getText().toString());
        }

        BoardDao dao = new BoardDao();
        dao.replyDao(item);
        setResult(20, intent);
        finish();
    }
}
