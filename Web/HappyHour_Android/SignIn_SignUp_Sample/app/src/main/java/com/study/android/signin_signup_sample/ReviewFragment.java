package com.study.android.signin_signup_sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;


public class ReviewFragment extends Fragment {
    private static final String TAG = "lecture";

    ViewGroup viewGroup;
    Button button;
    ListView listView;

    TextView boardTextView; // 게시판 이름이 적히는 TextView (리뷰)
    Button reviewButton;    // 리뷰 작성 창으로 넘어가는 버튼

    Button translation_button;

    ArrayList<String> textViews = new ArrayList<>();

    ReviewAdapter adapter;

    RadioGroup radioGroup;
    RadioButton radioButton;

    String ID;
    String name;
    String is_admin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup =
                (ViewGroup) inflater.inflate(R.layout.fragment_review, container, false);

        ID = getArguments().getString("ID");
        name = getArguments().getString("name");
        is_admin = getArguments().getString("is_admin");

        textViews.add("리뷰");    // 게시판 이름 저장
        textViews.add("리뷰를 남겨주세요.");    // 버튼에 들어갈 text 저장

        boardTextView = viewGroup.findViewById(R.id.board_review_TextView);
        reviewButton = viewGroup.findViewById(R.id.review_button);

        radioGroup = viewGroup.findViewById(R.id.search_radioGroup);


        listView = viewGroup.findViewById(R.id.review_listView);
        button = viewGroup.findViewById(R.id.review_button);
        button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {  // 리뷰쓰기 창으로
                    if(is_admin.equals("YES") || is_admin.equals("NO")) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), WriteReviewActivity.class);
                        intent.putExtra("ID", ID);
                        intent.putExtra("name", name);
                        intent.putExtra("is_admin", is_admin);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "로그인 후 이용할 수 있습니다.", Toast.LENGTH_LONG).show();
                    }
                }
            });




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                radioButton = radioGroup.findViewById(checkedId); //체크된 것을 입력받습니다.

                final BoardDao dao = new BoardDao();

                switch (checkedId) {
                    case R.id.radioButton1 :
                        adapter = dao.getReviewList(getActivity());
                        adapter.setUserInfo(ID, name, is_admin);
                        listView.setAdapter(adapter);
                        break;
                    case R.id.radioButton2 :
                        final int[] selectedItem = {0};

                        final String[] menus = dao.getMenu();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        builder.setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("메뉴")
                                .setCancelable(true)
                                .setSingleChoiceItems(menus, 0, new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedItem[0] = which;
                                    }
                                })
                                .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        BoardDao dao1 = new BoardDao();
                                        adapter = dao1.getReviewList(getActivity(), menus[selectedItem[0]]);
                                        adapter.setUserInfo(ID, name, is_admin);
                                        listView.setAdapter(adapter);
                                    }
                                });

                        AlertDialog alert = builder.create();
                        alert.show();
                        break;
                }
            }
        });

        translation_button = viewGroup.findViewById(R.id.review_translation);
        translation_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                final int[] selectedItem = {0};
                final String[] languages = {"한국어","English", "汉语", "日本語"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
                                    Iterator it = textViews.iterator();

                                    if(languages[selectedItem[0]].equals("한국어")){
                                        boardTextView.setText(it.next().toString());
                                        reviewButton.setText(it.next().toString());
                                    } else {

                                        Translation translation1 = new Translation();
                                        // 게시판 이름 번역
                                        boardTextView.setText(translation1.execute(it.next().toString(),
                                                                languages[selectedItem[0]]).get());
                                        if(it.hasNext()){
                                            Translation translation2 = new Translation();
                                            // 버튼에 들어갈 text 번역
                                            reviewButton.setText(translation2.execute(it.next().toString(),
                                                                languages[selectedItem[0]]).get());
                                        }
                                    }

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
        });

        return viewGroup;
    }

    @Override
    public void onStart() {
        super.onStart();

        radioButton = viewGroup.findViewById(R.id.radioButton1);
        radioButton.setChecked(true);

        BoardDao dao = new BoardDao();
        adapter = dao.getReviewList(getActivity());
        adapter.setUserInfo(ID, name, is_admin);
        listView.setAdapter(adapter);

//        textViews = getTexts(viewGroup);
    }

    // ViewGroup에 있는 모든 텍스트를 가져와 저장
    public ArrayList<String> getTexts(ViewGroup viewGroup) {

        ArrayList<String> texts = new ArrayList<>();

        // 번역전 text를 arraylist에 저장
        ArrayList<ViewGroup> viewGroups = new ArrayList<>();   // viewGroup
        for(int i=0; i < viewGroup.getChildCount();i++){

            // TextView, Button
            if(viewGroup.getChildAt(i) instanceof TextView) {
                texts.add(((TextView)viewGroup.getChildAt(i)).getText().toString());
            }

            // ViewGroup일 경우 리스트에 추가.
            if (viewGroup.getChildAt(i) instanceof ViewGroup){
                ViewGroup childViewGroup = (ViewGroup) viewGroup.getChildAt(i);
                viewGroups.add(childViewGroup);
            }
        }

        for(int i=0; i < viewGroups.size();i++){
            ViewGroup childViewGroup = viewGroups.get(i);
            for(int j=0;j < childViewGroup.getChildCount();j++){
                // TextView, Button
                if(childViewGroup.getChildAt(j) instanceof TextView) {
                    texts.add(((TextView)viewGroup.getChildAt(i)).getText().toString());
                }

                // ViewGroup일 경우 리스트에 추가.
                if (childViewGroup.getChildAt(j) instanceof ViewGroup){
                    ViewGroup childViewGroup2 = (ViewGroup) childViewGroup.getChildAt(j);
                    viewGroups.add(childViewGroup2);
                }
            }

        }

        return texts;
    }

}