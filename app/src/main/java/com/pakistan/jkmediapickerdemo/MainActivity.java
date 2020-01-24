package com.pakistan.jkmediapickerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pakistan.jkmediapicker.activity.JKMediaPickerActivity;
import com.pakistan.jkmediapicker.callback.JKMediaResultCallback;
import com.pakistan.jkmediapicker.dialog.JKMediaPickerOptionDialog;
import com.pakistan.jkpm.activity.JKPMActivity;
import com.pakistan.jkpm.callback.JKPMCallback;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements JKMediaResultCallback, JKPMCallback {

    private TextView mCapturedImagePath_TV, mBrowsedImagePath_TV;
    private TextView mCapturedVideoPath_TV, mBrowsedVideoPath_TV;
    private TextView mBrowsedDocPath_TV, mBrowsedAudioPath_TV;

    private static final String NO_CAP_IMG_PATH = "No Path for Captured Image";
    private static final String NO_BRO_IMG_PATH = "No Path for Browsed Image";
    private static final String NO_CAP_VID_PATH = "No Path for Captured Video";
    private static final String NO_BRO_VID_PATH = "No Path for BROWSED Video";
    private static final String NO_BRO_DOC_PATH = "No Path for BROWSED Document";
    private static final String NO_BRO_AUD_PATH = "No Path for BROWSED Audio";

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCapturedImagePath_TV = findViewById(R.id.cap_img_path_tv);
        mBrowsedImagePath_TV = findViewById(R.id.bro_img_path_tv);
        mCapturedVideoPath_TV = findViewById(R.id.cap_vid_path_tv);
        mBrowsedVideoPath_TV = findViewById(R.id.bro_vid_path_tv);
        mBrowsedDocPath_TV = findViewById(R.id.bro_doc_path_tv);
        mBrowsedAudioPath_TV = findViewById(R.id.bro_audio_path_tv);
        Button mOpenDialogButton = findViewById(R.id.open_dialog_btn);

        mOpenDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPermissionActivity();
                startJKMediaPickerActivity();
            }
        });
    }

    private void startJKMediaPickerActivity() {
        JKMediaPickerActivity.mJKMediaResultCallback = this;
        Intent intent = new Intent(MainActivity.this, JKMediaPickerActivity.class);
        startActivity(intent);
    }

    // Start Permission Activity
    private void startPermissionActivity() {
        JKPMActivity.jkpmCallback = this;
        Intent intent = new Intent(MainActivity.this, JKPMActivity.class);
        intent.putExtra(JKPMActivity.PERMISSION_KEY, PERMISSIONS);
        startActivity(intent);
    }

    @Override
    public void onMediaIntentResult(int requestCode, Intent data) {
        if(data == null){
            Toast.makeText(MainActivity.this, "No data Selected", Toast.LENGTH_LONG).show();
            return;
        }

        if(requestCode == JKMediaPickerActivity.CAPTURE_IMAGE_REQUEST_CODE){
            if(getAbsPath(data.getData()) != null)
                mCapturedImagePath_TV.setText(getAbsPath(data.getData()));
            else
                mCapturedImagePath_TV.setText(NO_CAP_IMG_PATH);
        }

        if(requestCode == JKMediaPickerActivity.PICK_IMAGE_REQUEST_CODE){
            if(getAbsPath(data.getData()) != null)
                mBrowsedImagePath_TV.setText(getAbsPath(data.getData()));
            else
                mBrowsedImagePath_TV.setText(NO_BRO_IMG_PATH);
        }

        if(requestCode == JKMediaPickerActivity.CAPTURE_VIDEO_REQUEST_CODE){
            if(getAbsPath(data.getData()) != null)
                mCapturedVideoPath_TV.setText(getAbsPath(data.getData()));
            else
                mCapturedVideoPath_TV.setText(NO_CAP_VID_PATH);
        }

        if(requestCode == JKMediaPickerActivity.PICK_VIDEO_REQUEST_CODE){
            if(getAbsPath(data.getData()) != null)
                mBrowsedVideoPath_TV.setText(getAbsPath(data.getData()));
            else
                mBrowsedVideoPath_TV.setText(NO_BRO_VID_PATH);
        }

        if(requestCode == JKMediaPickerActivity.PICK_DOCUMENT_REQUEST_CODE){
            if(getAbsPath(data.getData()) != null)
                mBrowsedDocPath_TV.setText(getAbsPath(data.getData()));
            else
                mBrowsedDocPath_TV.setText(NO_BRO_DOC_PATH);
        }

        if(requestCode == JKMediaPickerActivity.PICK_AUDIO_REQUEST_CODE){
            if(getAbsPath(data.getData()) != null)
                mBrowsedAudioPath_TV.setText(getAbsPath(data.getData()));
            else
                mBrowsedAudioPath_TV.setText(NO_BRO_AUD_PATH);
        }

    }

    private String getAbsPath(Uri uri) {
        if (uri != null) {
            File file = new File(uri.toString());
            return file.getAbsolutePath();
        }

        return null;
    }

    @Override
    public void onAllPermissionsGranted() {
        Toast.makeText(MainActivity.this, "Permissions Granted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAnyPermissionDenied() {
        Toast.makeText(MainActivity.this, "Permissions Denied", Toast.LENGTH_LONG).show();
        startPermissionActivity();
    }

}
