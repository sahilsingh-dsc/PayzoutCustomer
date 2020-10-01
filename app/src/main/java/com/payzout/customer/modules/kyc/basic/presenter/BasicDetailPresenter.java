package com.payzout.customer.modules.kyc.basic.presenter;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.AuthInterface;
import com.payzout.customer.modules.kyc.basic.BasicInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicDetailPresenter {
    private static final String TAG = "BasicDetailPresenter";
    private Context context;
    private IBasicDetail iBasicDetail;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    public BasicDetailPresenter(Context context, IBasicDetail iBasicDetail) {
        this.context = context;
        this.iBasicDetail = iBasicDetail;
    }

    public interface IBasicDetail {
        void sendBasicDetails();

        void fetchError();
    }

    public void postDetails(String firstName, String lastName, String email, int gender, String dob,
                            int maritalStatus, String panNo, String aadhaarNo,
                            String fatherName, String motherName, int language, int education) {
        String user_id = firebaseAuth.getCurrentUser().getUid();
        AuthInterface authInterface = APIClient.getRetrofitInstance().create(AuthInterface.class);
        Call<BasicInfo> basicInfoCall = authInterface.sendBasicInfo(user_id, firstName, lastName, email, gender, maritalStatus,  dob, panNo, aadhaarNo, language, education, fatherName, motherName);
        basicInfoCall.enqueue(new Callback<BasicInfo>() {
            @Override
            public void onResponse(Call<BasicInfo> call, Response<BasicInfo> response) {
                Log.e(TAG, "onResponse: "+ response.body());
                if (response.code() == 200){
                    iBasicDetail.sendBasicDetails();
                } else if (response.code() == 400){
                    iBasicDetail.fetchError();
                } else {
                    iBasicDetail.fetchError();
                }
            }

            @Override
            public void onFailure(Call<BasicInfo> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
                iBasicDetail.fetchError();
            }
        });
    }
}
