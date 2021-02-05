package com.study.android.signin_signup_sample;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MyPageActivity extends AppCompatActivity {

    private TextView infoBirthDateText;
    private TextView infoEmail;
    private EditText infoName;
    private EditText infoPhone;
    private CheckBox infoGenderMale;
    private CheckBox infoGenderFemale;
    private CheckBox infoAlertCheck;
    private Button buttonInfoUpdate;
    private Button buttonDatePicker;
    private Button buttonUpdatePassword;
    private String birthDate;

    int mYear, mMonth, mDay;
    boolean isConfirmed = false;

    public static MemberDTO memberDTO = MainActivity.memberDTO;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        final Calendar calendar = Calendar.getInstance();


        infoBirthDateText = findViewById(R.id.infoBirthDateText);
        if (memberDTO.getBirth() != null) {
            String[] birthDay = memberDTO.getBirth().split("/");
            String year = birthDay[0];
            String month = birthDay[1];
            String day = birthDay[2];
            infoBirthDateText.setText(year + "년 " + month + "월 " + day + "일");
        } else {
            infoBirthDateText.setVisibility(View.GONE);
            buttonDatePicker.setVisibility(View.INVISIBLE);
        }

        infoEmail = findViewById(R.id.infoEmail);
        infoEmail.setText(memberDTO.getEmail());

        infoName = findViewById(R.id.infoName);
        infoName.setText(memberDTO.getName());

        infoPhone = findViewById(R.id.infoPhone);
        infoPhone.setText(memberDTO.getPhone());

        infoGenderFemale = findViewById(R.id.infoGenderFamale);
        infoGenderMale = findViewById(R.id.infoGenderMale);
        infoAlertCheck = findViewById(R.id.infoAlertCheck);

        if (memberDTO.getGender() != null) {
            if (memberDTO.getGender().equals("FEMALE")) {
                infoGenderFemale.setChecked(true);
            } else if (memberDTO.getGender().equals("MALE")) {
                infoGenderMale.setChecked(true);
            } else {
                infoGenderFemale.setChecked(false);
                infoGenderMale.setChecked(false);
            }
        } else {
            infoGenderMale.setVisibility(View.GONE);
            infoGenderFemale.setVisibility(View.GONE);
        }

        if (memberDTO.getAlert() != null) {
            if (memberDTO.getAlert().equals("YES")) {
                infoAlertCheck.setChecked(true);
            } else if (memberDTO.getAlert().equals("NO")) {
                infoAlertCheck.setChecked(false);
            } else {
                infoAlertCheck.setChecked(false);
            }
        } else {
            infoAlertCheck.setVisibility(View.GONE);
        }

        buttonDatePicker = findViewById(R.id.buttonDatePicker);
        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(MyPageActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                        updateBirthdayTextField();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        buttonInfoUpdate = findViewById(R.id.buttonInfoUpdate);
        buttonInfoUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMemberDataValues();
                passwordConfirmDialogShow();

            }
        });

        buttonUpdatePassword = findViewById(R.id.buttonUpdatePassword);
        buttonUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogShow();
            }
        });
    }

    public void updateBirthdayTextField() {
        infoBirthDateText.setText(String.format("%d년 %d월 %d일", mYear, mMonth + 1, mDay));
        birthDate = mYear + "/" + (mMonth+1) + "/" + mDay;
        memberDTO.setBirth(birthDate);
    }

    public void setMemberDataValues() {
        String name = infoName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            infoName.setError("이름을 입력하세요.");
        } else {
            memberDTO.setName(name);
        }
        if (infoGenderMale.isChecked() && !infoGenderFemale.isChecked()) {
            memberDTO.setGender("MALE");
        } else if (infoGenderFemale.isChecked() && !infoGenderMale.isChecked()) {
            memberDTO.setGender("FEMALE");
        } else {
            infoGenderFemale.setChecked(false);
            infoGenderMale.setChecked(false);
            return;
        }

        if (infoAlertCheck.isChecked()) {
            memberDTO.setAlert("YES");
        } else {
            memberDTO.setAlert("NO");
        }
    }

    public void setNewPassword(String newPassword) {

        if (newPassword != null || !TextUtils.isEmpty(newPassword)) {
            memberDTO.setPassword(newPassword);
        }
    }

    public void passwordConfirmDialogShow() {


        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_password_confirm, null);
        final Button updatePassword = (Button) view.findViewById(R.id.buttonPasswordConfirm);
        final EditText etPasswordConfirm = (EditText) view.findViewById(R.id.etPasswordConfrim);

        AlertDialog.Builder builder = new AlertDialog.Builder(MyPageActivity.this);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPasswordConfirm = etPasswordConfirm.getText().toString();

                if (TextUtils.isEmpty(strPasswordConfirm) || strPasswordConfirm == null) {
                    etPasswordConfirm.setError("비밀번호를 입력하세요.");
                    etPasswordConfirm.requestFocus();
                    return;
                }

                try {
                    String sendInfo = "signIn";

                    String sendMsg = "email=" + memberDTO.getEmail() + "&password=" + strPasswordConfirm;

                    resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

                    if (resultString != null) {
                        String[] splitResult = resultString.split(" ");

                        if (splitResult[0].equals("MEMBER_LOGIN_SUCCESS")) {
                            isConfirmed = true;
                            String sendInfo2 = "updateInfo";

                            String sendMsg2 = "email=" + memberDTO.getEmail() + "&name=" + memberDTO.getName() + "&phone=" + memberDTO.getPhone() + "&alert=" + memberDTO.getAlert() + "&birth=" + memberDTO.getBirth() + "&gender=" + memberDTO.getGender();

                            resultString = new ConnectDB_JSP(sendInfo2).execute(sendMsg2).get();
                            Log.i("업데이트 결과 ", resultString);

                            if (resultString.contains("MEMBER_UPDATE_SUCCESS")) {
                                MainActivity.setNavigationHeader(memberDTO.getName(), memberDTO.getEmail());
                                Toast.makeText(MyPageActivity.this, "정보가 수정되었습니다.", Toast.LENGTH_LONG ).show();
                                dialog.dismiss();
                                finish();
                            } else {
                                Toast.makeText(MyPageActivity.this, "정보 수정에 실패하였습니다.", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                                finish();
                            }

                        } else {
                            Toast.makeText(MyPageActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                            etPasswordConfirm.setError("!");
                            etPasswordConfirm.setText(null);
                            etPasswordConfirm.requestFocus();
                            return;
                        }
                    } else {
                        Toast.makeText(MyPageActivity.this, "비밀번호 확인 과정에서 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    Toast.makeText(MyPageActivity.this, "ERROR: CODE_001 | 관리자에게 문의해 주십시오.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void alertDialogShow() {

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_password, null);
        final Button updatePassword = (Button) view.findViewById(R.id.buttonPasswordUpdate);
        final EditText etPrePassword = (EditText) view.findViewById(R.id.etPasswordConfrim);
        final EditText etPostPassword = (EditText) view.findViewById(R.id.etPostPassword);

        AlertDialog.Builder builder = new AlertDialog.Builder(MyPageActivity.this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPrePassword = etPrePassword.getText().toString();
                String strPostPassword = etPostPassword.getText().toString();
                setNewPassword(strPostPassword);

                try {
                    String sendInfo = "updatePassword";

                    String sendMsg = "email=" + memberDTO.getEmail() + "&prePassword=" + strPrePassword + "&postPassword=" + strPostPassword;

                    resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();
                    Log.i("업데이트 결과 ", resultString);

                    if (resultString.contains("MEMBER_UPDATE_SUCCESS")) {
                        Toast.makeText(MyPageActivity.this, "비밀번호가 변경되었습니다.", Toast.LENGTH_LONG ).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(MyPageActivity.this, "비밀번호 변경에 실패하였습니다.", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

}