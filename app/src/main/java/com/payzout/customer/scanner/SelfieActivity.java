package com.payzout.customer.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Camera;
import android.os.Bundle;

import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.controls.Facing;
import com.payzout.customer.R;

public class SelfieActivity extends AppCompatActivity {

    private CameraView camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie);
        camera = findViewById(R.id.camera);
        camera.setLifecycleOwner(this);
        initView();
    }

    private void initView() {
        setupCamera();
    }

    private void setupCamera() {
        camera.setFacing(Facing.FRONT);
    }
}