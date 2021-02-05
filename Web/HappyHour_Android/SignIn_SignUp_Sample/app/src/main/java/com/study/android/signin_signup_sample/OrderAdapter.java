package com.study.android.signin_signup_sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class OrderAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    Activity activity;
    ArrayList<OrderItem> items = new ArrayList<>();

    public OrderAdapter(Activity activity){
        this.activity = activity;
    }

    public void addItem(OrderItem item) {
        int status = item.getFood_status();

        // status 1(주문) 2(요리중) 만 리스트에 넣는다.
        // 새로운 주문을 리스트에 넣고 시간순으로 정렬
        if(status == 1 || status == 2){
            items.add(item);
            Collections.sort(items);
        }
    }

    // 리스트에서 제거
    public void removeItem(int sequence) {

        Iterator it = items.iterator();
        for(int i=0;it.hasNext();i++){
           OrderItem item = (OrderItem) it.next(); // items 안에 있는 item
           if(item.getSequence() == sequence){
               items.remove(i);
               break;
           }
        }
    }

    @Override
    public int getCount() {return items.size();}

    @Override
    public Object getItem(int position) {return items.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        OrderItemView view = null;
        if(convertView == null) {
            view = new OrderItemView(activity.getApplicationContext());
        } else {
            view = (OrderItemView) convertView;
        }

        final OrderItem item = items.get(position);

        view.setFood_table(item.getFood_table());
        view.setFood_name(item.getFood_name());
        view.setFood_amount(item.getFood_amount());
        view.setExpd_time(item.getExpd_time());
        view.setStatus(item.getFood_status());


        // 버튼 클릭 후 해당 요리의 요리상황을 변경
        final Button button1 = view.findViewById(R.id.button1);
        button1.setFocusable(false);
        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){

                String message = "";

                switch (item.getFood_status()) {
                    case 1:
                        message ="요리를 시작하겠습니까?";
                        break;
                    case 2:
                        message = "요리를 완료하였습니까?";
                        break;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                builder.setMessage(message)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("알림")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {

                                int status = item.getFood_status();
                                // 요리진행 상황 변경
                                switch (status) {
                                    case 1:
                                        // 리스트에 보이는 값 변경
                                        item.setFood_status(status+1);
                                        items.set(position, item);

                                        // db에 있는 값 변경
                                        databaseReference.child("foodList")
                                                .child(String.valueOf(item.getSequence()))
                                                .setValue(item);
                                        break;

                                    case 2:
                                        // 리스트에 보이는 값 변경
                                        item.setFood_status(status+1);
                                        items.set(position, item);

                                        // db에 있는 값 변경
                                        databaseReference.child("foodList")
                                                .child(String.valueOf(item.getSequence()))
                                                .setValue(item);

                                        databaseReference.child("foodList")
                                                .child(String.valueOf(item.getSequence()))
                                                .removeValue();


                                        items.remove(position);
                                        break;
                                }

                                notifyDataSetChanged();

                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        return view;
    }
}
