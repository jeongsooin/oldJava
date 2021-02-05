package com.study.android.signin_signup_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class WriteBoardActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    EditText editText1;
    EditText editText2;
    CheckBox checkBox;

    Button button;

    String ID, name, is_admin;

    String bMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_board);

        editText1 = findViewById(R.id.bTitle_editText);
        editText2 = findViewById(R.id.bContent_EditText);
        checkBox = findViewById(R.id.secret_checkBox);     // 비밀글 유무
        button = findViewById(R.id.write_board_button);

        Intent intent = getIntent();
        bMenu = intent.getExtras().getString("board");
        ID = intent.getExtras().getString("ID");
        name = intent.getExtras().getString("name");
        is_admin = intent.getExtras().getString("is_admin");



        if(bMenu.equals("공지사항")){
            checkBox.setVisibility(View.GONE);
            button.setText("공지작성");
        }
    }

    public void writeInquiry(View v) {

        String bTitle = editText1.getText().toString();

        if(bTitle.equals("") || bTitle==null){
            Toast.makeText(getApplicationContext(), "제목을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        String bContent = editText2.getText().toString();

        if(bContent.equals("") || bContent==null){
            Toast.makeText(getApplicationContext(), "내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Boolean check = checkBox.isChecked();   // 비밀글 유무

        BoardItem item = new BoardItem();
        item.setbMenu(bMenu);
        item.setID(ID);
        item.setbName(name);
        item.setbTitle(bTitle);
        item.setbContent(bContent);
        item.setSecret(String.valueOf(check));

        BoardDao dao = new BoardDao();
        dao.writeDao(item);
        Toast.makeText(getApplicationContext(), "글이 작성 되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
