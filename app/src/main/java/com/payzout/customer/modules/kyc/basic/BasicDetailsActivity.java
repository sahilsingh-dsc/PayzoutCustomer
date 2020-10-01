package com.payzout.customer.modules.kyc.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.payzout.customer.R;
import com.payzout.customer.modules.kyc.EductionAdapter;
import com.payzout.customer.modules.kyc.KycActivity;
import com.payzout.customer.modules.kyc.LanguageAdapter;
import com.payzout.customer.modules.kyc.basic.presenter.BasicDetailPresenter;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicDetailsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, BasicDetailPresenter.IBasicDetail {
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etDOB;
    private EditText etPanCardNo;
    private EditText etAadharCardNo;
    private EditText etFatherName;
    private EditText etMotherName;
    private TextView tvSubmitDetails;
    private TextView tvMale;
    private TextView tvFemale;
    private TextView tvSingle;
    private TextView tvMarried;
    private int spinnerSelectedEducation = 0;
    private int spinnerSelectedLanguage = 0;
    private LottieAnimationView lottieAnimation;
    private boolean et1State = false;
    private boolean et2State = false;
    private boolean et3State = false;
    private boolean et4State = false;
    private boolean et5State = false;
    private boolean et6State = false;
    private boolean et7State = false;
    private boolean et8State = false;
    private int genderSelect = 0;
    private int maritalStatus = 0;
    private String[] educationStatus = {"", "Higher Secondary", "Senior Secondary", "Under Graduation", "Post Graduation"};
    private String[] language = {"", "English", "Hindi", "Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);

        initViews();
        doMaleSelected();
        doSingleSelected();
    }

    private void initViews() {

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etDOB = findViewById(R.id.etDOB);
        etPanCardNo = findViewById(R.id.etPanCardNo);
        etAadharCardNo = findViewById(R.id.etAadharCardNo);
        etFatherName = findViewById(R.id.etFatherName);
        etMotherName = findViewById(R.id.etMotherName);
        tvSubmitDetails = findViewById(R.id.tvSubmitDetails);
        tvMale = findViewById(R.id.tvMale);
        tvFemale = findViewById(R.id.tvFemale);
        tvSingle = findViewById(R.id.tvSingle);
        tvMarried = findViewById(R.id.tvMarried);
        Spinner spinnerEducation = findViewById(R.id.spinnerEducation);
        Spinner spinnerLanguage = findViewById(R.id.spinnerLanguage);


        textValidation();

        EductionAdapter eductionAdapter = new EductionAdapter(BasicDetailsActivity.this, educationStatus);
        spinnerEducation.setAdapter(eductionAdapter);

        LanguageAdapter languageAdapter = new LanguageAdapter(BasicDetailsActivity.this, language);
        spinnerLanguage.setAdapter(languageAdapter);


        tvSubmitDetails.setOnClickListener(this);
        spinnerEducation.setOnItemSelectedListener(this);
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    spinnerSelectedLanguage = 0;
                    return;
                } else if (position == 1) {
                    spinnerSelectedLanguage = 1;
                    return;
                } else if (position == 2) {
                    spinnerSelectedLanguage = 2;
                    return;
                } else if (position == 3) {
                    spinnerSelectedLanguage = 3;
                    return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tvMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMaleSelected();

            }
        });
        tvFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFemaleSelected();
            }
        });
        tvSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSingleSelected();
            }
        });
        tvMarried.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMarriedSelected();
            }
        });
        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void textValidation() {
        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String firstName = etFirstName.getText().toString();
                if (firstName.isEmpty()) {
                    et1State = false;
                    enableButton(false);
                } else {
                    et1State = true;
                    if (et1State && et2State && et3State && et4State && et5State && et6State && et7State && et8State) {
                        enableButton(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String lastName = etLastName.getText().toString();
                if (lastName.isEmpty()) {
                    et2State = false;
                    enableButton(false);
                } else {
                    et2State = true;
                    if (et1State && et2State && et3State && et4State && et5State && et6State && et7State && et8State) {
                        enableButton(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = etEmail.getText().toString();
                if (email.isEmpty()) {
                    et3State = false;
                    enableButton(false);
                } else {
                    et3State = true;
                    if (et1State && et2State && et3State && et4State && et5State && et6State && et7State && et8State) {
                        enableButton(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etDOB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String dob = etDOB.getText().toString();
                if (dob.isEmpty()) {
                    et4State = false;
                    enableButton(false);
                } else {
                    et4State = true;
                    if (et1State && et2State && et3State && et4State && et5State && et6State && et7State && et8State) {
                        enableButton(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPanCardNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pan = etPanCardNo.getText().toString();
                if (pan.isEmpty()) {
                    et5State = false;
                    enableButton(false);
                } else {
                    et5State = true;
                    if (et1State && et2State && et3State && et4State && et5State && et6State && et7State && et8State) {
                        enableButton(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etAadharCardNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String aadhar = etAadharCardNo.getText().toString();
                if (aadhar.isEmpty()) {
                    et6State = false;
                    enableButton(false);
                } else {
                    et6State = true;
                    if (et1State && et2State && et3State && et4State && et5State && et6State && et7State && et8State) {
                        enableButton(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etFatherName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String father = etFatherName.getText().toString();
                if (father.isEmpty()) {
                    et7State = false;
                    enableButton(false);
                } else {
                    et7State = true;
                    if (et1State && et2State && et3State && et4State && et5State && et6State && et7State && et8State) {
                        enableButton(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etMotherName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mother = etMotherName.getText().toString();
                if (mother.isEmpty()) {
                    et8State = false;
                    enableButton(false);
                } else {
                    et8State = true;
                    if (et1State && et2State && et3State && et4State && et5State && et6State && et7State && et8State) {
                        enableButton(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == tvSubmitDetails) {
            doUiValidation();
        }

    }

    private void doUiValidation() {
        String firstName = etFirstName.getText().toString();

        if (TextUtils.isEmpty(firstName)) {
            etFirstName.requestFocus();
            etFirstName.setError("First Name Required");
            return;
        }
        String lastName = etLastName.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            etLastName.requestFocus();
            etLastName.setError("Last Name Required");
            return;
        }

        String email = etEmail.getText().toString().trim();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.requestFocus();
            etEmail.setError("Enter Correct Email");
            return;
        }

        String dob = etDOB.getText().toString();
        if (TextUtils.isEmpty(dob)) {
            etDOB.requestFocus();
            etDOB.setError("DOB is Required");
            return;
        }

        String panCard = etPanCardNo.getText().toString();
        Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
        Matcher matcher = pattern.matcher(panCard);
        if (!matcher.matches() || panCard.isEmpty()) {
            etPanCardNo.requestFocus();
            etPanCardNo.setError("Enter Correct PAN Card No.");
            return;
        }

        String aadharNo = etAadharCardNo.getText().toString();
        String regex = "^[2-9]{1}[0-9]{3}[0-9]{4}[0-9]{4}$";
        Pattern p = Pattern.compile(regex);
        matcher = p.matcher(aadharNo);
        if (!matcher.matches() || aadharNo.isEmpty()) {
            etAadharCardNo.requestFocus();
            etAadharCardNo.setError("Enter Correct Aadhar No.");
            return;
        }

        String fatherName = etFatherName.getText().toString();
        if (TextUtils.isEmpty(fatherName)) {
            etFatherName.requestFocus();
            etFatherName.setError("Father Name is Required");
            return;
        }

        String motherName = etMotherName.getText().toString();
        if (TextUtils.isEmpty(motherName)) {
            etMotherName.requestFocus();
            etMotherName.setError("Mother Name Required");
            return;
        }
        lottieAnimation.playAnimation();
        BasicDetailPresenter basicDetailPresenter = new BasicDetailPresenter(BasicDetailsActivity.this, BasicDetailsActivity.this);
        basicDetailPresenter.postDetails(firstName, lastName, email, genderSelect, dob,
                maritalStatus, panCard, aadharNo, fatherName, motherName, spinnerSelectedLanguage, spinnerSelectedEducation);
    }

    private void enableButton(Boolean inputState) {
        if (inputState) {
            tvSubmitDetails.setEnabled(true);
            tvSubmitDetails.setBackground(getResources().getDrawable(R.drawable.bg_button));
        } else {
            tvSubmitDetails.setEnabled(false);
            tvSubmitDetails.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position == 1) {
            spinnerSelectedEducation = 1;
            return;
        } else if (position == 2) {
            spinnerSelectedEducation = 2;
            return;
        } else if (position == 3) {
            spinnerSelectedEducation = 3;

            return;
        } else if (position == 4) {
            spinnerSelectedEducation = 4;

            return;
        } else {
            spinnerSelectedEducation = 0;
            return;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void doMaleSelected() {
        genderSelect = 1;
        tvMale.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvMale.setEnabled(true);
        tvMale.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvFemale.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvFemale.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doFemaleSelected() {
        genderSelect = 2;
        tvFemale.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvFemale.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvMale.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvMale.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doSingleSelected() {
        maritalStatus = 2;
        tvSingle.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvSingle.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvMarried.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvMarried.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void doMarriedSelected() {
        maritalStatus = 1;
        tvMarried.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvMarried.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvSingle.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvSingle.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(BasicDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                if (i1 + 1 <= 9) {
                    etDOB.setText(i2 + "/" + "0" + (i1 + 1) + "/" + i);
                } else {
                    etDOB.setText(i2 + "/" + (i1 + 1) + "/" + i);
                }

            }
        }, day, month, year);
        datePickerDialog.show();
    }

    @Override
    public void sendBasicDetails() {

        Toast.makeText(this, "sent", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(BasicDetailsActivity.this, KycActivity.class);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void fetchError() {

        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}