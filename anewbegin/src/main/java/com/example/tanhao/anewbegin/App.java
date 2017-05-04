package com.example.tanhao.anewbegin;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.example.DaoMaster;
import com.example.DaoSession;
import com.example.UserDao;
import com.example.liveDao;

/**
 * @version 1.0
 * @author TanHao
 * Created by tanhaoshi on 2017/2/22.
 */

public class App extends Application{

    //android 运行时出现错误 UnsupportedMethodException 将提示钩 点掉


    //android 中查看dex 文件的目录 anewbegin\\intermediates\\transforms\\dex\\mian

    public static App app;

    private boolean DEV_MODE = true;

    public static DaoSession sDaoSession;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //该方法加载会比oncreate运行快
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //MultiDex.install(this);
        app = this;
        if (DEV_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectCustomSlowCalls() //API等级11，使用StrictMode.noteSlowCode 针对执行比较耗时的检查
                    .detectDiskReads()  //磁盘读写
                    .detectDiskWrites() //1.datectNetWork()用于检测UI线程是否有网络线程
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    //.penaltyDialog() //弹出违规提示对话框
                    .penaltyLog() //在Logcat 中打印违规异常信息
                    .penaltyFlashScreen() //API等级11
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectActivityLeaks() //检查activity内存泄露 VmPolicy虚拟机策略检测
                    .detectLeakedSqlLiteObjects()//用于检测数据库游标检测是否正确关闭 VmPolicy虚拟机策略检测
                    .detectLeakedClosableObjects() //用于资源使用没有正确关闭时 做出提醒 VmPolicy虚拟机策略检测
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

           initDataBase();
    }


    public static App getInstance(){
        return app;
    }

    private void initDataBase(){
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constant.sql_Name_key, null);
        SQLiteDatabase db = helper.getWritableDatabase();

        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);

        sDaoSession = daoMaster.newSession();
    }

    public static UserDao getUserDao(){
        return sDaoSession.getUserDao();
    }

    public static liveDao getliveDao(){
        return sDaoSession.getLiveDao();
    }
}
