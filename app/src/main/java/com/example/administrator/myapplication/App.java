package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Environment;
import android.widget.Toast;


import okhttp3.OkHttpClient;


/**
 *  Created by tanhaosi on 2016/6/24.
 *  Android系统会为每个程序运行时创建一个Application类的对象且仅创建一个，
 *  所以Application可以说是单例 (Singleton)模式的一个类。且 Application 对象的生命周期是整个程序中最长的，
 *  它的生命周期就等于这个程序的生命周期。因为它是全局唯一的，所以在不同的Activity,Service中获得的对象都是同一个对象.
 *  所以通过 Application 来进行一些：数据传递、数据共享、数据缓存等操作。
 *
 *
 *
 *    三、通过 Application 传递数据

 　　假如有一个Activity A, 跳转到 Activity B ,并需要传递一些数据，通常的作法是 Intent.putExtra() 让Intent携带，
     或者有一个Bundle把信息加入Bundle让Intent传递Bundle对象，实现传递。但这样　　有一个问题在于，Intent 和 Bundle 所能携带的数据类型都是
     一些基本的数据类型，如果想实现复杂的数据传递就比较麻烦了，通常需要实现 Serializable 或者 Parcellable 接口。这其实是Android的一种IPC数据
     传递的方法。如果我们的两个Activity在同一个进程当中为什么还要这么麻烦呢，只要把需要传递的对象的引用传递过去就可以了。

 　　基本思路是：在 Application 中创建一个 HashMap ，以字符串为key，Object为value这样我们的 HashMap
     就可以存储任何类型的对象了。在Activity A中把需要传递的对象放入这个HashMap，然后通过 Intent 或者其它途径再把这
     key 传递给Activity B ,Activity B 就可以根据这个字符串在 HashMap 中取出这个对象了。只要再向下转型 ，就实现了对象的传递。、



    四、Application 数据缓存

 　　我一般会习惯在 Application 中建立两个 HashMap 一个用于数据的传递（见三），一个用于缓存一些数据。比如有一个Activity需要从网站获取一些数据，获取完之后我们就可以把这个数据先存到Application 当中，当页面跳转到其它 Activity 再回来的时候，就可以直接使用缓存好的数据了。但如果需要cache一些大量的数据，最好是cache一些 (软引用)SoftReference ，并把这些数据cache到本地Rom 上或者 SDCard上。如果在 Application 中的缓存不存在，从本地缓存查找，如果本地缓存的数据也不存在再从网络上获取。
 */

    public class App extends Application {

    //?postdata=
    //http://192.168.191.1:8081/becp/buyer/login?postdata={"loginName":"testtest","password":"1qazxsw2","platName":"test.jngcxh.com"}
    //http://testtma.56yongche.com/becp/buyer/login?postdata={"loginName":"testtest","password":"1qazxsw2","platName":"test.jngcxh.com"}
    //http://testtma.56yongche.com/becp/buyer/login{"loginName":"testtest","password":"1qazxsw2","platName":"test.jngcxh.com"}

        public static OkHttpClient mOkHttpClient;
        public static final String imagePath = Environment.getExternalStorageDirectory()+"/DCIM/Camera/";
        public static final String httpImagePaht = "http://192.168.191.1:8081/dida/owner/img_upload";
        private static final String TAG = "App";
        public static final int RESULT_LOCAL = 99;
        public static final int RESULT_REMOTE = 98;

        /**
         * 程序创建的时候执行
         */
        @Override
        public void onCreate() {
            super.onCreate();
            OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
            mOkHttpClient = mBuilder.build();
        }

        /**
         * 程序终止的时候执行
         */
        @Override
        public void onTerminate() {
            super.onTerminate();
        }

        /**
         * 程序低内存的时候执行
         */
        @Override
        public void onLowMemory() {
            super.onLowMemory();
        }

        /**
         * 当运行时决定当前应用程序应该减少其内存开销时（通常在进入后台运行的时候）调用，包含一个 level 参数，用于提供请求的上下文。
         *
         * @param level
         */
        @Override
        public void onTrimMemory(int level) {
            super.onTrimMemory(level);
        }

        /**
         * 与 Activity 不同，配置改变时，应用程序对象不会被终止和重启。如果应用程序使用的值依赖于特定的配置，
         * 则重写这个方法来加载这些值，或者在应用程序级处理配置值的改变。
         *
         * @param newConfig
         */
        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
        }

        public static void showToast(Context context, String message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
}
