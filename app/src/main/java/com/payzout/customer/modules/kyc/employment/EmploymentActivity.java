package com.payzout.customer.modules.kyc.employment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.AuthInterface;
import com.payzout.customer.common.Master;
import com.payzout.customer.utils.SpinnerDataAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmploymentActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSalaried;
    private TextView tvSelfEmployed;

    private EditText etMonthlyIncome;
    private EditText etCompanyName;
    private EditText etWorkExp;

    private TextView tvCash;
    private TextView tvCheque;
    private TextView tvInBank;

    private TextView tvBSYes;
    private TextView tvBSNo;

    private EditText etAddressLine1;
    private EditText etAddressLine2;
    private Spinner spinnerState;
    private EditText etCity;
    private EditText etLocality;
    private EditText etPincode;

    private TextView tvSubmitDetails;

    private int employment_type = 1;
    private int stateSelection = 0;
    private int bankStatement = 1;
    private int how_salary = 1;

    private boolean incomeStatus = false;
    private boolean companyNameStatus = false;
    private boolean workExpStatus = false;
    private boolean address1Status = false;
    private boolean stateStatus = false;
    private boolean cityStatus = false;
    private boolean localityStatus = false;
    private boolean pincodeStatus = false;

    private String[] states;

    private AuthInterface authInterface;
    private Master master;

    private static final String TAG = "EmploymentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment);
        initView();
    }

    private void initView() {
        tvSalaried = findViewById(R.id.tvSalaried);
        tvSelfEmployed = findViewById(R.id.tvSelfEmployed);

        etMonthlyIncome = findViewById(R.id.etMonthlyIncome);
        etCompanyName = findViewById(R.id.etCompanyName);
        etWorkExp = findViewById(R.id.etWorkExp);

        tvCash = findViewById(R.id.tvCash);
        tvCheque = findViewById(R.id.tvCheque);
        tvInBank = findViewById(R.id.tvInBank);

        tvBSYes = findViewById(R.id.tvBSYes);
        tvBSNo = findViewById(R.id.tvBSNo);

        etAddressLine1 = findViewById(R.id.etAddressLine1);
        etAddressLine2 = findViewById(R.id.etAddressLine2);

        spinnerState = findViewById(R.id.spinnerState);

        etCity = findViewById(R.id.etCity);
        etLocality = findViewById(R.id.etLocality);
        etPincode = findViewById(R.id.etPincode);

        tvSubmitDetails = findViewById(R.id.tvSubmitDetails);
        tvSubmitDetails.setOnClickListener(this);

        tvSalaried.setOnClickListener(this);
        tvSelfEmployed.setOnClickListener(this);
        tvBSYes.setOnClickListener(this);
        tvBSNo.setOnClickListener(this);
        tvCash.setOnClickListener(this);
        tvCheque.setOnClickListener(this);
        tvInBank.setOnClickListener(this);

        states = getResources().getStringArray(R.array.india_states);


        etMonthlyIncome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String income = etMonthlyIncome.getText().toString();
                if (income.isEmpty()) {
                    incomeStatus = false;
                } else {
                    incomeStatus = true;
                }
                checkForForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etCompanyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String companyName = etCompanyName.getText().toString();
                if (companyName.isEmpty()) {
                    companyNameStatus = false;
                } else {
                    companyNameStatus = true;
                }
                checkForForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etWorkExp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String workExp = etWorkExp.getText().toString();
                if (workExp.isEmpty()) {
                    workExpStatus = false;
                } else {
                    workExpStatus = true;
                }
                checkForForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etAddressLine1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String addressLine1 = etAddressLine1.getText().toString();
                if (addressLine1.isEmpty()) {
                    address1Status = false;
                } else {
                    address1Status = true;
                }
                checkForForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
                checkForForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etLocality.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String locality = etLocality.getText().toString();
                if (locality.isEmpty()) {
                    localityStatus = false;
                } else {
                    localityStatus = true;
                }
                checkForForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
                checkForForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    stateSelection = i;
                    checkForForm();
                    stateStatus = true;
                } else {
                    checkForForm();
                    stateStatus = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        authInterface = APIClient.getRetrofitInstance().create(AuthInterface.class);
        master = new Master(EmploymentActivity.this);

        doSelectSalaried();
        doSelectBSYes();
        doSelectCash();
        setStatesInSpinner();

    }

    private void setStatesInSpinner() {
        SpinnerDataAdapter spinnerDataAdapter = new SpinnerDataAdapter(EmploymentActivity.this, states);
        spinnerState.setAdapter(spinnerDataAdapter);
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

    private void doSelectBSYes() {
        bankStatement = 1;
        tvBSYes.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvBSYes.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvBSNo.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvBSNo.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doSelectBSNo() {
        bankStatement = 0;
        tvBSNo.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvBSNo.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvBSYes.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvBSYes.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doSelectCash() {
        how_salary = 1;
        tvCash.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvCash.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvCheque.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvCheque.setTextColor(getResources().getColor(R.color.colorTextH2));
        tvInBank.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvInBank.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doSelectCheque() {
        how_salary = 2;
        tvCash.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvCash.setTextColor(getResources().getColor(R.color.colorTextH2));
        tvCheque.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvCheque.setTextColor(getResources().getColor(R.color.colorTextH3));
        tvInBank.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvInBank.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void doSelectInBank() {
        how_salary = 3;
        tvCash.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvCash.setTextColor(getResources().getColor(R.color.colorTextH2));
        tvCheque.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
        tvCheque.setTextColor(getResources().getColor(R.color.colorTextH2));
        tvInBank.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvInBank.setTextColor(getResources().getColor(R.color.colorTextH3));
    }

    @Override
    public void onClick(View view) {
        if (view == tvSalaried) {
            doSelectSalaried();
        }

        if (view == tvSelfEmployed) {
            doSelectSelfEmployed();
        }

        if (view == tvSubmitDetails) {
            doSubmitDetails();
        }

        if (view == tvBSYes) {
            doSelectBSYes();
        }

        if (view == tvBSNo) {
            doSelectBSNo();
        }

        if (view == tvCash) {
            doSelectCash();
        }

        if (view == tvCheque) {
            doSelectCheque();
        }

        if (view == tvInBank) {
            doSelectInBank();
        }
    }

    private void doSubmitDetails() {
        String monthly = etMonthlyIncome.getText().toString();
        String companyName = etCompanyName.getText().toString();
        String workExp = etWorkExp.getText().toString();
        String address1 = etAddressLine1.getText().toString();
        String address2 = etAddressLine2.getText().toString();
        String city = etCity.getText().toString();
        String locality = etLocality.getText().toString();
        String pincode = etLocality.getText().toString();

        Call<EmploymentResponse> call = authInterface.submitEmploymentDetails(
                master.getToken(),
                employment_type,
                monthly,
                companyName,
                workExp,
                bankStatement,
                address1,
                address2,
                stateSelection,
                city
        );

        call.enqueue(new Callback<EmploymentResponse>() {
            @Override
            public void onResponse(Call<EmploymentResponse> call, Response<EmploymentResponse> response) {
                Log.e(TAG, "onResponse: "+response.code()+" "+response.message()+" "+response.body());
                if (response.code() == 200) {
                    Toast.makeText(EmploymentActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EmploymentActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EmploymentResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    private void checkForForm() {
        int pincodeLength = etPincode.getText().toString().length();
        if (incomeStatus && companyNameStatus && workExpStatus && address1Status && stateStatus && cityStatus && localityStatus && pincodeStatus && pincodeLength == 6) {
            tvSubmitDetails.setEnabled(true);
            tvSubmitDetails.setBackground(getResources().getDrawable(R.drawable.bg_button));
            tvSubmitDetails.setTextColor(getResources().getColor(R.color.colorTextH3));
        } else {
            tvSubmitDetails.setEnabled(false);
            tvSubmitDetails.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
            tvSubmitDetails.setTextColor(getResources().getColor(R.color.colorTextH2));
        }
    }

}