package com.example.administrator.myapplication.UpdatePackage;

import android.os.Bundle;

import com.example.administrator.myapplication.BasePackage.BaseActivity;
import com.example.administrator.myapplication.R;

public class UpdateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_update;
    }
}
