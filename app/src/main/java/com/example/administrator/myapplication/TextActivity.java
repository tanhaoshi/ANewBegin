package com.example.administrator.myapplication;

import android.os.Bundle;
import android.util.Log;

import com.example.administrator.myapplication.BasePackage.BaseActivity;

public class TextActivity extends BaseActivity {

    public static final String TAG = "TextActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
       // initView();
    }

    @Override
    public void initView() {
        super.initView();
        Log.i(TAG,"TextInitView");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_text;
    }

}
