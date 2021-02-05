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

public class WriteReviewActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView textView;
    EditText editText;
    ImageView imageView;
    RatingBar ratingBar;

    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";

    String imageName;   // 오리지널 사진이름
    String imagePath;   // 사진이 저장될 때 이름
    String saveImageName;

    Button add_image_button;
    Button delete_image_button;

    String ID, name, is_admin;

    private static final int PICK_FROM_ALBUM = 2;

    private String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private static final int MULTIPLE_PERMISSIONS = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        Intent intent = getIntent();
        ID = intent.getExtras().getString("ID");
        name = intent.getExtras().getString("name");
        is_admin = intent.getExtras().getString("is_admin");

        textView = findViewById(R.id.MenuTextView);
        editText = findViewById(R.id.bContentEditText);
        imageView = findViewById(R.id.write_review_ImageView);
        ratingBar = findViewById(R.id.write_ratingBar);

        add_image_button = findViewById(R.id.add_image_button);
        delete_image_button = findViewById(R.id.delete_image_button);
    }

    // 사진 추가버튼
    public void onAlbumClicked(View v) {


        // 이미지업로드 할 때 필요
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

        checkPermissions();
        selectFromAlbum();
    }

    // 사진 삭제버튼
    public void deleteImageClicked(View v) {
        imagePath = "";
        imageName = "";
        imageView.setVisibility(View.GONE);
        imageView.invalidate();

        delete_image_button.setVisibility(View.GONE);
        add_image_button.setVisibility(View.VISIBLE);

    }


    // 글작성 버튼
    public void writeReview(View v) {

        String bMenu = "리뷰";


        String menu_name = textView.getText().toString();

        if(menu_name.equals("") || menu_name==null){
            Toast.makeText(getApplicationContext(), "메뉴를 선택해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        String bContent = editText.getText().toString();

        if(bContent.equals("") || bContent==null){
            Toast.makeText(getApplicationContext(), "내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        int star = (int) ratingBar.getRating();    // 별점.

        BoardItem item = new BoardItem();
        item.setbMenu(bMenu);
        item.setbName(name);
        item.setID(ID);
        item.setMenu_name(menu_name);
        item.setbContent(bContent);
        item.setbImageName(imageName);
        item.setStar(star);

        // 사진이 포함되어 있지않을 때
        if(imagePath==null || imagePath.equals("")){

        } else { // 사진이 포함 되어있을때
            String ip ="192.168.219.114"; //자신의 IP번호

            item.setbImageName(imageName);
            item.setImg_extension(saveImageName);

            String serverip = "http://"+ip+":8081/image/";
            HttpFileUpload(serverip, "",imagePath);
        }

        BoardDao dao = new BoardDao();
        dao.writeDao(item);
        Toast.makeText(getApplicationContext(), "리뷰가 작성 되었습니다.", Toast.LENGTH_SHORT).show();
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

    public void select_menu(View v) {
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
