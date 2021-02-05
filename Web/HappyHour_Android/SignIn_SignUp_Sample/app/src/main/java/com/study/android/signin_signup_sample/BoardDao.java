package com.study.android.signin_signup_sample;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;

public class BoardDao {
    private static final String TAG = "lecture";

    String sendMsg;
    ConnectDB_Boot connectDB;

    public BoardDao() {
        connectDB = new ConnectDB_Boot();
    }

    // 리스트 가져오기
    public BoardListAdapter getList(Context context, String board) {
        BoardListAdapter adapter = new BoardListAdapter(context, board);

        sendMsg = "kind=list&bMenu="+board;

        try{

            String result = connectDB.execute(sendMsg).get(); // 해당 게시판의 게시물만 가져온다.

            JSONObject object = new JSONObject(result);
            JSONArray json = object.getJSONArray("items");

            for(int i=0;i<json.length();i++){

                JSONObject obj = json.getJSONObject(i);
                BoardItem item = new BoardItem();

                item.setbId(obj.getInt("bId"));
                item.setID(obj.getString("ID"));
                item.setbName(obj.getString("bName"));
                item.setbDate(Timestamp.valueOf(obj.getString("bDate")));
                item.setbTitle(obj.getString("bTitle"));
                item.setSecret(obj.getString("secret"));
                item.setbIndent(obj.getInt("bIndent"));
                item.setbStep(obj.getInt("bStep"));
                item.setbGroup(obj.getInt("bGroup"));
                item.setbImageName(obj.getString("bImageName"));
                item.setImg_extension(obj.getString("img_extension"));
                item.setReplyNum(obj.getInt("replyNum"));
                item.setbMenu(obj.getString("bMenu"));
                adapter.addItem(item);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return adapter;
    }

    // 리뷰리스트 가져오기
    public ReviewAdapter getReviewList(Activity activity) {
        ReviewAdapter adapter = new ReviewAdapter(activity);

        sendMsg = "kind=list&bMenu=리뷰";

        try{

            String result = connectDB.execute(sendMsg).get(); // 해당 게시판의 게시물만 가져온다.

            JSONObject object = new JSONObject(result);
            JSONArray json = object.getJSONArray("items");

            for(int i=0;i<json.length();i++){

                JSONObject obj = json.getJSONObject(i);
                BoardItem item = new BoardItem();


                item.setbId(obj.getInt("bId"));
                item.setbName(obj.getString("bName"));
                item.setID(obj.getString("ID"));
                item.setbTitle(obj.getString("bTitle"));
                item.setbDate(Timestamp.valueOf(obj.getString("bDate")));
                item.setMenu_name(obj.getString("menu_name"));
                item.setbContent(obj.getString("bContent"));
                item.setbGroup(obj.getInt("bGroup"));
                item.setbStep(obj.getInt("bStep"));
                item.setbMenu(obj.getString("bMenu"));
                item.setbImageName(obj.getString("bImageName"));
                item.setImg_extension(obj.getString("img_extension"));
                item.setStar(obj.getInt("star"));
                item.setMenu_name(obj.getString("menu_name"));

                adapter.addItem(item);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return adapter;
    }

    // 리뷰리스트 가져오기(검색)
    public ReviewAdapter getReviewList(Activity activity, String menu_name) {
        ReviewAdapter adapter = new ReviewAdapter(activity);

        sendMsg = "kind=list&bMenu=리뷰&menu_name=" + menu_name;

        try{
            String result = connectDB.execute(sendMsg).get();

            JSONObject object = new JSONObject(result);
            JSONArray json = object.getJSONArray("items");

            for(int i=0;i<json.length();i++){

                JSONObject obj = json.getJSONObject(i);
                BoardItem item = new BoardItem();

                item.setbId(obj.getInt("bId"));
                item.setbName(obj.getString("bName"));
                item.setbTitle(obj.getString("bTitle"));
                item.setID(obj.getString("ID"));
                item.setbDate(Timestamp.valueOf(obj.getString("bDate")));
                item.setMenu_name(obj.getString("menu_name"));
                item.setbContent(obj.getString("bContent"));
                item.setbGroup(obj.getInt("bGroup"));
                item.setbStep(obj.getInt("bStep"));
                item.setbImageName(obj.getString("bImageName"));
                item.setImg_extension(obj.getString("img_extension"));
                item.setStar(obj.getInt("star"));
                item.setMenu_name(obj.getString("menu_name"));

                adapter.addItem(item);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return adapter;
    }

    // 게시물 상세 보기
    public BoardContentAdapter getContent (Activity activity, int bId, String way) {  // String way => 수정시 조회수 올리지 않기위해
        BoardContentAdapter adapter = new BoardContentAdapter(activity);

        sendMsg = "kind=content&bId="+bId+"&way="+way;

        try{

            String result = connectDB.execute(sendMsg).get();

            JSONObject object = new JSONObject(result);
            JSONArray json = object.getJSONArray("items");
            for(int i=0;i<json.length();i++){
                BoardItem item = new BoardItem();
                JSONObject obj = json.getJSONObject(i);
                item.setbId(obj.getInt("bId"));
                item.setbContent(obj.getString("bContent"));
                item.setID(obj.getString("ID"));
                item.setbName(obj.getString("bName"));
                item.setbDate(Timestamp.valueOf(obj.getString("bDate")));
                item.setbTitle(obj.getString("bTitle"));
                item.setbMenu(obj.getString("bMenu"));
                item.setbStep(obj.getInt("bStep"));
                item.setbIndent(obj.getInt("bIndent"));
                item.setbGroup(obj.getInt("bGroup"));
                item.setImg_extension(obj.getString("img_extension"));
                item.setbImageName(obj.getString("bImageName"));

                adapter.addItem(item);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return adapter;
    }

    // 글작성
    public void writeDao(BoardItem item) {

        sendMsg = "kind=write" +
                  "&bMenu="+ item.getbMenu() +
                  "&bContent=" + item.getbContent() +
                  // id bName 세션에서 가져와서 저장
                  "&ID=" + item.getID() +
                  "&bName=" + item.getbName() +
                  "&bIndent=" + 0 +
                  "&bStep=" + 0;

        if(item.getbMenu().equals("리뷰")){
            sendMsg = sendMsg + "&star=" + item.getStar()
                              + "&menu_name=" + item.getMenu_name()
                              + "&secret=&bTitle=";
        } else if(item.getbMenu().equals("문의사항")){
            sendMsg = sendMsg + "&secret=" + item.getSecret()+"&bTitle=" + item.getbTitle() +"&star=&menu_name=";
        } else if(item.getbMenu().equals("공지사항")){
            sendMsg = sendMsg + "&secret=&star=&menu_name=&bTitle=" + item.getbTitle();
        }

        if(item.getImg_extension()!=null){ // 사진 올릴 때
            sendMsg = sendMsg + "&bImageName=" + item.getbImageName() +
                                "&img_extension=" + item.getImg_extension();
        } else {  // 사진 올리지 않을 때
            sendMsg = sendMsg + "&bImageName="+"&img_extension=";
        }

        connectDB.execute(sendMsg);
    }

    public void deleteDao(int bId) {
        sendMsg = "kind=delete" +
                 "&bId=" + bId;
        connectDB.execute(sendMsg);
    }

    public void modifyDao(BoardItem item) {
        sendMsg = "kind=modify" +
                 "&bId=" + item.getbId() +
                 "&bContent=" + item.getbContent();

        if(item.getbMenu().equals("리뷰")){
            sendMsg = sendMsg + "&star=" + item.getStar()
                    + "&menu_name=" + item.getMenu_name()
                    + "&secret=&bTitle=";
        } else if(item.getbMenu().equals("문의사항")){
            sendMsg = sendMsg + "&secret=" + item.getSecret()+"&star=&menu_name="
                    + "&bTitle=" + item.getbTitle();
        } else if(item.getbMenu().equals("공지사항")){
            sendMsg = sendMsg + "&secret=&star=&menu_name="
                    + "&bTitle=" + item.getbTitle();
        }

        if(item.getImg_extension()!=null){ // 사진 올릴 때
            sendMsg = sendMsg + "&bImageName=" + item.getbImageName() +
                    "&img_extension=" + item.getImg_extension();
        } else {  // 사진 올리지 않을 때
            sendMsg = sendMsg + "&bImageName="+"&img_extension=";
        }
        connectDB.execute(sendMsg);
    }

    public void modifyReplyDao(BoardItem item) {
        sendMsg = "kind=modifyReply" +
                "&bId=" + item.getbId() +
                "&bContent=" + item.getbContent() +
                "&bTitle=" + item.getbTitle();

        connectDB.execute(sendMsg);
    }

    public void replyDao(BoardItem item) {


        sendMsg = "kind=reply" +
                "&bMenu="+ item.getbMenu() +
                "&bTitle=" + item.getbTitle() +
                "&bContent=" + item.getbContent() +
                // id bName 세션에서 가져와서 저장
                "&ID=" + item.getID() +
                "&bName=" + item.getbName() +
                "&bIndent=" + item.getbIndent() +
                "&bStep=" + item.getbStep() +
                "&bGroup=" + item.getbGroup();
        connectDB.execute(sendMsg);
    }

    public String[] getMenu() {

        String[] menus = null;
        sendMsg = "kind=menu";


        try {
            String result = connectDB.execute(sendMsg).get(); // 해당 게시판의 게시물만 가져온다.
            JSONObject object = new JSONObject(result);
            JSONArray json = object.getJSONArray("items");

            menus = new String[json.length()];

            for(int i=0;i<json.length();i++){

                JSONObject obj = json.getJSONObject(i);

                menus[i] = obj.getString("menu");
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        return menus;
    }
}
