package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.administrator.myapplication.BasePackage.BaseActivity;
import com.example.administrator.myapplication.ListenerPackage.OkhttpclientRequest;
import com.example.administrator.myapplication.SingleLogin.LoginActivity;
import com.example.administrator.myapplication.Util.Constants;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  by create tanhaoshi on 2016/12/06
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.find_swipe)
    public Button mBtn_Swipe ;

    @BindView(R.id.find_ikonw)
    public Button mBtn_iknowfind;

    public static final String TAG = "MainActivity";

    static{
        System.loadLibrary("hello-jni");
    }

    public native String getMessage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    public void initListener() {
        mBtn_Swipe.setOnClickListener(this);
        mBtn_iknowfind.setOnClickListener(this);
    }

    public void initView() {
        mBtn_Swipe.setText(Constants.value.Action);
        mBtn_iknowfind.setText(Constants.value.LastKey);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_swipe:
                mOkHttputil.httpclient("http://www.baidu.com", new OkhttpclientRequest() {
                    @Override
                    public void ResponseSussce(String message) {
                       System.out.println("Sussce");
                    }

                    @Override
                    public void ResponseFailure(String message) {
                        System.out.println("Failure");
                    }
                });
               // startActivity(new Intent(MainActivity.this, SwipeMenuActivity.class));
                break;
            case R.id.find_ikonw:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                //.putExtra("content",getMessage())
                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
