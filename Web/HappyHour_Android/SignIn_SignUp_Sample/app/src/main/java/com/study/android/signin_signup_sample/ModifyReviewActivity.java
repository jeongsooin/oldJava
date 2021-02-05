package com.study.android.signin_signup_sample;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ModifyReviewActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    Bitmap bitmap;

    BoardItem item = new BoardItem();

    TextView textView;
    EditText editText;
    ImageView imageView;
    RatingBar ratingBar;

    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";

    int bId;

    Boolean changed = false;    // 사진 변경 유무
    String imageName;   // 오리지널 사진이름
    String imagePath;
    String saveImageName; // 사진이 저장될 때 이름

    Button add_image_button;
    Button delete_image_button;

    private static final int PICK_FROM_ALBUM = 2;

    private String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private static final int MULTIPLE_PERMISSIONS = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_review);

        checkPermissions();

        textView = findViewById(R.id.modify_menu);
        editText = findViewById(R.id.bContentEditText_modify);
        imageView = findViewById(R.id.review_ImageView_modify);
        ratingBar = findViewById(R.id.modify_ratingBar);

        add_image_button = findViewById(R.id.add_image_button_modify);
        delete_image_button = findViewById(R.id.delete_image_button_modify);

        Intent intent = getIntent();
        bId = intent.getExtras().getInt("bId");

        textView.setText(intent.getExtras().getString("menu_name"));
        editText.setText(intent.getExtras().getString("bContent"));
        imageName = intent.getExtras().getString("bImageName");
        saveImageName = intent.getExtras().getString("img_extension");
        ratingBar.setRating(intent.getExtras().getInt("star"));
        // 사진있는 글이면 사진 가져오기
        if(!saveImageName.equals("null")){
            String ip ="192.168.219.114"; //자신의 IP번호
            String serverip = "http://"+ip+":8081/upload/"+ saveImageName;
            new LoadImage().execute(serverip);
            imageView.setVisibility(View.VISIBLE);
            add_image_button.setVisibility(View.GONE);
            delete_image_button.setVisibility(View.VISIBLE);
        } else {
            saveImageName = "";
        }

    }

    // 사진 추가 or 변경 버튼
    public void onAlbum_clicked(View v) {

        // 이미지업로드 할 때 필요
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());


        selectFromAlbum();
    }

    // 사진 삭제버튼
    public void delete_image_clicked(View v) {
        imagePath = "";
        imageName = "";
        saveImageName="";

        imageView.setVisibility(View.GONE);
        imageView.invalidate();


        delete_image_button.setVisibility(View.GONE);
        add_image_button.setVisibility(View.VISIBLE);

    }


    // 수정하기 버튼
    public void modifyReview(View v) {

        String bMenu = "리뷰";
        String menu = textView.getText().toString();
        if(menu.equals("") || menu==null){
            Toast.makeText(getApplicationContext(), "메뉴가 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        String bContent = editText.getText().toString();
        if(bContent.equals("") || bContent==null){
            Toast.makeText(getApplicationContext(), "내용이 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        int star = (int) ratingBar.getRating();

        BoardItem item = new BoardItem();
        item.setbId(bId);
        item.setbMenu(bMenu);
        item.setMenu_name(menu);
        item.setbContent(bContent);
        item.setbImageName(imageName);
        item.setImg_extension(saveImageName);
        item.setStar(star);

        if(changed == true){    // 사진이 변경 됐을 때 서버로 변경 된 이미지 전송
            String ip ="192.168.219.114"; //자신의 IP번호
            String serverip = "http://"+ip+":8081/image/";
            HttpFileUpload(serverip, "",imagePath);
        }


        BoardDao dao = new BoardDao();
        dao.modifyDao(item);
        Toast.makeText(getApplicationContext(), "변경 되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }


    private boolean checkPermissions() {
        int result;
        List<String> permissionList = new ArrayList<>();
        for(String pm : permissions){
            result = ContextCompat.checkSelfPermission(this, pm);
            if(result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pm);
            }
        }
        if(!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissionList.toArray(new String[permissionList.size()]),
                    MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode) {
            case  MULTIPLE_PERMISSIONS: {
                if(grantResults.length > 0){
                    for (int i = 0; i < permissions.length; i++){
                        if (permissions[i].equals(this.permissions[0])){
                            if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showNoPermissionToastAndFinish();
                            }
                        } else if (permissions[i].equals(this.permissions[1])) {
                            if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showNoPermissionToastAndFinish();
                            }
                        }
                    }
                } else {
                    showNoPermissionToastAndFinish();
                }
            }
        }
    }

    private void showNoPermissionToastAndFinish() {
        Toast.makeText(this, "권한 요청에 동의 해주셔야 이용 가능합니다.",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void selectFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_FROM_ALBUM) {
                // 갤러리에서 가져오기
                getPictureFromGallery(data.getData());
            }
        }
    }

    private void getPictureFromGallery(Uri imgUri) {

        imagePath = getRealPathFromURI(imgUri);

        this.imageName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
        saveImageName = getUuid() + "." + imageName;
//        imagePath = imagePath.substring(0, imagePath.lastIndexOf("/")) + imageName;

        Log.d(TAG, imagePath);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int  exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);

        // 경로를 통해 비트맵으로 전환
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        if (bitmap != null) {

            // 이미지 뷰에 비트맵 넣기
//            imageView.setImageBitmap(bitmap);
            imageView.setImageBitmap(rotate(bitmap, exifDegree));
            imageView.invalidate();
            imageView.setVisibility(View.VISIBLE);
            add_image_button.setVisibility(View.GONE);
            delete_image_button.setVisibility(View.VISIBLE);
            changed = true;
        } else {
            Log.d(TAG, "BBB");
        }
    }

    // 사진을 정방향대로 회전하기
    private Bitmap rotate(Bitmap src, float degree) {
        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }

    // 사진의 회전값 가져오기
    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }


    // 사진의 절대경로 구하기
    private String getRealPathFromURI(Uri contentUri) {
        int column_index=0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        Log.d(TAG, cursor.getString(column_index));
        return cursor.getString(column_index);
    }


    // 서버에 사진 올리기
    public void HttpFileUpload(String urlString, String params, String fileName) {
        try {

            FileInputStream mFileInputStream = new FileInputStream(fileName);
            URL connectUrl = new URL(urlString);
            Log.d("Test", "mFileInputStream  is " + mFileInputStream);

            // HttpURLConnection 통신
            HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            // write data
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + saveImageName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);

            int bytesAvailable = mFileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);

            byte[] buffer = new byte[bufferSize];
            int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);

            Log.d("Test", "image byte is " + bytesRead);

            // read image
            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = mFileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // close streams
            Log.e("Test", "File is written");
            mFileInputStream.close();
            dos.flush();
            // finish upload...

            // get response
            InputStream is = conn.getInputStream();

            StringBuffer b = new StringBuffer();
            for (int ch = 0; (ch = is.read()) != -1; ) {
                b.append((char) ch);
            }
            is.close();
            Log.e("Test", b.toString());

        } catch (Exception e) {
            Log.d("Test", "exception " + e.getMessage());
            // TODO: handle exception
        }
    } // end of HttpFileUpload()

    // 사진 이름을 서버에 저장될 이름으로 변경
    public static String getUuid(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
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
                bitmap = BitmapFactory
                        .decodeStream((InputStream) new URL(args[0]).getContent());

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

    public void select_menu_modify(View v) {
        final int[] selectedItem = {0};
        BoardDao dao = new BoardDao();
        final String[] menus = dao.getMenu();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

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
                        textView.setText(menus[selectedItem[0]]);
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
