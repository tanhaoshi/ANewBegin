package com.example.tanhao.anewbegin.modules.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseActivity;
import com.example.tanhao.anewbegin.modules.login.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @version 1.0
 * @author TanHao
 * Created by admin on 2017/2/21.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.id_back)
    Button back;
    @BindView(R.id.id_touch)
    Button touch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Then execute the subclass
        Log.i("HomeActivity","Home");
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void inject() {
       component.inject(this);
    }

    @OnClick({R.id.id_touch,R.id.id_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.id_back:
                break;
            case R.id.id_touch:
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

}
