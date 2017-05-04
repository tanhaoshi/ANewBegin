package com.example.tanhao.anewbegin.utils;

import android.os.StrictMode;
import android.os.SystemClock;

import com.example.tanhao.anewbegin.modules.home.activity.HomeActivity;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/21.
 */

public class StrictModeManager {

    //严苟模式 主要检测两大问题  一个是线程策略问题 即ThreadPolicy 另一个是VM策略VmPolicy
    //1.其中ThreadPolicy线程策略检测
    //线程检测策略内容有:
    //1.自定义耗时的调用 使用detectCustomSlowCalls()开启
    //2.磁盘读取操作 使用detectDiskReads()开启
    //3.磁盘写入操作 使用detectDiskWrites()开启
    //4.网络操作 使用detectNetwork()开启
    //
    //2.VmPolicy虚拟机策略检测
    //1.Activity泄露 使用detectActivityLeaks()开启
    //2.未关闭的closable对象泄露 使用delectLeakedSqliteObjects()开启
    //3.泄露的Sqlite对象 使用delectLeakedSqliteObjects()开启
    //4.检测实例数量 使用setClassInstanceLimit()开启


    /**
     * 检查activity内存泄露 VmPolicy虚拟机策略检测
     */
    public static void testingdetectActivityLeaks(){
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .penaltyLog()
                .build());
    }

    /**
     * 用于资源使用没有正确关闭时 做出提醒 VmPolicy虚拟机策略检测
     */
    public static void testingdetectLeakedClosableObjects(){
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build());
    }

    /**
     *用于检测数据库游标检测是否正确关闭 VmPolicy虚拟机策略检测
     */
    public static void testingdetectLeakedSqlLiteObjects(){
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .build());
    }

    /**
     * 设置某个类的同时处于内存中的实例上限，可以协助检查内存泄露
     * //  2代表 2个实例  VmPolicy虚拟机策略检测
     */
    public static void testingsetClassInstanceLimit(){
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .setClassInstanceLimit(HomeActivity.class,2)
                .penaltyLog()
                .build());
    }

    /**
     * ---ThreadPolicy 策略----
     *
     * 1.datectNetWork()用于检测UI线程是否有网络线程
     */
    public static void testingdetectNetwork(){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectNetwork()
                .penaltyLog()
                .build());
    }

    /**
     * 2.detectDiskReads()和 detectDiskWrites() 是磁盘读写
     */
    public static void testingDiskReadAndWrites(){
         StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                 .detectDiskReads()
                 .detectDiskWrites()
                 .penaltyLog()
                 .build());
    }

    /**
     * 3.noteSlowCall针对执行比较耗时的检查
     *  当消耗时间大于500Ms时 做出提醒
     */
    public static void testingSlowCall(Runnable task){
        long startTime = SystemClock.uptimeMillis();
        task.run();
        long SLOW_CALL_THRESHOLD = 500;
        long cost = SystemClock.uptimeMillis() - startTime;
        if (cost > SLOW_CALL_THRESHOLD) {
            StrictMode.noteSlowCall("slowCall cost=" + cost);
        }
    }
}
