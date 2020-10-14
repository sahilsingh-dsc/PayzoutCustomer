package com.payzout.customer.modules.kyc.residence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.AuthInterface;
import com.payzout.customer.common.Master;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResidenceActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvOwned;
    private TextView tvRented;
    private TextView tvPG;

    private EditText etAddressLine1;
    private EditText etAddressLine2;

    private Spinner spinnerState;

    private EditText etCity;
    private EditText etLocality;
    private EditText etPincode;

    private int residence = 1;
    private int stateSelection = 0;

    private boolean addressLine1Status = false;
    private boolean cityStatus = false;
    private boolean localityStatus = false;
    private boolean pincodeStatus = false;
    private boolean stateStatus = false;

    private TextView tvSubmitDetails;
    private ImageView ivGoBack;

    private Master master;
    private AuthInterface authInterface;
    private static final String TAG = "ResidenceActivity";

    private String[] states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence);
        initView();
    }

    private void initView() {
        tvOwned = findViewById(R.id.tvOwned);
        tvRented = findViewById(R.id.tvRented);
        tvPG = findViewById(R.id.tvPG);

        etAddressLine1 = findViewById(R.id.etAddressLine1);
        etAddressLine2 = findViewById(R.id.etAddressLine2);

        spinnerState = findViewById(R.id.spinnerState);
        etCity = findViewById(R.id.etCity);

        etLocality = findViewById(R.id.etLocality);
        etPincode = findViewById(R.id.etPincode);

        tvSubmitDetails = findViewById(R.id.tvSubmitDetails);
        tvSubmitDetails.setOnClickListener(this);

        tvOwned.setOnClickListener(this);
        tvRented.setOnClickListener(this);
        tvPG.setOnClickListener(this);

        ivGoBack = findViewById(R.id.ivGoBack);
        ivGoBack.setOnClickListener(this);

        etAddressLine1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String addressLine1 = etAddressLine1.getText().toString();
                if (addressLine1.isEmpty()) {
                    addressLine1Status = false;
                    checkForForm();
                } else {
                    addressLine1Status = true;
                    checkForForm();
                }
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
                    checkForForm();
                } else {
                    cityStatus = true;
                    checkForForm();
                }
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

        master = new Master(ResidenceActivity.this);
        authInterface = APIClient.getRetrofitInstance().create(AuthInterface.class);
        states = getResources().getStringArray(R.array.india_states);

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

        setStatesInSpinner();
        doSelectOwnedResidence();
    }

    @Override
    public void onClick(View view) {
        if (view == tvSubmitDetails) {
            submitDetails();
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

        if (view == ivGoBack) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setStatesInSpinner() {
        StateAdapter stateAdapter = new StateAdapter(ResidenceActivity.this, states);
        spinnerState.setAdapter(stateAdapter);
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

    private void checkForForm() {
        if (addressLine1Status && stateStatus && cityStatus && localityStatus && pincodeStatus) {
            tvSubmitDetails.setEnabled(true);
            tvSubmitDetails.setBackground(getResources().getDrawable(R.drawable.bg_button));
            tvSubmitDetails.setTextColor(getResources().getColor(R.color.colorTextH3));
        } else {
            tvSubmitDetails.setEnabled(false);
            tvSubmitDetails.setBackground(getResources().getDrawable(R.drawable.bg_cards_unselected));
            tvSubmitDetails.setTextColor(getResources().getColor(R.color.colorTextH2));
        }
    }

    private void submitDetails() {
        String address1 = etAddressLine1.getText().toString();
        String address2 = etAddressLine2.getText().toString();
        String city = etCity.getText().toString();
        String locality = etLocality.getText().toString();
        String pincode = etLocality.getText().toString();
        Call<ResidenseResponse> call = authInterface.submitResidentialAddress(
                master.getToken(),
                residence,
                address1,
                address2,
                stateSelection,
                city,
                locality,
                pincode
        );

        call.enqueue(new Callback<ResidenseResponse>() {
            @Override
            public void onResponse(Call<ResidenseResponse> call, Response<ResidenseResponse> response) {
                Log.e(TAG, "onResponse: "+response.code()+response.message());
                if (response.code() == 200) {
                    Toast.makeText(ResidenceActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ResidenceActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResidenseResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });

    }



}