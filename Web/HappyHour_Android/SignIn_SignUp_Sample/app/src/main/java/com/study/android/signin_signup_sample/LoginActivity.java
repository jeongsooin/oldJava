package com.study.android.signin_signup_sample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;

    public static LoginActivity loginActivity;
    public static MemberDTO memberDTO = MainActivity.memberDTO;
    private static String mEmail;
    private static String mPassword;
    String resultString;
    String idByANDROID_ID;

    private static EditText mEmailField;
    private static EditText mPasswordField;
    private static View mLoginFormView;

    private Button emailSignInButton;
    private Button emailCreateAccountButton;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginActivity = LoginActivity.this;
        idByANDROID_ID = FirebaseInstanceId.getInstance().getToken();


        mEmailField = (EditText) findViewById(R.id.userEmail);
        mPasswordField = (EditText) findViewById(R.id.userPassword);
        mLoginFormView = findViewById(R.id.login_form);

        emailSignInButton = findViewById(R.id.emailSignInButton);
        emailCreateAccountButton = findViewById(R.id.emailCreateAccountButton);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    public void updateDeviceId(String deviceid, String email) {
        try {
            String sendInfo = "updateDeviceId";

            String sendMsg = "deviceid=" + deviceid + "&email=" + email;

            resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

            if (resultString != null) {
                String[] splitResult = resultString.split(" ");

                if (splitResult[0].contains("SUCCESS")) {
                    Log.i("Device Id Updating " , "SUCCESS");
                } else {
                    Log.i("Device Id Updating " , "FAILED");
                }
            } else {
                Log.i("Server Connection", "ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onEmailSignInButtonClicked() {
        if (!validateLoginInfo()) {
            return;
        }

        String _mEmail = mEmailField.getText().toString();
        String _mPassword = mPasswordField.getText().toString();

        try {
            String sendInfo = "signIn";

            String sendMsg = "email=" + _mEmail + "&password=" + _mPassword;

            resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

            if (resultString != null) {
                String[] splitResult = resultString.split(" ");

                if (splitResult[0].equals("MEMBER_LOGIN_SUCCESS")) {
                    String[] memberInfo = new String[splitResult.length - 1];

                    for (int i = 0; i < splitResult.length - 1; i++) {
                        memberInfo[i] = splitResult[i + 1];
                    }

                    memberDTO.setEmail(memberInfo[1]);
                    memberDTO.setPassword(memberInfo[2]);
                    memberDTO.setName(memberInfo[3]);
                    memberDTO.setPhone(memberInfo[4]);
                    memberDTO.setIsadmin(memberInfo[5]);
                    memberDTO.setIsvalid(memberInfo[6]);
                    memberDTO.setAlert(memberInfo[7]);
                    memberDTO.setBirth(memberInfo[8]);
                    memberDTO.setGender(memberInfo[9]);
                    memberDTO.setRsvok(Integer.parseInt(memberInfo[10]));
                    memberDTO.setRsvok(Integer.parseInt(memberInfo[11]));
                    memberDTO.setDeviceid(idByANDROID_ID);
                    MainActivity.loginStatus = "YN";
                    MyPageFragment.buttonSignOut.setVisibility(View.VISIBLE);
                    MyPageFragment.buttonSignIn.setVisibility(View.GONE);
                    MyPageFragment.buttonRSVInfo.setVisibility(View.VISIBLE);
                    MyPageFragment.buttonMyInfo.setVisibility(View.VISIBLE);
                    MainActivity.isAdmin = memberDTO.getIsadmin();
                    MainActivity.setNavigationHeader(memberInfo[2], memberInfo[0]);
                    MainActivity.setNavigationMenu(MainActivity.isAdmin);
                    updateDeviceId(idByANDROID_ID, memberDTO.getEmail());

                    Toast.makeText(getApplicationContext(), "안녕하세요 " + memberInfo[2] + " 님!", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (splitResult[0].equals("MEMBER_LOGIN_FAILED") && splitResult[1].equals("INVALID_EMAIL")) {
                    mEmailField.setError("!");
                    mPasswordField.setError("!");
                    Toast.makeText(getApplicationContext(), "이메일 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else if (splitResult[0].equals("MEMBER_LOGIN_FAILED") && splitResult[1].equals("INVALID_PASSWORD")) {
                    mEmailField.setError("!");
                    mPasswordField.setError("!");
                    Toast.makeText(getApplicationContext(), "이메일 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else if (splitResult[0].equals("MEMBER_LOGIN_FAILED") && splitResult[1].equals("ERROR")) {
                    mEmailField.setError("!");
                    mPasswordField.setError("!");
                    Toast.makeText(getApplicationContext(), "로그인 과정에서 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "로그인 과정에서 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    public void onEmailCreateAccountButtonClicked() {
        mLoginFormView.setVisibility(View.GONE);
    }

    public static void setEmailPassword(String email, String password) {
        mEmail = email;
        mPassword = password;
        mEmailField.setText(mEmail);
        mPasswordField.setText(mPassword);
        mLoginFormView.setVisibility(View.VISIBLE);
    }

    private static boolean validateLoginInfo() {
        boolean valid = true;

        String _email = mEmailField.getText().toString();
        String _password = mPasswordField.getText().toString();

        if (TextUtils.isEmpty(_email) || !_email.contains("@")) {
            mEmailField.setError("이메일을 입력해 주세요.");
            mEmailField.requestFocus();
            return false;
        } else {
            mEmailField.setError(null);
        }

        if (TextUtils.isEmpty(_password)) {
            mPasswordField.setError("비밀번호를 입력하세요.");
            mPasswordField.requestFocus();
            return false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                finish();
                break;
            case R.id.emailSignInButton:
                onEmailSignInButtonClicked();
                break;
            case R.id.emailCreateAccountButton:
                onEmailCreateAccountButtonClicked();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    // 구글 계정을 해당 앱에서 삭제할 때 - 구글 로그인으로 가입한 회원이 탈퇴시
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null)
                MainActivity.loginStatus = "YS";
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.layout.fragment_my_page, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}