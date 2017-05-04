package com.example.tanhao.anewbegin.modules.mvp.module.impl;

import android.content.Context;
import android.widget.Toast;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.modules.mvp.module.RegisterInteractor;
import com.example.tanhao.anewbegin.network.RequestCallBack;
import com.example.tanhao.anewbegin.sqlite.DbUtils;
import com.example.tanhao.anewbegin.utils.EncryptUtil;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @verison 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/8.
 */

public class RegisterInteractorImpl implements RegisterInteractor<com.example.User>{

    @Inject
    public RegisterInteractorImpl(){

    }

    @Override
    public Subscription registerApp(final Map<String, String> content, final RequestCallBack<com.example.User> callBack, final Context context) {
        //将数据插入到 数据库中
        //rxjava 详解http://blog.csdn.net/chunqiuwei/article/details/51778401
        //rx java作用就是，我们可以简单的把一切操作，都封装成一个方法  然后我们将
        //我们的方法放入到我们的 rx java的操作符中， 让代码逻辑更加清晰，更加明了。
        //我们可以通过 被观察者发送信息，观察者接收，处理完，可以无限循环，即使我们的业务增加，更加繁忙
        //我们只是在不断的循环我们的操作符。
        //应该将其放在子线程中存储，存储成功的话
        //Observer 观察者(接收信息)  Observerable 被观察者(发送消息)
        //map的作用，把Observable发射的消息转换成另外一个东东 map
        //from 可以代替循环的功能
        //通过测试可以发现doOnNext()函数执行的地方跟subcribe()中的onNext()执行的地方没有必然联系。
        //doOnNext()的执行在onNext()之前，对数据进行相关处理。
        //Observer 的子类 是 Subscriber
        //RxJava 还内置了一个实现了 Observer 的抽象类：Subscriber。
        // Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的：
        // http://gank.io/post/560e15be2dca930e00da1083

        //我们将数据插入到数据库中，这肯定是一个在子线程中的过程
        //所以我们要指定 在生产线程中进行Io操作 然后我们进行 绑定subscriber 然后在onnext中进行回调接口 我们在到异常中进行 打印日志错误
        return Observable.create(new Observable.OnSubscribe<com.example.User>() {
                    @Override
                    public void call(Subscriber<? super com.example.User> subscriber) {
                          if(DbUtils.queryUser(content.get("userName")) >= 1){
                              subscriber.onCompleted();
                          }else {
                              com.example.User user = new com.example.User();
                              user.setUserName(content.get("userName"));
                              user.setPassWord(content.get("passWord"));
                              //由于我设置了cool字段不能为空，假设这个字段没有获取到字段，将不会进行插入
                              user.setCool("tanhao");
                              DbUtils.insertUser(user);
                              subscriber.onNext(user);
                          }
                    }
                }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                    .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                        .subscribe(new Observer<com.example.User>() {
                            @Override
                            public void onCompleted() {
                                EncryptUtil.showToast("已存在该账号!",App.getInstance());
                                callBack.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                callBack.onError(e.getMessage(), false);
                            }

                            @Override
                            public void onNext(com.example.User users) {
                                Toast.makeText(App.getInstance(),"成功登入",Toast.LENGTH_SHORT).show();
                                callBack.onSuccess(users);
                            }
                        });
          }
}
