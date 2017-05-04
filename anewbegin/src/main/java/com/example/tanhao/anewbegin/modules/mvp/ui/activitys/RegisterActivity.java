package com.example.tanhao.anewbegin.modules.mvp.ui.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import com.example.tanhao.anewbegin.Constant;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseActivity;
import com.example.tanhao.anewbegin.modules.mvp.presenter.impl.RegisterPresenterImpl;
import com.example.tanhao.anewbegin.modules.mvp.view.RegisterView;
import com.example.tanhao.anewbegin.utils.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @version 1/0
 * @author TanHao
 * Created by Administrator on 2017/3/8.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenterImpl> implements RegisterView{

    @Inject
    RegisterPresenterImpl mPresenter;
    @BindView(R.id.input_user)
    EditText user;
    @BindView(R.id.input_pass)
    EditText password;
    private ProgressDialog mDialog;

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        base_iv_back.setVisibility(View.VISIBLE);
        base_title.setText("Register");
        mBasePresenter = mPresenter;
        mBasePresenter.onBindView(this);
        mDialog = new ProgressDialog(RegisterActivity.this);
        mDialog.setMessage("注册中...");
    }

    @Override
    protected void inject() {
        component.inject(this);
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.remeber_me,R.id.base_iv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.remeber_me:
                loadData(false);
                break;
            case R.id.base_iv_back:
                finish();
                break;
        }
    }

    public void getTextContent(){
        String userName = user.getText().toString().trim();
        String passWord = password.getText().toString().trim();
        Map<String,String> maps = new HashMap<>();
        if(EncryptUtil.checkAcount(userName,passWord,RegisterActivity.this)){
            maps.put("userName",EncryptUtil.encrypt(userName));
            maps.put("passWord",EncryptUtil.encrypt(passWord));
            SharedPreferences.Editor sharedPreferences = getSharedPreferences(Constant.LOGIN_KEY, Context.MODE_PRIVATE).edit();
            sharedPreferences.clear();
            sharedPreferences.putString("userName",EncryptUtil.encrypt(user.getText().toString()));
            sharedPreferences.putString("passWord",EncryptUtil.encrypt(password.getText().toString()));
            sharedPreferences.commit();
            mPresenter.toRegister(maps);
        }
    }

    @Override
    public void toHomeActivity(com.example.User user) {
        if(user != null){
            startActivity(new Intent(RegisterActivity.this, MainActivty.class));
            finish();
        }
    }

    @Override
    public void showProgress(boolean isTrue) {
        mDialog.show();
    }

    @Override
    public void hideProgress() {
         mDialog.dismiss();
    }

    @Override
    public void showError(String msg, boolean pullToRefresh) {
        EncryptUtil.showToast(msg,RegisterActivity.this);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getTextContent();
    }
}
