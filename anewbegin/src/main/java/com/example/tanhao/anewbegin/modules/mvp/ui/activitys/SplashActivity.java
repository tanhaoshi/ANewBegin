package com.example.tanhao.anewbegin.modules.mvp.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tanhao.anewbegin.modules.login.activity.LoginActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/2/27.
 */

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoHomeActivity();
    }

    private void GoHomeActivity(){
        /**
         * Observable：发射源，英文释义“可观察的”，在观察者模式中称为“被观察者”或“可观察对象”；

         Observer：接收源，英文释义“观察者”，没错！就是观察者模式中的“观察者”，可接收Observable、Subject发射的数据；

         Subject：Subject是一个比较特殊的对象，既可充当发射源，也可充当接收源，为避免初学者被混淆，
         本章将不对Subject做过多的解释和使用，
         重点放在Observable和Observer上，先把最基本方法的使用学会，后面再学其他的都不是什么问题；

         Subscriber：“订阅者”，也是接收源，那它跟Observer有什么区别呢？Subscriber实现了Observer接口，
         比Observer多了一个最重要的方法unsubscribe( )，用来取消订阅，当你不再想接收数据了，可以调用unsubscribe( )方法停止接收，
         Observer 在 subscribe() 过程中,最终也会被转换成 Subscriber 对象，一般情况下，建议使用Subscriber作为接收源；

         Subscription ：Observable调用subscribe( )方法返回的对象，同样有unsubscribe( )方法，可以用来取消订阅事件；

         Action0：RxJava中的一个接口，它只有一个无参call（）方法，且无返回值，同样还有Action1，Action2...Action9等，
         Action1封装了含有 1 个参的call（）方法，即call（T t），
         Action2封装了含有 2 个参数的call方法，即call（T1 t1，T2 t2），以此类推；

         Func0：与Action0非常相似，也有call（）方法，但是它是有返回值的，同样也有Func0、Func1...Func9;
         */
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
