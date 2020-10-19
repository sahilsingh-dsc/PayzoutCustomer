package com.payzout.customer.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.CustomerInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUploadService extends Service {

    private CustomerInterface customerInterface;
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "ContactUploadService";

    @Override
    public void onCreate() {
        super.onCreate();
        customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getContactList();
        return START_STICKY;
    }

    private void getContactList() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i(TAG, "Name: " + name);
                        Log.i(TAG, "Phone Number: " + phoneNo);
                        doSaveContact(name, phoneNo);
                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
    }

    private void doSaveContact(final String name, String phoneNo) {
        String uid = firebaseAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference reference = db.collection("contacts").document();
        String contactID = reference.getId();
        Call<CustomerContact> call = customerInterface.saveCustomerContacts(uid, contactID, name, phoneNo);
        call.enqueue(new Callback<CustomerContact>() {
            @Override
            public void onResponse(Call<CustomerContact> call, Response<CustomerContact> response) {
                if (response.code() == 200) {
                    Log.e(TAG, "onResponse: Contact saved " + name);
                } else if (response.code() == 400) {
                    Log.e(TAG, "onResponse: Contact already exist");
                }
            }

            @Override
            public void onFailure(Call<CustomerContact> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
