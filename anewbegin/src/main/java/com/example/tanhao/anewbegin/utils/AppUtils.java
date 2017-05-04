package com.example.tanhao.anewbegin.utils;

import android.app.Activity;
import android.os.Looper;
import android.view.WindowManager;

import rx.Subscription;

/**
 * @version 1.0
 * @author TanHao
 * Created by TanHao on 2017/3/27.
 */

public class AppUtils {
    /**
     * 解除订阅
     *
     * @param subscription
     */
    public static void cancelSubscription(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    //判断是否是全屏
    public static boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN)==WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    /**
     * 判断当前是主线程，还是子线程
     */
    public static boolean isMainThread(){
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 获取屏幕高度
     */
    public static int backWindonwsHeight(Activity activity){
        return activity.getResources().getDisplayMetrics().heightPixels;
    }
}
