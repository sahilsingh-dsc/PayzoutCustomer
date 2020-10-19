package com.payzout.customer.lending.kyc;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.lending.model.CustomerKycDetails;
import com.payzout.customer.utils.Constant;
import com.payzout.customer.utils.DatePickerFragment;
import com.payzout.customer.utils.PayzoutLoading;
import com.payzout.customer.utils.SpinnerDataAdapter;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KycDetailsActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private ImageView ivGoBack;

    private Spinner spinnerState;
    private Spinner spinnerEducation;
    private Spinner spinnerLanguage;

    private EditText etFullName;
    private EditText etDateOfBirth;
    private EditText etEmail;
    private EditText etAddressLine;
    private EditText etCity;
    private EditText etPincode;
    private EditText etCompanyName;
    private EditText etMonthlyIncome;

    private TextView tvMale;
    private TextView tvFemale;
    private TextView tvSingle;
    private TextView tvMarried;
    private TextView tvSalaried;
    private TextView tvSelfEmployed;
    private TextView tvOwned;
    private TextView tvRented;
    private TextView tvPG;
    private TextView tvSubmitDetails;

    private LottieAnimationView aniLoading;

    private LinearLayout lvCreditApplication;

    private int genderSelect = 1;
    private int maritalStatus = 1;
    private int employment_type = 1;
    private int residence = 1;
    private int spinnerSelectedState = 0;
    private int spinnerSelectedEducation = 0;
    private int spinnerSelectedLanguage = 0;
    private String stateSelected;

    private boolean fullNameStatus = false;
    private boolean dobStatus = false;
    private boolean emailStatus = false;
    private boolean educationStatus = false;
    private boolean languageStatus = false;
    private boolean addressStatus = false;
    private boolean stateStatus = false;
    private boolean cityStatus = false;
    private boolean pincodeStatus = false;
    private boolean companyStatus = false;
    private boolean monthlyStatus = false;

    private String[] EDUCATION = {"", "Higher Secondary", "Senior Secondary", "Under Graduation", "Post Graduation"};
    private String[] LANGUAGE = {"", "English", "Hindi", "Others"};
    private String[] STATES;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "KycDetailsActivity";
    private CustomerInterface customerInterface;
    private PayzoutLoading payzoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_details);
        initView();
    }

    private void initView() {
        payzoutLoading = PayzoutLoading.getInstance();
        customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);
        firebaseAuth = FirebaseAuth.getInstance();
        aniLoading = findViewById(R.id.aniLoading);

        ivGoBack = findViewById(R.id.ivGoBack);
        ivGoBack.setOnClickListener(this);

        spinnerState = findViewById(R.id.spinnerState);
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelectedState = adapterView.getSelectedItemPosition();
                stateSelected = STATES[i];
                if (spinnerSelectedState > 0) {
                    stateStatus = true;
                } else {
                    stateStatus = false;
                }
                formStatus();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerEducation = findViewById(R.id.spinnerEducation);
        spinnerEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelectedEducation = adapterView.getSelectedItemPosition();
                if (spinnerSelectedEducation > 0) {
                    educationStatus = true;
                } else {
                    educationStatus = false;
                }
                formStatus();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerLanguage = findViewById(R.id.spinnerLanguage);
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelectedLanguage = adapterView.getSelectedItemPosition();
                if (spinnerSelectedLanguage > 0) {
                    languageStatus = true;
                } else {
                    languageStatus = false;
                }
                formStatus();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etFullName = findViewById(R.id.etFullName);
        etFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String fullName = etFullName.getText().toString();
                if (fullName.isEmpty()) {
                    fullNameStatus = false;
                } else {
                    fullNameStatus = true;
                }
                formStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        etDateOfBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String dob = etDateOfBirth.getText().toString();
                if (dob.isEmpty()) {
                    dobStatus = false;
                } else {
                    dobStatus = true;
                }
                formStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etDateOfBirth.setOnClickListener(this);

        etEmail = findViewById(R.id.etEmail);
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = etEmail.getText().toString();
                if (email.isEmpty()) {
                    emailStatus = false;
                } else {
                    emailStatus = true;
                }
                formStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etAddressLine = findViewById(R.id.etAddressLine);
        etAddressLine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String address = etAddressLine.getText().toString();
                if (address.isEmpty()) {
                    addressStatus = false;
                } else {
                    addressStatus = true;
                }
                formStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etCity = findViewById(R.id.etCity);
        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String city = etCity.getText().toString();
                if (city.isEmpty()) {
                    cityStatus = false;
                } else {
                    cityStatus = true;
                }
                formStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etPincode = findViewById(R.id.etPincode);
        etPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pincode = etPincode.getText().toString();
                if (pincode.isEmpty()) {
                    pincodeStatus = false;
                } else {
                    pincodeStatus = true;
                }
                formStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etCompanyName = findViewById(R.id.etCompanyName);
        etCompanyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String company = etCompanyName.getText().toString();
                if (company.isEmpty()) {
                    companyStatus = false;
                } else {
                    companyStatus = true;
                }
                formStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etMonthlyIncome = findViewById(R.id.etMonthlyIncome);
        etMonthlyIncome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String income = etMonthlyIncome.getText().toString();
                if (income.isEmpty()) {
                    monthlyStatus = false;
                } else {
                    monthlyStatus = true;
                }
                formStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tvMale = findViewById(R.id.tvMale);
        tvFemale = findViewById(R.id.tvFemale);
        tvSingle = findViewById(R.id.tvSingle);
        tvMarried = findViewById(R.id.tvMarried);
        tvSalaried = findViewById(R.id.tvSalaried);
        tvSelfEmployed = findViewById(R.id.tvSelfEmployed);
        tvOwned = findViewById(R.id.tvOwned);
        tvRented = findViewById(R.id.tvRented);
        tvPG = findViewById(R.id.tvPG);
        tvSubmitDetails = findViewById(R.id.tvSubmitDetails);

        lvCreditApplication = findViewById(R.id.lvCreditApplication);

        STATES = getResources().getStringArray(R.array.india_states);

        tvMale.setOnClickListener(this);
        tvFemale.setOnClickListener(this);
        tvSingle.setOnClickListener(this);
        tvMarried.setOnClickListener(this);
        tvSalaried.setOnClickListener(this);
        tvSelfEmployed.setOnClickListener(this);
        tvOwned.setOnClickListener(this);
        tvRented.setOnClickListener(this);
        tvPG.setOnClickListener(this);
        tvSubmitDetails.setOnClickListener(this);

        setStatesInSpinner();
        setEducationInSpinner();
        setLanguageInSpinner();
        doMaleSelected();
        doSingleSelected();
        doSelectSalaried();
        doSelectOwnedResidence();
    }

    private void doSelectSalaried() {
        employment_type = 1;
        tvSalaried.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvSalaried.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvSelfEmployed.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvSelfEmployed.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doSelectSelfEmployed() {
        employment_type = 2;
        tvSelfEmployed.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvSelfEmployed.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvSalaried.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvSalaried.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void setStatesInSpinner() {
        SpinnerDataAdapter spinnerDataAdapter = new SpinnerDataAdapter(KycDetailsActivity.this, STATES);
        spinnerState.setAdapter(spinnerDataAdapter);
    }

    private void setEducationInSpinner() {
        SpinnerDataAdapter spinnerDataAdapter = new SpinnerDataAdapter(KycDetailsActivity.this, EDUCATION);
        spinnerEducation.setAdapter(spinnerDataAdapter);
    }

    private void setLanguageInSpinner() {
        SpinnerDataAdapter spinnerDataAdapter = new SpinnerDataAdapter(KycDetailsActivity.this, LANGUAGE);
        spinnerLanguage.setAdapter(spinnerDataAdapter);
    }

    private void showDOBDialog() {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
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

    private void doMarriedSelected() {
        maritalStatus = 1;
        tvMarried.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvMarried.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvSingle.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvSingle.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doSelectOwnedResidence() {
        residence = 1;
        tvOwned.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvOwned.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvRented.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvRented.setTextColor(getResources().getColor(R.color.colorTextH2));
        tvPG.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvPG.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doSelectRentedResidence() {
        residence = 2;
        tvOwned.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvOwned.setTextColor(getResources().getColor(R.color.colorTextH2));
        tvRented.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvRented.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvPG.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvPG.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doSelectPGResidence() {
        residence = 3;
        tvOwned.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvOwned.setTextColor(getResources().getColor(R.color.colorTextH2));
        tvRented.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvRented.setTextColor(getResources().getColor(R.color.colorTextH2));
        tvPG.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvPG.setTextColor(getResources().getColor(R.color.colorTextH3));
    }

    private void formStatus() {
        if (fullNameStatus && dobStatus && emailStatus && educationStatus && languageStatus && addressStatus && stateStatus && cityStatus && pincodeStatus && companyStatus && monthlyStatus) {
            tvSubmitDetails.setEnabled(true);
            tvSubmitDetails.setBackground(getResources().getDrawable(R.drawable.bg_button));
        } else {
            tvSubmitDetails.setEnabled(false);
            tvSubmitDetails.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
        }
    }

    private void doSubmitDetails() {
        String uid = firebaseAuth.getCurrentUser().getUid();
        String name = etFullName.getText().toString();
        String dob = etDateOfBirth.getText().toString();
        String email = etEmail.getText().toString();
        String address = etAddressLine.getText().toString();
        String city = etCity.getText().toString();
        String pincode = etPincode.getText().toString();
        String company = etCompanyName.getText().toString();
        String income = etMonthlyIncome.getText().toString();

        Call<CustomerKycDetails> call = customerInterface.submitKyc(
                uid,
                name,
                email,
                genderSelect,
                maritalStatus,
                dob,
                residence,
                address,
                city,
                stateSelected,
                Integer.parseInt(pincode),
                spinnerSelectedLanguage,
                spinnerSelectedEducation,
                employment_type,
                company,
                income,
                "Other"
        );


        call.enqueue(new Callback<CustomerKycDetails>() {
            @Override
            public void onResponse(Call<CustomerKycDetails> call, Response<CustomerKycDetails> response) {
                payzoutLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    gotoKycDocumentUpload();
                } else if (response.code() == Constant.HTTP_RESPONSE_BAD_REQUEST) {
                    gotoKycDocumentUpload();
                }
            }

            @Override
            public void onFailure(Call<CustomerKycDetails> call, Throwable t) {
                payzoutLoading.hideProgress();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void gotoKycDocumentUpload() {
        Intent intent = new Intent(KycDetailsActivity.this, KycDocumentUploadActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == ivGoBack) {
            onBackPressed();
        }
        if (view == etDateOfBirth) {
            showDOBDialog();
        }
        if (view == tvMale) {
            doMaleSelected();
        }
        if (view == tvFemale) {
            doFemaleSelected();
        }
        if (view == tvSingle) {
            doSingleSelected();
        }
        if (view == tvMarried) {
            doMarriedSelected();
        }
        if (view == tvSalaried) {
            doSelectSalaried();
        }
        if (view == tvSelfEmployed) {
            doSelectSelfEmployed();
        }
        if (view == tvOwned) {
            doSelectOwnedResidence();
        }
        if (view == tvRented) {
            doSelectRentedResidence();
        }
        if (view == tvPG) {
            doSelectPGResidence();
        }
        if (view == tvSubmitDetails) {
            payzoutLoading.showProgress(KycDetailsActivity.this);
            doSubmitDetails();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        int months = month + 1;
        String month_with_zero = "";
        String day_with_zero = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (months < 10) {
            month_with_zero = "0" + months;
        } else {
            month_with_zero = String.valueOf(months);
        }
        if (dayOfMonth < 10) {
            day_with_zero = "0" + dayOfMonth;
        } else {
            day_with_zero = String.valueOf(dayOfMonth);
        }
        String currentDateString = day_with_zero + "-" + month_with_zero + "-" + year;
        etDateOfBirth.setText(currentDateString);
    }

}