package com.study.android.signin_signup_sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class KitchenFragment extends Fragment {
    private static final String TAG = "lecture";

    public static KitchenFragment getInstance() { return new KitchenFragment(); }

    public KitchenFragment() { }

    ViewGroup viewGroup;

    OrderAdapter adapter;
    private ListView listView1;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup =
                (ViewGroup) inflater.inflate(R.layout.activity_kitchen, container, false);

        return viewGroup;
    }

    @Override
    public void onStart() {
        super.onStart();

        listView1 = viewGroup.findViewById(R.id.listView1);

        openChat();
    }

    private void openChat() {
        adapter = new OrderAdapter(getActivity());

        listView1.setAdapter(adapter);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등.. 리스너 관리
        databaseReference.child("foodList")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s){
                        OrderItem item = dataSnapshot.getValue(OrderItem.class);
                        item.setSequence(Integer.valueOf(dataSnapshot.getKey()));
                        adapter.addItem(item);
                        adapter.notifyDataSetChanged();
                        Log.d(TAG,dataSnapshot.getKey());
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot){
                        adapter.removeItem(Integer.valueOf(dataSnapshot.getKey()));
                        adapter.notifyDataSetChanged();
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
