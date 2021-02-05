package com.study.android.signin_signup_sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class BoardListAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<BoardItem> items = new ArrayList<>();
    String language = "한국어";
    String board;

    String ID, name, is_admin;

    public BoardListAdapter(Context context, String board){
        this.context = context;
        this.board = board;
    }

    public void setUserInfo(String ID, String name, String is_admin) {
        this.ID = ID;
        this.name = name;
        this.is_admin = is_admin;
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
    public View getView(int position, View convertView, ViewGroup parent){

        BoardListItemView view = null;
//        if(convertView == null){
//            view = new BoardListItemView(context);
//        }else {
//            view = (BoardListItemView) convertView;
//        }

        view = new BoardListItemView(context);

        BoardItem item = items.get(position);
        if(!item.getSecret().equals("true") || item.getID().equals(ID) || is_admin.equals("YES")){  // 비밀글 X
            view.setItemView();
            view.setbName(item.getbName(), language);
            view.setbDate(item.getbDate());
            view.setbTitle(item.getbTitle(), language);

            if((item.getbMenu()).equals("문의사항")){
                String replyStatus = "";

                if(item.getReplyNum() == 0){
                    replyStatus = "답변대기";
                } else{
                    replyStatus = "답변완료";
                }

                if(board.equals("문의사항")){
                    view.setReplyCheck(replyStatus, language);
                }
            }
        } else {
            view.setSecretItemView(language);
        }



        return view;
    }

}
