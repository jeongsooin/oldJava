package com.study.android.signin_signup_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    ChatAdapter adapter;

    TextView textView;

    private String CHAT_NAME;
    private String USER_NAME;
    private String ISADMIN;

    private String CHAT_USER_NAME; // 고객 이름

    String deviceId = "";
    JSONArray deviceIds;

    private ListView chat_view;
    private EditText chat_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        CHAT_NAME = intent.getStringExtra("chat_name"); // 사용자의 이메일
        USER_NAME = intent.getStringExtra("user_name"); // 사용자의 이름
        ISADMIN = intent.getStringExtra("is_admin");    // 사용자인지 관리자인지

        chat_view = findViewById(R.id.chat_view);
        chat_edit = findViewById(R.id.chat_edit);

        if(ISADMIN.equals("YES")) {
            CHAT_USER_NAME = intent.getStringExtra("chat_user_name");
            try {
//                ConnectDB_Boot connectDB = new ConnectDB_Boot();
//                deviceId = connectDB.execute("kind=deviceId&ID="+CHAT_NAME).get();
//                JSONObject obj = new JSONObject(result);
//                deviceId = obj.getString("item");
            } catch (Exception e) {

            }
        } else {
            CHAT_USER_NAME = "관리자";
            try {
//                ConnectDB_Boot connectDB = new ConnectDB_Boot();
//                deviceId = connectDB.execute("kind=ManagerDeviceId").get();
//                JSONObject obj = new JSONObject(result);
//                deviceIds = obj.getJSONArray("items");
            } catch (Exception e) {

            }
        }

        Log.d(TAG, deviceId);

        textView = findViewById(R.id.chatUserName_textView);
        textView.setText(CHAT_USER_NAME);


    }

    public void onStart() {
        super.onStart();
        StringTokenizer tokenizer = new StringTokenizer(CHAT_NAME, ".");
        CHAT_NAME = tokenizer.nextToken();

        // 채팅 방 입장
        openChat(CHAT_NAME);
    }

    public void onBtnClicked(View v) {
        if(chat_edit.getText().toString().equals(""))
            return;

        Date today = new Date();

        SimpleDateFormat time = new SimpleDateFormat("a hh:mm");

        // ChatDTO를 이용하여 데이터를 묶는다.
        ChatDTO chat = new ChatDTO(USER_NAME, chat_edit.getText().toString(), time.format(today));

        if(ISADMIN.equals("YES")){
            chat.setCustomerName(CHAT_USER_NAME);
        } else {
            chat.setCustomerName(USER_NAME);
        }

        // 데이터 푸쉬
        databaseReference.child("chat").child(CHAT_NAME).push().setValue(chat);
        databaseReference.child("chat").child("lastChat").child(CHAT_NAME).setValue(chat);

        SendFCM sendFCM = new SendFCM();
        sendFCM.execute(deviceId, USER_NAME, chat.getMessage());


        // 입력창 초기화
        chat_edit.setText("");
    }



    private void openChat(String chatName) {
        adapter = new ChatAdapter(this, USER_NAME);
        chat_view.setAdapter(adapter);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등.. 리스너 관리
        databaseReference.child("chat").child(chatName)
                                        .addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s){
                ChatDTO item = dataSnapshot.getValue(ChatDTO.class);
                adapter.addItem(item);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot){

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }


}
