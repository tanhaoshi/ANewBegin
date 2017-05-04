package com.example.administrator.myapplication;

import android.os.Bundle;
import android.util.Log;

import com.example.administrator.myapplication.BasePackage.BaseActivity;

import hugo.weaving.DebugLog;

public class TextActivity extends BaseActivity {

    public static final String TAG = "TextActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
       // initView();
        textvoid();
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

    @DebugLog
    void textvoid(){
        for(int i = 0; i<100;i++){
            System.out.println(i);
        }
    }

}
