package com.android.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


/**
 * 闪屏页
 *
 * @author YangShengLong
 */
public class SplashActivity extends FragmentActivity {

    private static final String TAG = "SplashActivity";
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        Intent mLoginIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(mLoginIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
