package com.pakistan.jkmediapicker.callback;

import android.content.Intent;

public interface JKMediaResultCallback {
    void onMediaIntentResult(int requestCode, Intent data);
}
