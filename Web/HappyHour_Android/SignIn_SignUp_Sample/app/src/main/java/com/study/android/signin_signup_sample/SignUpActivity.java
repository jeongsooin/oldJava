package com.study.android.signin_signup_sample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    public static SignUpActivity signUpActivity;

    private FirebaseAuth mEmailAuth;

    private Button buttonStartVerification;
    private Button buttonSignUpComplete;
    private Button verifyEmailButton;
    private Button sendVerifyEmailButton;

    private LinearLayout verifyEmailForm;
    private TextView fieldVerifyAlertText;
    private TextView mEmailField;


    private String mEmail;
    private String mPassword;
    private String mPhoneNumber;
    private String mName;
    private String mIsAdmin;
    private String mIsValid;
    private String mAlert;
    private String mBirth;
    private String mGender;

    LoginActivity loginActivity = (LoginActivity)LoginActivity.loginActivity;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }

        signUpActivity = SignUpActivity.this;

        Intent intent = getIntent();
        mEmail = intent.getStringExtra("email");
        mPassword = intent.getStringExtra("password");
        mPhoneNumber = intent.getStringExtra("phone");
        mName = intent.getStringExtra("name");
        mIsAdmin = intent.getStringExtra("isAdmin");
        mIsValid = intent.getStringExtra("isValid");
        mBirth = intent.getStringExtra("birth");
        mGender = intent.getStringExtra("gender");
        mAlert = intent.getStringExtra("alert");

        // Views
        fieldVerifyAlertText = findViewById(R.id.fieldVerifyAlertText);
        mEmailField = findViewById(R.id.mEmailText);
        verifyEmailForm = findViewById(R.id.verifyEmailForm);

        // Buttons
        buttonStartVerification = findViewById(R.id.buttonStartVerification);
        buttonSignUpComplete = findViewById(R.id.buttonSignUpComplete);
        verifyEmailButton = findViewById(R.id.verifyEmailButton);
        sendVerifyEmailButton = findViewById(R.id.sendVerifyEmailButton);

        // Visibility Settings
        buttonStartVerification.setVisibility(View.VISIBLE);
        verifyEmailButton.setVisibility(View.VISIBLE);
        verifyEmailForm.setVisibility(View.GONE);
        fieldVerifyAlertText.setVisibility(View.GONE);
        buttonSignUpComplete.setVisibility(View.GONE);

        // TextView Settings
        mEmailField.setText(mEmail);
        mEmailAuth = FirebaseAuth.getInstance();

        // OnClickListeners
        verifyEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyEmailButton.setEnabled(false);
                buttonStartVerification.setVisibility(View.GONE);
                verifyEmailForm.setVisibility(View.VISIBLE);
                buttonSignUpComplete.setVisibility(View.VISIBLE);
            }
        });

        sendVerifyEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(mEmail, mPassword);
            }
        });

        buttonStartVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhoneAuthActivity.class);
                intent.putExtra("mPhoneNumber", mPhoneNumber);
                intent.putExtra("email", mEmail);
                intent.putExtra("password", mPassword);
                intent.putExtra("name", mName);
                intent.putExtra("isAdmin", mIsAdmin);
                intent.putExtra("isValid", mIsValid);
                intent.putExtra("birth", mBirth);
                intent.putExtra("gender", mGender);
                intent.putExtra("alert", mAlert);
                startActivity(intent);
            }
        });

        buttonSignUpComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mIsValid = "YES";

                    String sendInfo = "signUp";

                    String sendMsg = "email=" + mEmail + "&password=" + mPassword + "&name=" + mName + "&phone=" + mPhoneNumber + "&isadmin=" + mIsAdmin + "&isvalid=" + mIsValid + "&alert=" + mAlert + "&birth=" + mBirth + "&gender=" + mGender;

                    resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

                    if (resultString != null) {
                        String[] splitResult = resultString.split(" ");

                        if (splitResult[0].equals("MEMBER_JOIN_SUCCESS")) {
                            Toast.makeText(getApplicationContext(), "축하합니다! 회원가입이 완료되었습니다.", Toast.LENGTH_LONG ).show();
                            loginActivity.setEmailPassword(mEmail, mPassword);
                            finish();
                        } else if (splitResult[0].equals("MEMBER_JOIN_FAILED")) {
                            Toast.makeText(getApplicationContext(), "회원가입에 문제가 발생하였습니다.", Toast.LENGTH_LONG ).show();
                            loginActivity.finish();
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR!.", Toast.LENGTH_SHORT).show();
                        loginActivity.finish();
                        finish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);

        mEmailAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user =

                                    mEmailAuth.getCurrentUser();
                            sendEmailVerification();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    private void sendEmailVerification() {
        final FirebaseUser user = mEmailAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        findViewById(R.id.verifyEmailButton).setEnabled(true);

                        if (task.isSuccessful()) {
                            verifyEmailButton.setVisibility(View.GONE);
                            fieldVerifyAlertText.setVisibility(View.VISIBLE);
                            fieldVerifyAlertText.setText("입력하신 이메일로 인증 메일이 발송되었습니다.");
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            fieldVerifyAlertText.setVisibility(View.VISIBLE);
                            fieldVerifyAlertText.setText("이메일 발송에 실패하였습니다.");
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        mEmailAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "인증 과정에서 문제가 발생하였습니다.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "인증 과정에서 문제가 발생하였습니다.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void signOut() {
        mEmailAuth.signOut();
    }
}