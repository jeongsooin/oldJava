package com.example.dailye;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private EditText chat_room_name, user_name;
    private ListView chat_list;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    public static MainFragment getInstance() {
        return new MainFragment();
    }

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        chat_room_name = rootView.findViewById(R.id.chatroom_name);
        user_name = rootView.findViewById(R.id.chat_nickname);
        chat_list = rootView.findViewById(R.id.chat_list);
        showChatList();
        Button button = rootView.findViewById(R.id.start_chat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnClicked();
            }
        });
        return rootView;
    }

    public void onBtnClicked() {
        if(chat_room_name.getText().toString().equals("") || user_name.getText().toString().equals(""))
            return;

        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra("chatName", chat_room_name.getText().toString());
        intent.putExtra("userName", user_name.getText().toString());
        startActivity(intent);
    }

    private void showChatList() {
        final ArrayAdapter<String> adapter
                = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        chat_list.setAdapter(adapter);
        databaseReference.child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.e("LOG", "dataSnapshot.getKey() : " + dataSnapshot.getKey());
                adapter.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

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
