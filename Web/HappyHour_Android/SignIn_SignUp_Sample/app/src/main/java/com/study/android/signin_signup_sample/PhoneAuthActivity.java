package com.study.android.signin_signup_sample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class PhoneAuthActivity extends AppCompatActivity implements
        View.OnClickListener {

    private static final String TAG = "PhoneAuthActivity";
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    private FirebaseAuth mAuth;

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    // views and buttons
    private TextView mPhoneText;
    private EditText fieldVerificationCode;
    private Button sendVerifySMSButton;
    private Button buttonVerifyPhone;
    private Button buttonResend;
    private Button buttonSMSComplete;

    // Datas
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
    SignUpActivity signUpActivity = (SignUpActivity)SignUpActivity.signUpActivity;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }

        Intent intent = getIntent();
        mPhoneNumber = intent.getStringExtra("mPhoneNumber");
        mEmail = intent.getStringExtra("email");
        mPassword = intent.getStringExtra("password");
        mName = intent.getStringExtra("name");
        mIsAdmin = intent.getStringExtra("isAdmin");
        mIsValid = intent.getStringExtra("isValid");
        mBirth = intent.getStringExtra("birth");
        mGender = intent.getStringExtra("gender");
        mAlert = intent.getStringExtra("alert");

        // Assign views and buttons
        mPhoneText = findViewById(R.id.mPhoneText);
        mPhoneText.setText(mPhoneNumber);
        fieldVerificationCode = findViewById(R.id.fieldVerificationCode);
        sendVerifySMSButton = findViewById(R.id.sendVerifySMSButton);
        buttonVerifyPhone = findViewById(R.id.buttonVerifyPhone);
        buttonVerifyPhone.setEnabled(false);
        buttonResend = findViewById(R.id.buttonResend);
        buttonResend.setEnabled(false);
        buttonSMSComplete = findViewById(R.id.buttonSMSComplete);

        // Assign click listeners
        sendVerifySMSButton.setOnClickListener(this);
        buttonVerifyPhone.setOnClickListener(this);
        buttonResend.setOnClickListener(this);
        buttonSMSComplete.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                mPhoneText.setText("인증이 완료되었습니다.");
                fieldVerificationCode.setVisibility(View.GONE);
                buttonVerifyPhone.setEnabled(false);
                buttonResend.setEnabled(false);
                mVerificationInProgress = false;
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

                mVerificationInProgress = false;

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    mPhoneText.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (mVerificationInProgress) {
            startPhoneNumberVerification(mPhoneText.getText().toString());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this, mCallbacks);
        mVerificationInProgress = true;
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this, mCallbacks, token);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            buttonSMSComplete.setEnabled(true);
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                fieldVerificationCode.setError("Invalid code.");
                            }

                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendVerifySMSButton:
                sendVerifySMSButton.setEnabled(false);
                buttonVerifyPhone.setEnabled(true);
                buttonResend.setEnabled(true);
                sendVerifySMSButton.setVisibility(View.GONE);
                String phoneNumber = "+82" + mPhoneText.getText().subSequence(1, mPhoneText.length()).toString();
                startPhoneNumberVerification(phoneNumber);
                break;
            case R.id.buttonVerifyPhone:
                String code = fieldVerificationCode.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    fieldVerificationCode.setError("인증코드를 입력하세요.");
                    return;
                }
                verifyPhoneNumberWithCode(mVerificationId, code);
                break;
            case R.id.buttonResend:
                resendVerificationCode(mPhoneNumber, mResendToken);
                break;
            case R.id.buttonSMSComplete:
                try {

                    mIsValid = "YES";

                    String sendInfo = "signUp";

                    String sendMsg = "email=" + mEmail + "&password=" + mPassword + "&name=" + mName + "&phone=" + mPhoneNumber + "&isadmin=" + mIsAdmin + "&isvalid=" + mIsValid + "&alert=" + mAlert + "&birth=" + mBirth + "&gender=" + mGender;

                    resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

                    if (resultString != null) {
                        String[] splitResult = resultString.split(" ");

                        if (splitResult[0].equals("MEMBER_JOIN_SUCCESS")) {
                            Toast.makeText(getApplicationContext(), "축하합니다! 회원가입이 완료되었습니다.", Toast.LENGTH_LONG ).show();
                            signUpActivity.finish();
                            loginActivity.setEmailPassword(mEmail, mPassword);
                            finish();
                        } else if (splitResult[0].equals("MEMBER_JOIN_FAILED")) {
                            Toast.makeText(getApplicationContext(), "회원가입에 문제가 발생하였습니다.", Toast.LENGTH_LONG ).show();
                            signUpActivity.finish();
                            loginActivity.finish();
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_SHORT).show();
                        signUpActivity.finish();
                        loginActivity.finish();
                        finish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    signUpActivity.finish();
                    loginActivity.finish();
                    finish();
                }
                break;
        }
    }
}