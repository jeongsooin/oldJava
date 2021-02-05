package com.study.android.signin_signup_sample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;

public class ReviewItemView extends LinearLayout {
    private static final String TAG = "lecture";

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView menu_name_textView;
    RatingBar ratingBar;

    ImageView imageView;
    Bitmap bitmap;

    LayoutInflater inflater;

    public ReviewItemView(Context context){
        super(context);
        inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setbName(String bName, String language){

        if(language.equals("한국어")){
            textView1.setText(bName);
        } else {
            Translation translation = new Translation();
            try {
                textView1.setText(translation.execute(bName,language).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setbDate(Timestamp bDate){
        textView2.setText(bDate+"");
    }

    public void setMenuName(String menuName) { menu_name_textView.setText(menuName);}

    public void setbContent(String bContent, String language) {
        if(language.equals("한국어")){
            textView3.setText(bContent);
        } else {
            Translation translation = new Translation();
            try {
                textView3.setText(translation.execute(bContent,language).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setImage(){ // 답글 화살표 이미지 삽입
        imageView.setImageResource(R.drawable.rightarrow);
    }

    public void setRating(double score) {
        ratingBar.setRating((float) score);
    }

    public void setImageView(String bExtension) {
        if(bExtension != null || !bExtension.equals("")){
            String ip ="192.168.219.114"; //자신의 IP번호
            String serverip = "http://"+ip+":8081/upload/"+ bExtension;

            // 콘솔에 오류나와서 if문에 넣음
            // java.io.FileNotFoundException: http://192.168.219.127:8081/upload/null
            if(!serverip.equals("http://192.168.219.114:8081/upload/null")){
                new LoadImage().execute(serverip);
            }

        } else {

        }
    }

    // 서버에서 이미지 가져오기
    private class LoadImage extends AsyncTask<String, String, Bitmap> {

//        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pDialog = new ProgressDialog(ImageUrlDown.this);
//            pDialog.setMessage("이미지 로딩중입니다...");
//            pDialog.show();
        }

        protected Bitmap doInBackground(String... args) {
            try {

//                bitmap =
//                        BitmapFactory
//                        .decodeStream((InputStream) new URL(args[0]).getContent());

                Bitmap b = null;



                BitmapFactory.Options options = new BitmapFactory.Options();

                options.inJustDecodeBounds = true;

                BitmapFactory.decodeStream( (InputStream) new URL(args[0]).getContent(), null, options );

                int scale = 1;

                if( options.outHeight > 1024 || options.outWidth > 1024 ) {

                    scale = (int)Math.pow(  2,  (int)Math.round( Math.log( 1024 / (double)Math.max( options.outHeight, options.outWidth ) ) / Math.log( 0.5 ) ) );

                }

                BitmapFactory.Options o2 = new BitmapFactory.Options();

                o2.inSampleSize = scale;

                bitmap = BitmapFactory.decodeStream( (InputStream) new URL(args[0]).getContent(), null, o2 );


            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if (image != null) {

                imageView.setImageBitmap(image);
                imageView.setVisibility(View.VISIBLE);
//                pDialog.dismiss();

            } else {
//                pDialog.dismiss();
//                Toast.makeText(ImageUrlDown.this, "이미지가 존재하지 않습니다.",
//                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    // 리뷰창
    public void setReview() {
        inflater.inflate(R.layout.review_item_view, this, true);
        textView1 = findViewById(R.id.bName_TextView);
        textView2 = findViewById(R.id.bDate_TextView);
        textView3 = findViewById(R.id.bContent_TextView);
        menu_name_textView = findViewById(R.id.menu_name_TextView);
        imageView = findViewById(R.id.review_imageView);
        ratingBar = findViewById(R.id.review_ratingBar);
    }

    // 답변
    public void setReply() {
        inflater.inflate(R.layout.reply_review_item_view, this, true);
        textView1 = findViewById(R.id.rName_TextView);
        textView2 = findViewById(R.id.rDate_TextView);
        textView3 = findViewById(R.id.rContent_TextView);
        imageView = findViewById(R.id.reply_imageView);
    }

}
