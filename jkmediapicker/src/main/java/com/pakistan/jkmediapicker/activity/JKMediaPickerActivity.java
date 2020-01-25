package com.pakistan.jkmediapicker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pakistan.jkmediapicker.callback.JKMediaPickerDialogOptionCallback;
import com.pakistan.jkmediapicker.dialog.JKMediaPickerOptionDialog;
import com.pakistan.jkmediapicker.callback.JKMediaResultCallback;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class JKMediaPickerActivity extends AppCompatActivity
        implements JKMediaPickerDialogOptionCallback {

    public static JKMediaResultCallback mJKMediaResultCallback;

    public static final int CAPTURE_IMAGE_REQUEST_CODE = 4313;
    public static final int PICK_IMAGE_REQUEST_CODE = 6457;
    public static final int CAPTURE_VIDEO_REQUEST_CODE = 6487;
    public static final int PICK_VIDEO_REQUEST_CODE = 3526;
    public static final int PICK_AUDIO_REQUEST_CODE = 7812;
    public static final int PICK_DOCUMENT_REQUEST_CODE = 2290;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showOptionDialog();
    }

    private void showOptionDialog(){
        JKMediaPickerOptionDialog optionDialog = new JKMediaPickerOptionDialog();
        optionDialog.setJkMediaPickerDialogOptionCallback(this);
        optionDialog.show(getSupportFragmentManager(), optionDialog.getTag());
    }

    @Override
    public void onCaptureImageClicked() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    public void onBrowseImageClicked() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST_CODE);
    }

    @Override
    public void onCaptureVideoClicked() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, CAPTURE_VIDEO_REQUEST_CODE);
    }

    @Override
    public void onBrowseVideoClicked() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a Video"), PICK_VIDEO_REQUEST_CODE);
    }

    @Override
    public void onBrowseDocumentClicked() {
        Intent intent = new Intent();
        intent.setType("file/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a Document"), PICK_DOCUMENT_REQUEST_CODE);
    }

    @Override
    public void onBrowseAudioClicked() {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a Document"), PICK_AUDIO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        AtomicBoolean flag = new AtomicBoolean(false);

        if(resultCode == RESULT_OK && data != null) {
            flag.set(true);
        }
        else {
            flag.set(false);
        }

        if(flag.get()) {
            mJKMediaResultCallback.onMediaIntentResult(requestCode, data);
            finish();
        }
        else {
            mJKMediaResultCallback.onMediaIntentResult(requestCode, null);
            finish();
        }
        
    }

}
