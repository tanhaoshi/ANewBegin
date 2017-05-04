package com.example.administrator.myapplication.BasePackage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.administrator.myapplication.OkHttpPackage.OkHttputil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by tanhaoshi on 2016/12/6.
 */
public abstract class BaseActivity extends Activity implements BaseFuncIml,View.OnClickListener{

    public OkHttputil mOkHttputil;
    public static final String TAG = "BaseActivity";
    private Unbinder m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentView());
        m = ButterKnife.bind(this);
        mOkHttputil = new OkHttputil(this);
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("getClassName",this.getClass().getSimpleName());
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initLoad() {

    }

    @Override
    public void onClick(View v) {

    }


    protected abstract int getContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(m != null){
            m.unbind();
        }
    }
}
