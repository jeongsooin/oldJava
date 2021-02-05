package com.study.android.signin_signup_sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Activity activity;
    Context context;
    ArrayList<BoardItem> items = new ArrayList<>();

    String ID, name, is_admin;

    String language = "한국어";

    public ReviewAdapter(Activity activity){
        this.activity = activity;
        context=activity.getApplicationContext();
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
    public View getView(final int position, View convertView, ViewGroup parent){

        final ReviewItemView view = new ReviewItemView(context);
        final BoardItem item = items.get(position);

            if(item.getbStep()==0){ //답글이 아닐 때
                view.setReview();
                if(item.getImg_extension() != null){   // 사진이 있을 때
                    String imageName = item.getImg_extension();
                    view.setMenuName(item.getMenu_name());
                    view.setImageView(imageName);
                    view.setRating(item.getStar()); // 별점표시
                    final Button button1 = view.findViewById(R.id.delete_review_button);

                   if(item.getID().equals(ID)){
                       button1.setFocusable(false);
                       button1.setVisibility(View.VISIBLE);
                       button1.setOnClickListener(new Button.OnClickListener(){
                           @Override
                           public void onClick(View v){


                               AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                               builder.setMessage("삭제하시겠습니까?")
                                       .setIcon(android.R.drawable.ic_dialog_alert)
                                       .setTitle("알림")
                                       .setCancelable(true)
                                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                           public void onClick(DialogInterface dialog, int id) {

                                               // 답글이 있으면 답글도 삭제
                                               if(items.size()>1){
                                                   for(int i = 1; items.get(position+i).getbGroup() == item.getbGroup();i++){
                                                       BoardDao dao = new BoardDao();
                                                       dao.deleteDao(items.get(position+i).getbId());

                                                       items.remove(position+i);
                                                   }
                                               }

                                               // DB에서 삭제
                                               BoardDao dao = new BoardDao();
                                               dao.deleteDao(item.getbId());

                                               // 리스트에서 삭제
                                               items.remove(position);
                                               notifyDataSetChanged();



                                               Toast.makeText(activity.getApplicationContext(), "리뷰가 삭제 되었습니다.", Toast.LENGTH_SHORT).show();

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
                       button1.setVisibility(View.GONE);
                   }


                    final Button button2 = view.findViewById(R.id.modify_review_button);
                    if(item.getID().equals(ID)){
                        button2.setFocusable(false);
                        button2.setVisibility(View.VISIBLE);
                        button2.setOnClickListener(new Button.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                Intent intent = new Intent(context, ModifyReviewActivity.class);
                                intent.putExtra("bId", item.getbId());
                                intent.putExtra("board", item.getbMenu());
                                intent.putExtra("menu_name", item.getMenu_name());
                                intent.putExtra("bContent", item.getbContent());
                                intent.putExtra("bImageName", item.getbImageName());
                                intent.putExtra("img_extension", item.getImg_extension());
                                intent.putExtra("star", item.getStar());
                                activity.startActivity(intent);
                            }
                        });
                    } else {
                        button2.setVisibility(View.GONE);
                    }


                    Button replyButton = view.findViewById(R.id.reply_review_button);
                        if(is_admin.equals("YES") && items.size()-1>position){
                            if(items.get(position+1).getbGroup() != item.getbGroup()){
                                replyButton.setVisibility(View.VISIBLE);
                                replyButton.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {  // 답변하기 창으로
                                        Intent intent = new Intent(context, ReplyActivity.class);
                                        intent.putExtra("board", "리뷰");
                                        intent.putExtra("ID", ID);
                                        intent.putExtra("name", name);
                                        intent.putExtra("bGroup", item.getbGroup());
                                        intent.putExtra("bStep", item.getbStep());
                                        intent.putExtra("bIndent", item.getbIndent());
                                        activity.startActivity(intent);
                                    }
                                });
                            }

                    } else {
                        replyButton.setVisibility(View.GONE);
                    }





                }
            } else {    // 답글일 때
                view.setReply();
                view.setImage();

                final Button reply_delete_button = view.findViewById(R.id.delete_review_reply);
                final Button reply_modify_button = view.findViewById(R.id.modify_review_reply);
                if(is_admin.equals("YES")){
                    reply_delete_button.setVisibility(View.VISIBLE);
                    reply_delete_button.setFocusable(false);
                    reply_delete_button.setOnClickListener(new Button.OnClickListener(){
                        @Override
                        public void onClick(View v){


                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                            builder.setMessage("삭제하시겠습니까?")
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setTitle("알림")
                                    .setCancelable(true)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int id) {

                                            // DB에서 삭제
                                            BoardDao dao = new BoardDao();
                                            dao.deleteDao(item.getbId());

                                            // 리스트에서 삭제
                                            items.remove(position);
                                            notifyDataSetChanged();

                                            Toast.makeText(activity.getApplicationContext(), "글이 삭제 되었습니다.", Toast.LENGTH_SHORT).show();

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
                    reply_modify_button.setVisibility(View.VISIBLE);
                    reply_modify_button.setFocusable(false);
                    reply_modify_button.setOnClickListener(new Button.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent intent = new Intent(activity, ModifyReplyActivity.class);
                            intent.putExtra("bId", item.getbId());
                            intent.putExtra("board", item.getbMenu());
                            intent.putExtra("title", item.getbTitle());
                            intent.putExtra("content",item.getbContent());
                            intent.putExtra("ID", ID);
                            intent.putExtra("name", name);
                            activity.startActivity(intent);
                        }
                    });
                }   else {
                    reply_delete_button.setVisibility(View.GONE);
                    reply_modify_button.setVisibility(View.GONE);
                }
            }


        view.setbName(item.getbName(),language);
        view.setbDate(item.getbDate());
        view.setbContent(item.getbContent(),language);

        return view;
    }


}
