package com.study.android.signin_signup_sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;


public class BoardFragment extends Fragment {
    private static final String TAG = "lecture";

    ViewGroup viewGroup;

    String board;

    BoardListAdapter adapter;

    private ListView listView1;
    TextView boardTextView; // 문의사항or공지사항
    TextView textView1;
    TextView textView2;
    TextView textView3;

    ArrayList<String> texts = new ArrayList<>();

    String ID, name, is_admin;

    Button write_button;
    Button translation_button;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup =
                (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);

        board = getArguments().getString("bMenu");
        ID = getArguments().getString("ID");
        name = getArguments().getString("name");
        is_admin = getArguments().getString("is_admin");

        boardTextView = viewGroup.findViewById(R.id.boardTextView);
        boardTextView.setText(board); // 공지사항 or 문의사항
        listView1 = viewGroup.findViewById(R.id.board_listView);
        textView1 = viewGroup.findViewById(R.id.ID_textView);
        textView2 = viewGroup.findViewById(R.id.bDate_textView);
        textView3 = viewGroup.findViewById(R.id.bTitle_textView);
        write_button = viewGroup.findViewById(R.id.board_write_button);

        texts.add(board); // 게시판 이름 저장


        if(board.equals("공지사항") && is_admin.equals("YES")){
            write_button.setVisibility(View.VISIBLE);
            write_button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {  // 글쓰기 창으로 이동
                    Intent intent = new Intent(getActivity().getApplicationContext(), WriteBoardActivity.class);
                    intent.putExtra("board", board);
                    intent.putExtra("ID", ID);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            });
        } else {
            write_button.setVisibility(View.GONE);
        }

        if (board.equals("문의사항")) {
            write_button.setVisibility(View.VISIBLE);

                write_button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {  // 글쓰기 창으로 이동
                        if (is_admin.equals("YES") || is_admin.equals("NO")) {
                            Intent intent = new Intent(getActivity().getApplicationContext(), WriteBoardActivity.class);
                            intent.putExtra("board", board);
                            intent.putExtra("ID", ID);
                            intent.putExtra("name", name);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "로그인 후 이용할 수 있습니다.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        }


        if(board.equals("문의사항")){
            write_button.setText("문의하기");
            texts.add("문의하기");
        } else if (board.equals("공지사항")){
            write_button.setText("공지작성");
            texts.add("공지작성");
        }

        translation_button = viewGroup.findViewById(R.id.board_translation);
        translation_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                final String[] languages = {"한국어","English", "汉语", "日本語"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final int[] selectedItem ={0};
                builder.setIcon(android.R.drawable.ic_dialog_alert)
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
                                    Iterator it = texts.iterator();

                                    if(languages[selectedItem[0]].equals("한국어")){
                                        // 게시판 이름 넣기
                                        boardTextView.setText(it.next().toString());
                                        if(it.hasNext()){
                                            // 문의사항일 경우 문의하기 버튼 텍스트 넣기
                                            write_button.setText(it.next().toString());
                                        }
                                    } else {
                                        Translation translation1 = new Translation();
                                        // 게시판 이름 번역
                                        boardTextView.setText(translation1.execute(it.next().toString(),
                                                languages[selectedItem[0]]).get());

                                        if(it.hasNext()){
                                            Translation translation2 = new Translation();
                                            // 버튼에 들어갈 text 번역
                                            write_button.setText(translation2.execute(it.next().toString(),
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
        BoardDao dao = new BoardDao();
        adapter = dao.getList(getContext(), board);

        adapter.setUserInfo(ID, name, is_admin);

        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BoardItem item = (BoardItem) adapter.getItem(position);

                // 본인 혹은 관리자 비밀글이 아닐때 만 글을 볼수있다.
                if(item.getSecret().equals("true")) {
                    if(item.getID().equals(ID) || is_admin.equals("YES")){
                        Intent intent = new Intent(getActivity().getApplicationContext(), BoardContentActivity.class);
                        intent.putExtra("bId", item.getbId());
                        intent.putExtra("ID", ID);
                        intent.putExtra("name", name);
                        intent.putExtra("is_admin", is_admin);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), BoardContentActivity.class);
                    intent.putExtra("bId", item.getbId());
                    intent.putExtra("ID", ID);
                    intent.putExtra("name", name);
                    intent.putExtra("is_admin", is_admin);
                    startActivity(intent);

                }

            }
        });




    }
}

