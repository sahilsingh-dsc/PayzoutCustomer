package com.payzout.customer.lending.kyc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.common.Master;
import com.payzout.customer.lending.model.UserAB;
import com.payzout.customer.lending.model.UserAF;
import com.payzout.customer.lending.model.UserDL;
import com.payzout.customer.lending.model.UserPAN;
import com.payzout.customer.lending.model.UserSelfie;
import com.payzout.customer.utils.Constant;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KycDocumentUploadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int TAKE_PICTURE = 1;

    private MaterialCardView cardSelfie;
    private ImageView ivYourSelfie;
    private MaterialCardView cardPan;
    private MaterialCardView cardBS;
    private ImageView ivPan;
    private MaterialCardView cardAadhaarFront;
    private ImageView ivAadhaarFront;
    private MaterialCardView cardAadhaarBack;
    private ImageView ivAadhaarBack;
    private MaterialCardView cardDL;
    private ImageView ivDL;
    private TextView tvSubmitForReview;
    private ImageView ivBankStatement;
    private String fileUrl;
    private FirebaseStorage storage;
    private int photoType = 0;
    private Master master;
    private String id;
    private ProgressBar progressPhoto;
    private ProgressBar progressPan;
    private ProgressBar progressBack;
    private ProgressBar progressFront;
    private ProgressBar progressDL;
    private ProgressBar progressBS;
    private CustomerInterface customerInterface;
    private boolean selfieStatus = false;
    private boolean panStatus = false;
    private boolean aadhaarFStatus = false;
    private boolean aadhaarBStatus = false;
    private boolean dlStatus = false;
    private boolean bsStatus = false;
    private LinearLayout lvDoc;

    private ImageView ivGoBack;

    private static final String TAG = "KycDocumentUploadActivi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_document_upload);
        initView();
    }

    private void initView() {
        cardSelfie = findViewById(R.id.cardSelfie);
        cardSelfie.setOnClickListener(this);
        ivYourSelfie = findViewById(R.id.ivYourSelfie);
        tvSubmitForReview = findViewById(R.id.tvSubmitForReview);


        progressPhoto = findViewById(R.id.progressPhoto);
        progressPan = findViewById(R.id.progressPan);
        progressBack = findViewById(R.id.progressBack);
        progressFront = findViewById(R.id.progressFront);
        progressDL = findViewById(R.id.progressDL);
        progressBS = findViewById(R.id.progressBS);

        storage = FirebaseStorage.getInstance();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        id = auth.getCurrentUser().getUid();

        customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);

        cardPan = findViewById(R.id.cardPan);
        cardPan.setOnClickListener(this);
        ivPan = findViewById(R.id.ivPan);

        cardAadhaarFront = findViewById(R.id.cardAadhaarFront);
        cardAadhaarFront.setOnClickListener(this);
        ivAadhaarFront = findViewById(R.id.ivAadhaarFront);

        master = new Master(KycDocumentUploadActivity.this);

        cardAadhaarBack = findViewById(R.id.cardAadhaarBack);
        cardAadhaarBack.setOnClickListener(this);
        ivAadhaarBack = findViewById(R.id.ivAadhaarBack);

        cardDL = findViewById(R.id.cardDL);
        cardDL.setOnClickListener(this);
        ivDL = findViewById(R.id.ivDL);

        cardBS = findViewById(R.id.cardBS);
        cardBS.setOnClickListener(this);
        ivBankStatement = findViewById(R.id.ivBankStatement);

        lvDoc = findViewById(R.id.lvDoc);
        ivGoBack = findViewById(R.id.ivGoBack);

        ivGoBack.setOnClickListener(this);
        tvSubmitForReview.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == cardSelfie) {
            selfieStatus = false;
            photoType = Constant.PHOTO_SELFIE;
            capturePhoto();
        }

        if (view == cardPan) {
            panStatus = false;
            photoType = Constant.PHOTO_PAN;
            capturePan();
        }
        if (view == cardDL) {
            dlStatus = false;
            photoType = Constant.PHOTO_DL;
            captureDL();
        }
        if (view == cardBS) {
            bsStatus = false;
            photoType = Constant.PDF_BS;
            openDocument();
        }
        if (view == cardAadhaarFront) {
            aadhaarFStatus = false;
            photoType = Constant.PHOTO_AF;
            captureAF();
        }
        if (view == cardAadhaarBack) {
            aadhaarBStatus = false;
            photoType = Constant.PHOTO_AB;
            captureAB();
        }
        if (view == tvSubmitForReview) {
            gotoContactReference();
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

    private void capturePhoto() {
        ImagePicker.Companion.with(KycDocumentUploadActivity.this)
                .cameraOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    private void capturePan() {
        ImagePicker.Companion.with(KycDocumentUploadActivity.this)
                .cameraOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    private void captureAF() {
        ImagePicker.Companion.with(KycDocumentUploadActivity.this)
                .cameraOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    private void captureAB() {
        ImagePicker.Companion.with(KycDocumentUploadActivity.this)
                .cameraOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    private void captureDL() {
        ImagePicker.Companion.with(KycDocumentUploadActivity.this)
                .cameraOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    private void openDocument() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select PDF"), Constant.PDF_BS);
    }

    private void gotoContactReference() {
        startActivity(new Intent(KycDocumentUploadActivity.this, KycReferencesActivity.class));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri filePath = data.getData();
            if (photoType == Constant.PHOTO_SELFIE) {
                if (filePath != null) {
                    progressPhoto.setVisibility(View.VISIBLE);
                    ivYourSelfie.setImageURI(filePath);
                    uploadSelfieToFS(filePath);
                }
            } else if (photoType == Constant.PHOTO_PAN) {
                if (filePath != null) {
                    progressPan.setVisibility(View.VISIBLE);
                    ivPan.setImageURI(filePath);
                    uploadPanImageToFS(filePath);
                }
            } else if (photoType == Constant.PHOTO_DL) {
                if (filePath != null) {
                    progressDL.setVisibility(View.VISIBLE);
                    ivDL.setImageURI(filePath);
                    uploadDLToFs(filePath);
                }
            } else if (photoType == Constant.PHOTO_AF) {
                if (filePath != null) {
                    progressFront.setVisibility(View.VISIBLE);
                    ivAadhaarFront.setImageURI(filePath);
                    uploadAFToFS(filePath);
                }
            } else if (photoType == Constant.PHOTO_AB) {
                if (filePath != null) {
                    progressBack.setVisibility(View.VISIBLE);
                    ivAadhaarBack.setImageURI(filePath);
                    uploadABToFS(filePath);
                }
            } else if (photoType == Constant.PDF_BS) {
                if (filePath != null) {
                    ivBankStatement.setImageURI(filePath);
                    uploadBSToFS(filePath);
                }
            }
        }
    }

    private void uploadSelfieToFS(Uri filePath) {
        StorageReference reference = storage.getReference();
        StorageReference imageFolder = reference.child("selfie");
        final StorageReference imageRef = imageFolder.child("images/"
                + UUID.randomUUID().toString());
        imageRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        fileUrl = uri.toString();
                        sendSelfieToApi(id, fileUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }
        });
    }

    private void uploadPanImageToFS(Uri filePath) {
        StorageReference reference = storage.getReference();
        StorageReference imageFolder = reference.child("pan_card");
        final StorageReference imageRef = imageFolder.child("images/" +
                UUID.randomUUID().toString());
        imageRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        fileUrl = uri.toString();
                        sendPanToApi(id, fileUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();

            }
        });

    }

    private void uploadAFToFS(Uri filePath) {
        StorageReference reference = storage.getReference();
        StorageReference imageFolder = reference.child("aadhaar_front");
        final StorageReference imageRef = imageFolder.child("images/" +
                UUID.randomUUID().toString());
        imageRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressPan.setVisibility(View.INVISIBLE);
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        fileUrl = uri.toString();
                        sendAFToApi(id, fileUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void uploadABToFS(Uri filePath) {
        StorageReference reference = storage.getReference();
        StorageReference imageFolder = reference.child("aadhaar_back");
        final StorageReference imageRef = imageFolder.child("images/" +
                UUID.randomUUID().toString());
        imageRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressPan.setVisibility(View.INVISIBLE);
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        fileUrl = uri.toString();
                        sendABToApi(id, fileUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void uploadDLToFs(final Uri filePath) {
        StorageReference reference = storage.getReference();
        StorageReference imageFolder = reference.child("driving_license");
        final StorageReference imageRef = imageFolder.child("images/" +
                UUID.randomUUID().toString());
        imageRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        fileUrl = uri.toString();
                        sendDlToApi(id, fileUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();

            }
        });

    }

    private void uploadBSToFS(Uri filePath) {
        StorageReference reference = storage.getReference();
        StorageReference imageFolder = reference.child("bank_statement");
        final StorageReference imageRef = imageFolder.child("pdf/" +
                UUID.randomUUID().toString());
        imageRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressPan.setVisibility(View.INVISIBLE);
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        fileUrl = uri.toString();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void sendSelfieToApi(String id, String fileUrl) {
        Call<UserSelfie> userSelfieCall = customerInterface.submitSelfie(id, fileUrl);
        userSelfieCall.enqueue(new Callback<UserSelfie>() {
            @Override
            public void onResponse(Call<UserSelfie> call, Response<UserSelfie> response) {
                if (response.code() == 200) {
                    progressPhoto.setVisibility(View.INVISIBLE);
                    selfieStatus = true;
                    formValidation();
                } else if (response.code() == 400) {
                    Snackbar.make(lvDoc, "Invalid Document", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserSelfie> call, Throwable t) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void sendPanToApi(String id, String fileUrl) {
        Call<UserPAN> call = customerInterface.submitUserPan(id, fileUrl);
        call.enqueue(new Callback<UserPAN>() {
            @Override
            public void onResponse(Call<UserPAN> call, Response<UserPAN> response) {
                if (response.code() == 200) {
                    progressPan.setVisibility(View.INVISIBLE);
                    panStatus = true;
                    formValidation();
                } else if (response.code() == 400) {
                    Snackbar.make(lvDoc, "Invalid Document", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserPAN> call, Throwable t) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void sendAFToApi(String id, String fileUrl) {
        Call<UserAF> userAFCall = customerInterface.submitUserAF(id, fileUrl);
        userAFCall.enqueue(new Callback<UserAF>() {
            @Override
            public void onResponse(Call<UserAF> call, Response<UserAF> response) {
                Log.e(TAG, "onResponse: " + response.code() + response.message());
                if (response.code() == 200) {
                    progressFront.setVisibility(View.INVISIBLE);
                    aadhaarFStatus = true;
                    formValidation();
                } else if (response.code() == 400) {
                    Snackbar.make(lvDoc, "Invalid Document", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserAF> call, Throwable t) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void sendABToApi(String id, String fileUrl) {
        Call<UserAB> userABCall = customerInterface.submitUserAB(id, fileUrl);
        userABCall.enqueue(new Callback<UserAB>() {
            @Override
            public void onResponse(Call<UserAB> call, Response<UserAB> response) {
                Log.e(TAG, "onResponse: " + response.code() + response.message());
                if (response.code() == 200) {
                    progressBack.setVisibility(View.INVISIBLE);
                    aadhaarBStatus = true;
                    formValidation();
                } else if (response.code() == 400) {
                    Snackbar.make(lvDoc, "Invalid Document", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserAB> call, Throwable t) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void sendDlToApi(String id, String fileUrl) {
        Call<UserDL> userDLCall = customerInterface.submitUserDl(id, fileUrl, "1");
        userDLCall.enqueue(new Callback<UserDL>() {
            @Override
            public void onResponse(Call<UserDL> call, Response<UserDL> response) {
                Log.e(TAG, "onResponse: " + response.code() + response.message());
                if (response.code() == 200) {
                    progressDL.setVisibility(View.INVISIBLE);
                    dlStatus = true;
                    Snackbar.make(lvDoc, "Driving licence uploaded successfully", Snackbar.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    progressDL.setVisibility(View.INVISIBLE);
                    Snackbar.make(lvDoc, "Invalid Document", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDL> call, Throwable t) {
                Snackbar.make(lvDoc, "Something went wrong, Try again", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void formValidation() {
        if (selfieStatus && aadhaarFStatus && panStatus && aadhaarBStatus) {
            tvSubmitForReview.setEnabled(true);
            tvSubmitForReview.setBackground(getResources().getDrawable(R.drawable.btn_bg_gradient));
        } else {
            tvSubmitForReview.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
            tvSubmitForReview.setEnabled(false);
        }
    }
}