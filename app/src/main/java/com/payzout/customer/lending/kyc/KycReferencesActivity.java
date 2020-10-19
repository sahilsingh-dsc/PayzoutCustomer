package com.payzout.customer.lending.kyc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.lending.model.AddCustomerReference;
import com.payzout.customer.utils.Constant;
import com.payzout.customer.utils.PayzoutLoading;
import com.payzout.customer.utils.SpinnerDataAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KycReferencesActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivGoBack;

    private Spinner spinnerRelationship1;
    private Spinner spinnerRelationship2;

    private TextView tvReferencesProceed;
    private CustomerInterface customerInterface;
    private EditText etPhoneNum1;
    private EditText etPhoneNum2;
    private EditText etName1;
    private EditText etName2;
    private String type1;
    private String name_1;
    private String number1;
    private String type2;
    private String name_2;
    private String number2;
    private String TAG = "Contacts";
    private static final int REQUEST_CODE = 1;
    private boolean spinner1 = false;
    private boolean name1 = false;
    private boolean spinner2 = false;
    private boolean name2 = false;
    private int refType = 0;

    private LottieAnimationView aniLoadingRef;
    private LinearLayout lvKycReference;
    private PayzoutLoading payzoutLoading;

    private String[] RELATIONSHIP = {"", "Parent", "Brother", "Sister", "Child", "Friend", "Colleague"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_references);
        initView();
    }

    private void initView() {
        ivGoBack = findViewById(R.id.ivGoBack);
        payzoutLoading = PayzoutLoading.getInstance();
        spinnerRelationship1 = findViewById(R.id.spinnerRelationship1);
        spinnerRelationship2 = findViewById(R.id.spinnerRelationship2);
        tvReferencesProceed = findViewById(R.id.tvReferencesProceed);
        customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);
        etName1 = findViewById(R.id.etName1);
        etName2 = findViewById(R.id.etName2);
        etPhoneNum1 = findViewById(R.id.etPhoneNum1);
        etPhoneNum2 = findViewById(R.id.etPhoneNum2);

        etName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refType = Constant.REF_CONTACT_1;
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        etName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refType = Constant.REF_CONTACT_2;
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        setRelationshipInSpinner();

        tvReferencesProceed.setOnClickListener(this);
        ivGoBack.setOnClickListener(this);

    }

    private void setRelationshipInSpinner() {
        SpinnerDataAdapter spinnerDataAdapter = new SpinnerDataAdapter(KycReferencesActivity.this, RELATIONSHIP);

        spinnerRelationship1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    type1 = RELATIONSHIP[position];
                    spinner1 = true;
                    etName1.setEnabled(true);
                    first();
                } else {
                    etName1.setText("");
                    etName1.setHint("Name");
                    etPhoneNum1.setText("");
                    etPhoneNum1.setHint("Phone number");
                    spinner1 = false;
                    etName1.setEnabled(false);
                    first();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRelationship1.setAdapter(spinnerDataAdapter);

        spinnerRelationship2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    type2 = RELATIONSHIP[position];
                    spinner2 = true;
                    etName2.setEnabled(true);
                    first();
                } else {
                    etName2.setText("");
                    etName2.setHint("Name");
                    etPhoneNum2.setText("");
                    etPhoneNum2.setHint("Phone number");
                    spinner2 = false;
                    etName2.setEnabled(false);
                    first();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRelationship2.setAdapter(spinnerDataAdapter);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (REQUEST_CODE):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String contactName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String num = "";
                        if (Integer.valueOf(hasNumber) == 1) {
                            Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);

                            while (numbers.moveToNext()) {
                                num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                if (refType == Constant.REF_CONTACT_1) {
                                    if (!num.isEmpty() && !contactName.isEmpty()) {
                                        number1 = num;
                                        name_1 = contactName;
                                        etPhoneNum1.setText(num);
                                        etName1.setText(contactName);
                                        name1 = true;
                                    } else {
                                        name1 = false;
                                    }

                                    first();


                                } else if (refType == Constant.REF_CONTACT_2) {
                                    if (!num.isEmpty() && !contactName.isEmpty()) {
                                        number2 = num;
                                        name_2 = contactName;
                                        etName2.setText(contactName);
                                        etPhoneNum2.setText(num);
                                        name2 = true;
                                    } else {
                                        name2 = false;
                                    }

                                    first();

                                }

                            }
                        }
                    }
                    break;
                }
        }
    }

    private void first() {
        if (spinner1 && spinner2 && name1 && name2) {
            tvReferencesProceed.setEnabled(true);
            tvReferencesProceed.setBackground(getResources().getDrawable(R.drawable.bg_button));
        } else {
            tvReferencesProceed.setEnabled(false);
            tvReferencesProceed.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
        }
    }


    private void proceedReference() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference reference = db.collection("reference").document();
        String id = reference.getId();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        Call<AddCustomerReference> addCustomerReferenceCall = customerInterface.addCustomerReference(type1, name_1, number1, type2, name_2, number2, id, userId);
        addCustomerReferenceCall.enqueue(new Callback<AddCustomerReference>() {
            @Override
            public void onResponse(Call<AddCustomerReference> call, Response<AddCustomerReference> response) {
                payzoutLoading.hideProgress();
                if (response.code() == 200) {
                    Intent intent = new Intent(KycReferencesActivity.this, KycBankActivity.class);
                    startActivity(intent);
                } else if (response.code() == 400) {
                    Snackbar.make(lvKycReference, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddCustomerReference> call, Throwable t) {
                payzoutLoading.hideProgress();
                Snackbar.make(lvKycReference, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == tvReferencesProceed) {
            payzoutLoading.showProgress(KycReferencesActivity.this);
            proceedReference();
        }
        if (view == ivGoBack) {
            onBackPressed();
        }
    }
}
