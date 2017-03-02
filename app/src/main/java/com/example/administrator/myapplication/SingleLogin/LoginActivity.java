package com.example.administrator.myapplication.SingleLogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.BasePackage.BaseActivity;
import com.example.administrator.myapplication.MVP.mvp.bean.User;
import com.example.administrator.myapplication.MVP.mvp.presenter.UserLoginPresenter;
import com.example.administrator.myapplication.MVP.mvp.view.IUserLoginView;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.App;

public class LoginActivity extends BaseActivity implements IUserLoginView{

    private EditText login_name;
    private EditText login_password;

    private Button login_btn;

    private UserLoginPresenter presenter = new UserLoginPresenter(this,LoginActivity.this);

    UserLoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    @Override
    public void initView() {
        login_name = (EditText)findViewById(R.id.login_name);
        login_password = (EditText)findViewById(R.id.login_password);
        login_btn = (Button)findViewById(R.id.login_btn);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  presenter.login();
//                name = login_name.getText().toString();
//                password = login_password.getText().toString();
//               if(oldName.equals(name.toString()) && oldPassword.equals(password.toString())) {
//                   App.showToast(LoginActivity.this,"登入成功!");
//                   SharedPreferences.Editor mSharedPreferences = getSharedPreferences("LoginFile", Context.MODE_PRIVATE).edit();
//                   mSharedPreferences.putString("userNmae",name);
//                   mSharedPreferences.putString("userPassword", password);
//                   mSharedPreferences.commit();
//                   Intent intent = new Intent(LoginActivity.this, PhotoActivity.class);
//                   startActivity(intent);
//               }
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
//        synchronized (this) {
//            SharedPreferences mSharedPreferences = getSharedPreferences("LoginFile", Context.MODE_PRIVATE);
//            login_name.setText(mSharedPreferences.getString("userNmae", ""));
//            login_password.setText(mSharedPreferences.getString("userPassword", ""));
//            if (oldName.equals(login_name.getText().toString()) && oldPassword.equals(login_password.getText().toString())) {
//                Intent intent = new Intent(LoginActivity.this, PhotoActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }
    }

    @Override
    public String getUserName() {
        return login_name.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return login_password.getText().toString();
    }

    @Override
    public void showMessage(String message) {
       App.showToast(LoginActivity.this,message);
    }

    @Override
    public void toMainActivity(User user) {

    }
}
