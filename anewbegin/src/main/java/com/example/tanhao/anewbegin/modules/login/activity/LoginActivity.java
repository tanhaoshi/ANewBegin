package com.example.tanhao.anewbegin.modules.login.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tanhao.anewbegin.Constant;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseActivity;
import com.example.tanhao.anewbegin.modules.login.presenter.impl.LoginPresenterImpl;
import com.example.tanhao.anewbegin.modules.login.view.LoginView;
import com.example.tanhao.anewbegin.modules.mvp.ui.activitys.MainActivty;
import com.example.tanhao.anewbegin.modules.mvp.ui.activitys.RegisterActivity;
import com.example.tanhao.anewbegin.utils.AndroidBug5497Workaround;
import com.example.tanhao.anewbegin.utils.AppUtils;
import com.example.tanhao.anewbegin.utils.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.tanhao.anewbegin.R.id.btn_login;

/**
 * @version 1.0
 * @author TanHao
 * Created by admin on 2017/2/21.
 */

public class LoginActivity extends BaseActivity<LoginPresenterImpl> implements LoginView{

    @Inject
    LoginPresenterImpl mLoginPresenter;
    @BindView(R.id.ediTextClean2)
    EditText clean2;
    @BindView(R.id.ediTextClean)
    EditText clean;
    @BindView(btn_login)
    Button login;
    private ProgressDialog mDialog;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        if(AppUtils.isFullScreen(this)){
            AndroidBug5497Workaround.assistActivity(this);
        }
        mBasePresenter = mLoginPresenter;
        mBasePresenter.onBindView(this);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("登入中...");
    }

    @Override
    protected void inject() {
        component.inject(this);
    }

    @Override
    protected void initListener() {

    }

    @OnClick({btn_login,R.id.register_me})
    public void onClick(View view){
        switch (view.getId()){
            case btn_login:
                loadData(false);
                break;
            case R.id.register_me:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void loginApp(com.example.User data) {
        if(data!=null){
            startActivity(new Intent(LoginActivity.this, MainActivty.class));
            finish();
            SharedPreferences.Editor editor = getSharedPreferences(Constant.LOGIN_KEY, Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.putString("userName",EncryptUtil.encrypt(clean.getText().toString()));
            editor.putString("passWord",EncryptUtil.encrypt(clean2.getText().toString()));
            editor.commit();
        }
    }

    @Override
    public void shareLoginApp(com.example.User data) {
        if(data != null){
            startActivity(new Intent(LoginActivity.this, MainActivty.class));
            finish();
        }
    }

    @Override
    public void showProgress(boolean pullToRefresh) {
         mDialog.show();
    }

    @Override
    public void hideProgress() {
         mDialog.dismiss();
    }

    @Override
    public void showError(String msg, boolean pullToRefresh) {
         mDialog.dismiss();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
         login();
    }

    private void login(){
        String userName = clean2.getText().toString().trim();
        String password = clean.getText().toString().trim();
        Map<String,String> params = new HashMap<>();
        if(EncryptUtil.checkAcount(userName,password,LoginActivity.this)){
            params.put("userName",EncryptUtil.encrypt(userName));
            params.put("passWord",EncryptUtil.encrypt(password));
            mLoginPresenter.loginApp(params,LoginActivity.this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(Constant.LOGIN_KEY,Context.MODE_PRIVATE);
            if(preferences.getString("userName",null) != null && preferences.getString("passWord",null) != null) {
                Map<String,String> maps = new HashMap<>();
                maps.put("userName",preferences.getString("userName",null));
                maps.put("passWord",preferences.getString("passWord",null));
                mLoginPresenter.shareLoginApp(maps,LoginActivity.this);
            }
        }
}
