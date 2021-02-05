package com.study.android.signin_signup_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;


public class ChatFragment extends Fragment {
    private static final String TAG = "lecture";

    ViewGroup viewGroup;

    ChatListAdapter adapter;

    private ListView chat_list;

    private String USER_NAME;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewGroup =
                (ViewGroup) inflater.inflate(R.layout.fragment_chat, container, false);


        USER_NAME = getArguments().getString("user_name");

        return viewGroup;
    }

    @Override
    public void onStart(){
        super.onStart();

        chat_list = viewGroup.findViewById(R.id.chat_list);

        showChatList();
    }

    private void showChatList() {
        // 리스트 어댑터 생성 및 세팅
        adapter = new ChatListAdapter(getActivity());
        chat_list.setAdapter(adapter);
        chat_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Intent intent = new Intent(getActivity().getApplicationContext(), ChatActivity.class);
                intent.putExtra("chat_name", adapter.items.get(position).getRoomName());    // 고객아이디
                intent.putExtra("user_name", USER_NAME);
                intent.putExtra("is_admin", "YES");
                intent.putExtra("chat_user_name", adapter.items.get(position).getCustomerName()); // 고객 이름
                startActivity(intent);
            }
        });
        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        databaseReference.child("chat").child("lastChat").orderByChild("chatTime").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s){
                ChatDTO item = dataSnapshot.getValue(ChatDTO.class);
                item.setRoomName(dataSnapshot.getKey());    // userName에 채팅방 이름 저장
                adapter.addItem(item);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String ID = dataSnapshot.getKey();
                adapter.removeItem(ID);
                ChatDTO item = dataSnapshot.getValue(ChatDTO.class);
                item.setRoomName(dataSnapshot.getKey());    // userName에 채팅방 이름 저장
                adapter.addItem(item);
                adapter.notifyDataSetChanged();
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
