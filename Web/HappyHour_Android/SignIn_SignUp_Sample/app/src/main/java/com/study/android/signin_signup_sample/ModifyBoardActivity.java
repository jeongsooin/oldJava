package com.study.android.signin_signup_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyBoardActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    EditText editText1;
    EditText editText2;
    CheckBox checkBox;

    int bId;
    String bMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_board);
        editText1 = findViewById(R.id.bTitle_editText_modify);
        editText2 = findViewById(R.id.bContent_editText_modify);
        checkBox = findViewById(R.id.modify_checkBox);

        Intent intent = getIntent();
        bId = intent.getExtras().getInt("bId");
        bMenu = intent.getExtras().getString("bMenu");

        editText1.setText(intent.getExtras().getString("bTitle"));
        editText2.setText(intent.getExtras().getString("bContent"));
        checkBox.setChecked(Boolean.valueOf(intent.getExtras().getString("secret")));
    }


    public void modifyInquiry(View v) {
        String bTitle = editText1.getText().toString();

        if(bTitle.equals("") || bTitle==null){
            Toast.makeText(getApplicationContext(), "제목이 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        String bContent = editText2.getText().toString();

        if(bContent.equals("") || bContent==null){
            Toast.makeText(getApplicationContext(), "내용이 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        BoardItem item = new BoardItem();
        item.setbId(bId);
        item.setbTitle(bTitle);
        item.setbContent(bContent);
        item.setbMenu(bMenu);
        item.setSecret(String.valueOf(checkBox.isChecked()));

        BoardDao dao = new BoardDao();
        dao.modifyDao(item);

        Intent intent = getIntent();
        intent.putExtra("bId", bId);
        intent.putExtra("bMenu", bMenu);
        setResult(10, intent);
        Toast.makeText(getApplicationContext(), "글이 변경 되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
