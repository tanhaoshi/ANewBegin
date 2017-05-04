package com.example.tanhao.anewbegin.sqlite;

import android.content.SharedPreferences;

/**
 * @version TanHao
 * @author  1.0
 * Created by Administrator on 2017/3/24.
 */

public class ShaerXML {

    private SharedPreferences mSharedPreferences ;

    /**
     * 单例模式最优方案
     */
    //定义一个私有的构造方法，避免通过new出一个实例
    private ShaerXML(){

    }

    //定义一个静态私有变量(不初始化,不使用final关键字,使用volatile保证了多线程访问时sShaerXML变量的可见性)
    //避免了sShaer初始化时其它变量属性还没赋完值时，被另外线程调用)
    private static volatile ShaerXML sShaerXML;

    //定义一个公有的静态方法,返回该类型的实例
    public static ShaerXML getInstance(){
        //对象实例化时进行判断 如果不为空的直接返回一个实例
        if(sShaerXML == null){
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建
            synchronized (ShaerXML.class){
                sShaerXML = new ShaerXML();
            }
        }
        return sShaerXML;
    }

    //在java中 以通过Java反射机制来实例化private类型的构造方法，此时基本上会使所有的Java单例实现失效。

}
