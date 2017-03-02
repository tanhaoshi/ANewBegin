package com.example.tanhao.anewbegin.modules.login.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseActivity;
import com.example.tanhao.anewbegin.modules.login.presenter.impl.LoginPresenterImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @version 1.0
 * @author TanHao
 * Created by admin on 2017/2/21.
 */

public class LoginActivity extends BaseActivity{

    private final String TAG = "LoginActivity";
    private ProgressDialog mDialog;

    @Inject
    LoginPresenterImpl mLoginPresenter;
    @BindView(R.id.base_back)
    ImageView back;
    @BindView(R.id.base_title)
    TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mDialog = new ProgressDialog(this);
        back.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText("登入");
//        if(RefrenceManager.getInstance().backSoftManager(mDialog).get() != null){
//            mDialog.setMessage("登入中...");
//            mDialog.show();
//        }
    }

    @Override
    protected void inject() {
        component.inject(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //mDialog.dismiss();
    }
}
