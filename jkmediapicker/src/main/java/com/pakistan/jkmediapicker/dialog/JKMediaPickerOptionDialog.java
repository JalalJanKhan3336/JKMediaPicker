package com.pakistan.jkmediapicker.dialog;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.pakistan.jkmediapicker.R;
import com.pakistan.jkmediapicker.callback.JKMediaPickerDialogOptionCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class JKMediaPickerOptionDialog extends DialogFragment {

    private FrameLayout mCameraImage_FL, mGalleryImage_FL, mCameraVideo_FL,
            mGalleryVideo_FL, mDocumentFile_FL, mAudioFile_FL;

    private JKMediaPickerDialogOptionCallback jkMediaPickerDialogOptionCallback;

    public void setJkMediaPickerDialogOptionCallback(@NonNull JKMediaPickerDialogOptionCallback listener) {
        jkMediaPickerDialogOptionCallback = listener;
    }

    public JKMediaPickerOptionDialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = null;

        setCancelable(true);
        AlertDialog dialog = null;

        if(getActivity() != null){
            view = getActivity().getLayoutInflater().inflate(R.layout.fragment_jkmedia_picker_option_dialog, new LinearLayout(getActivity()), false);

            initView(view);
            click();

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(view);

            dialog = builder.create();
        }

        return dialog;
    }


    private void initView(View view) {
        mCameraImage_FL = view.findViewById(R.id.cap_img_fl);
        mGalleryImage_FL = view.findViewById(R.id.bro_img_fl);
        mCameraVideo_FL = view.findViewById(R.id.cap_vid_fl);
        mGalleryVideo_FL = view.findViewById(R.id.bro_video_fl);
        mDocumentFile_FL = view.findViewById(R.id.bro_doc_file_fl);
        mAudioFile_FL = view.findViewById(R.id.bro_audio_file_fl);
    }

    public void cancelDialog() {
        if(getDialog() != null){
            if(getDialog().isShowing()){
                getDialog().cancel();
            }
        }
    }

    public void dismissDialog() {
        if(getDialog() != null){
            if(getDialog().isShowing()){
                getDialog().dismiss();
            }
        }
    }

    private void click() {
        mCameraImage_FL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                jkMediaPickerDialogOptionCallback.onCaptureImageClicked();
            }
        });

        mGalleryImage_FL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                jkMediaPickerDialogOptionCallback.onBrowseImageClicked();
            }
        });

        mCameraVideo_FL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                jkMediaPickerDialogOptionCallback.onCaptureVideoClicked();
            }
        });

        mGalleryVideo_FL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                jkMediaPickerDialogOptionCallback.onBrowseVideoClicked();
            }
        });

        mDocumentFile_FL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                jkMediaPickerDialogOptionCallback.onBrowseDocumentClicked();
            }
        });

        mAudioFile_FL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                jkMediaPickerDialogOptionCallback.onBrowseAudioClicked();
            }
        });
    }

}
