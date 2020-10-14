package com.payzout.customer.lending.kyc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.payzout.customer.R;
import com.payzout.customer.utils.Constant;

import java.io.IOException;

public class KycDocumentUploadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int TAKE_PICTURE = 1;

    private Uri filePath;

    private MaterialCardView cardSelfie;
    private ImageView ivYourSelfie;
    private MaterialCardView cardPan;
    private ImageView ivPan;
    private MaterialCardView cardAadhaarFront;
    private ImageView ivAadhaarFront;
    private MaterialCardView cardAadhaarBack;
    private ImageView ivAadhaarBack;
    private MaterialCardView cardDL;
    private ImageView ivDL;

    private int photoType = 0;

    private boolean selfieStatus = false;
    private boolean panStatus = false;
    private boolean aadhaarFStatus = false;
    private boolean aadhaarBStatus = false;

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

        cardPan = findViewById(R.id.cardPan);
        cardPan.setOnClickListener(this);
        ivPan = findViewById(R.id.ivPan);

        cardAadhaarFront = findViewById(R.id.cardAadhaarFront);
        cardAadhaarFront.setOnClickListener(this);
        ivAadhaarFront = findViewById(R.id.ivAadhaarFront);

        cardAadhaarBack = findViewById(R.id.cardAadhaarBack);
        cardAadhaarBack.setOnClickListener(this);
        ivAadhaarBack = findViewById(R.id.ivAadhaarBack);

        cardDL = findViewById(R.id.cardDL);
        cardDL.setOnClickListener(this);
        ivDL = findViewById(R.id.ivDL);
    }

    @Override
    public void onClick(View view) {
        if (view == cardSelfie) {
            selfieStatus = false;
            photoType = Constant.PHOTO_SELFIE;
            capturePhoto();
        }
        if (view == cardAadhaarFront) {

        }
    }

    private void capturePhoto() {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivYourSelfie.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}