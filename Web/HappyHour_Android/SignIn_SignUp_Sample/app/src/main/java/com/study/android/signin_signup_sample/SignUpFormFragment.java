package com.study.android.signin_signup_sample;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class SignUpFormFragment extends Fragment {

    private static final String TAG = "SignUpForm";

    public static SignUpFormFragment getInstance() {
        return new SignUpFormFragment();
    }

    // Member Data Items
    private String email;
    private String password;
    private String passwordConfirm;
    private String name;
    private String phoneNumber;
    private String isValid;
    private String isAdmin;
    private String birthDate;
    private String gender;
    private String alertCheck;

    private Button datePickerButton;
    private Button verifyButton;
    private Button fieldCustomer;
    private Button fieldAdmin;

    private ScrollView loginForm;
    private TextView birthDateText;
    private EditText fieldEmail;
    private EditText fieldPassword;
    private EditText fieldPasswordConfirm;
    private EditText fieldName;
    private EditText fieldPhoneNumber;
    private CheckBox fieldGenderMale;
    private CheckBox fieldGenderFemale;
    private CheckBox fieldAlertCheck;

    private LinearLayout memberType;
    private LinearLayout emailLoginForm;
    private LinearLayout optionalLoginForm;

    int mYear, mMonth, mDay;

    public SignUpFormFragment() { }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState)
    {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_sign_up_form, container, false);

        final Calendar calendar = Calendar.getInstance();

        // Views
        fieldEmail = rootView.findViewById(R.id.fieldEmail);
        fieldPassword = rootView.findViewById(R.id.feildPassword);
        fieldPasswordConfirm = rootView.findViewById(R.id.feildPasswordConfirm);
        fieldName = rootView.findViewById(R.id.fieldName);
        fieldPhoneNumber = rootView.findViewById(R.id.fieldPhoneNumber);
        fieldGenderMale = rootView.findViewById(R.id.fieldGenderMale);
        fieldGenderFemale = rootView.findViewById(R.id.fieldGenderFamale);
        fieldAlertCheck = rootView.findViewById(R.id.fieldAlertCheck);
        loginForm = rootView.findViewById(R.id.loginForm);
        memberType = rootView.findViewById(R.id.fieldMemberType);
        emailLoginForm = rootView.findViewById(R.id.email_login_form);
        optionalLoginForm = rootView.findViewById(R.id.optional_login_form);
        birthDateText = rootView.findViewById(R.id.fieldBirthDateText);

        // Visibility Settings
        memberType.setVisibility(View.VISIBLE);
        emailLoginForm.setVisibility(View.GONE);
        optionalLoginForm.setVisibility(View.GONE);

        // Buttons
        datePickerButton = rootView.findViewById(R.id.datePickerButton);
        fieldCustomer = rootView.findViewById(R.id.fieldCustomer);
        fieldAdmin = rootView.findViewById(R.id.fieldAdmin);
        verifyButton = rootView.findViewById(R.id.verifyButton);

        // OnClickListeners
        fieldCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberType.setVisibility(View.GONE);
                emailLoginForm.setVisibility(View.VISIBLE);
                optionalLoginForm.setVisibility(View.VISIBLE);
                isAdmin = "NO";
            }
        });

        fieldAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberType.setVisibility(View.GONE);
                emailLoginForm.setVisibility(View.VISIBLE);
                optionalLoginForm.setVisibility(View.GONE);
                isAdmin = "YES";
            }
        });

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    setMemberDataValues();
                }

                Intent intent = new Intent(getContext(), SignUpActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("phone", phoneNumber);
                intent.putExtra("name", name);
                intent.putExtra("isAdmin", isAdmin);
                intent.putExtra("isValid", isValid);
                intent.putExtra("birth", birthDate);
                intent.putExtra("gender", gender);
                intent.putExtra("alert", alertCheck);
                startActivity(intent);
            }
        });

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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

        return rootView;
    }

    public void updateBirthdayTextField() {
        birthDateText.setText(String.format("%d년 %d월 %d일", mYear, mMonth + 1, mDay));
        birthDate = mYear + "/" + (mMonth+1) + "/" + mDay;
    }

    public void setMemberDataValues() {

        isValid = "NO";

        if (fieldGenderMale.isChecked()) {
            gender = "MALE";
        } else if (fieldGenderFemale.isChecked()) {
            gender = "FEMALE";
        } else {
            gender = null;
        }

        if (fieldAlertCheck.isChecked()) {
            alertCheck = "YES";
        } else {
            alertCheck = "NO";
        }

        Log.d(TAG, email + " | " + password + " | " + passwordConfirm + " | " +
                   name + " | " + phoneNumber + " | " + isValid + " | " + isAdmin + " | " +
                   birthDate + " | " + gender + " | " + alertCheck);
    }

    private boolean validateForm() {
        boolean valid = true;

        String _email = fieldEmail.getText().toString();
        String _password = fieldPassword.getText().toString();
        String _passwordConfirm = fieldPasswordConfirm.getText().toString();
        String _name = fieldName.getText().toString();
        String _phone = fieldPhoneNumber.getText().toString();

        if (TextUtils.isEmpty(_email) || !_email.contains("@")) {
            fieldEmail.setError("이메일을 입력해 주세요.");
            fieldEmail.requestFocus();
            return false;
        } else {
            fieldEmail.setError(null);
            email = _email;
        }

        if (TextUtils.isEmpty(_password) || TextUtils.isDigitsOnly(_password) || _password.length() < 6 ) {
            fieldPassword.setError("비밀번호는 6자리 이상의 영문자와 숫자의 조합이어야 합니다.");
            fieldPassword.requestFocus();
            return false;
        } else {
            fieldPassword.setError(null);
            password = _password;
        }

        if (!_passwordConfirm.equals(_password)) {
            fieldPasswordConfirm.setError("비밀번호와 일치하지 않습니다.");
            fieldPasswordConfirm.requestFocus();
            return false;
        } else  {
            fieldPasswordConfirm.setError(null);
            passwordConfirm = _passwordConfirm;
        }

        if (TextUtils.isEmpty(_name)) {
            fieldName.setError("이름은 필수 입력 사항입니다.");
            fieldName.requestFocus();
            return false;
        } else  {
            name = _name;
        }

        if (TextUtils.isEmpty(_phone)) {
            fieldPhoneNumber.setError("전화번호를 입력해 주세요.");
            fieldPhoneNumber.requestFocus();
            return false;
        } else {
            phoneNumber = _phone;
        }

        return valid;
    }
}