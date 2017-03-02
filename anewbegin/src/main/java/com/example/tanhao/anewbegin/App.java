package com.example.tanhao.anewbegin;

import android.app.Application;
import android.content.Context;

/**
 * Created by tanhaoshi on 2017/2/22.
 */

public class App extends Application{

    public static Context sContext;
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        sContext = getApplicationContext();
    }

    public static App getAppInstance(){
        return app;
    }

    public static Context getContext() {
        return sContext;
    }

}
