package com.payzout.customer.lending.kyc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.airbnb.lottie.LottieAnimationView;
import com.payzout.customer.R;
import com.payzout.customer.utils.Constant;
import com.payzout.customer.utils.SpinnerDataAdapter;

public class KycReferencesActivity extends AppCompatActivity {

    private ImageView ivGoBack;

    private Spinner spinnerRelationship1;
    private Spinner spinnerRelationship2;

    private EditText etPhoneNum1;
    private EditText etPhoneNum2;
    private EditText etName1;
    private EditText etName2;
    private String TAG = "Contacts";
    private static final int REQUEST_CODE = 1;

    private int refType = 0;

    private LottieAnimationView aniLoadingRef;
    private LinearLayout lvKycReference;

    private String[] RELATIONSHIP = {"", "Parent", "Brother", "Sister", "Child", "Friend", "Colleague"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_references);
        initView();
    }

    private void initView() {

        spinnerRelationship1 = findViewById(R.id.spinnerRelationship1);
        spinnerRelationship2 = findViewById(R.id.spinnerRelationship2);

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
    }

    private void setRelationshipInSpinner() {
        SpinnerDataAdapter spinnerDataAdapter = new SpinnerDataAdapter(KycReferencesActivity.this, RELATIONSHIP);
        spinnerRelationship1.setAdapter(spinnerDataAdapter);
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
                                if (refType == Constant.REF_CONTACT_1){
                                    etPhoneNum1.setText(num);
                                    etName1.setText(contactName);
                                }else if (refType == Constant.REF_CONTACT_2){
                                    etName2.setText(num);
                                    etPhoneNum2.setText(contactName);
                                }

                            }
                        }
                    }
                    break;
                }
        }
    }
}
