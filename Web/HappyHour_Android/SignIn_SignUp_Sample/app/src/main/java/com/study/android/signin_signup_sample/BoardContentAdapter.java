package com.study.android.signin_signup_sample;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardContentAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Activity activity;
    Context context;
    ArrayList<BoardItem> items = new ArrayList<>();
    String language = "한국어";
    String board;

    String ID, name, is_admin;

    public BoardContentAdapter(Activity activity){
        this.activity = activity;
        context = activity.getApplicationContext();
    }

    public void setUserInfo(String ID, String name, String is_admin) {
        this.ID = ID;
        this.name = name;
        this.is_admin = is_admin;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void addItem(BoardItem item){
        items.add(item);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        BoardContentItemView view = null;
//        if(convertView == null){
//            view = new BoardListItemView(context);
//        }else {
//            view = (BoardListItemView) convertView;
//        }

        view = new BoardContentItemView(activity);
        view.setContent();


        Button replyButton = view.findViewById(R.id.reply_content_button);
        replyButton.setFocusable(false);

        final BoardItem item = items.get(position);
        if(item.getbMenu().equals("문의사항") && is_admin.equals("YES") && items.size()==1) {    // 본글
            replyButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {  // 답변하기 창으로
                    Intent intent = new Intent(context, ReplyActivity.class);
                    intent.putExtra("board", board);
                    intent.putExtra("bGroup", items.get(0).getbGroup());
                    intent.putExtra("bStep", items.get(0).getbStep());
                    intent.putExtra("bIndent", items.get(0).getbIndent());
                    intent.putExtra("ID", ID);
                    intent.putExtra("name", name);
                    intent.putExtra("is_admin", is_admin);
                    activity.startActivity(intent);
                }
            });

        } else {    // 답변

            replyButton.setVisibility(View.GONE);
        }

        view.setbTitle(item.getbTitle(), language);
        view.setbName(item.getbName(), language);
        view.setbDate(item.getbDate());
        view.setbContent(item.getbContent(), language);

        // 삭제버튼
        Button deleteButton = view.findViewById(R.id.delete_content_button);
        if(item.getID().equals(ID)) {
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setFocusable(false);
            deleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                    builder.setMessage("글을 삭제 하겠습니까?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {

                                    if(item.getbStep() == 0) {   // 본글 삭제시 답글까지 삭제
                                        Iterator it = items.iterator();
                                        while (it.hasNext()){
                                            BoardDao dao = new BoardDao();
                                            dao.deleteDao(((BoardItem)it.next()).getbId());
                                        }
                                    }

                                    BoardDao dao = new BoardDao();
                                    dao.deleteDao(item.getbId());
                                    activity.finish();
                                    Toast.makeText(context, "글이 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
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
        } else {
            deleteButton.setVisibility(View.GONE);
        }


        Button modifyButton = view.findViewById(R.id.modify_content_button);
        if(item.getbStep() == 0){
            if(item.getID().equals(ID)){
                modifyButton.setVisibility(View.VISIBLE);
                modifyButton.setFocusable(false);
                modifyButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {  // 수정하기 창으로

                        Intent intent = new Intent(context, ModifyBoardActivity.class);
                        intent.putExtra("secret", item.getSecret());
                        intent.putExtra("bId", item.getbId());
                        intent.putExtra("bMenu", item.getbMenu());
                        intent.putExtra("bTitle", item.getbTitle());
                        intent.putExtra("bContent", item.getbContent());

                        activity.startActivityForResult(intent, 1);

                    }
                });
            } else {
                modifyButton.setVisibility(View.GONE);
            }
        } else {
            if(is_admin.equals("YES")) {
                modifyButton.setVisibility(View.VISIBLE);
                modifyButton.setFocusable(false);
                modifyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {  // 수정하기 창으로
                        Intent intent = new Intent(context, ModifyReplyActivity.class);
                        intent.putExtra("bId", item.getbId());
                        intent.putExtra("board", item.getbMenu());
                        intent.putExtra("title", item.getbTitle());
                        intent.putExtra("content", item.getbContent());

                        activity.startActivity(intent);
                    }
                });
            } else {
                modifyButton.setVisibility(View.GONE);
            }
        }

        if(items.size()>1 && item.getbStep()>0) {
            view.replyText.setVisibility(View.VISIBLE);
        } else {
            view.replyText.setVisibility(View.GONE);
        }



        return view;
    }

}
