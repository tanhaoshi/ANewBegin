package com.example.tanhao.anewbegin.refrence;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/1.
 */

public class RefrenceManager {

    //write android code for android studio you shoudle
    //dont't write 'static'
    //http://blog.csdn.net/u012205719/article/details/49615387

     private static RefrenceManager sRefrenceManager;

     public RefrenceManager(){

     }

     public static RefrenceManager getInstance(){
         //静态 单例模式 比简单new 出实例，效率要高
         if(sRefrenceManager != null){
             return sRefrenceManager;
         }else{
             return new RefrenceManager();
         }
     }

    /**
     * soft mangger
     */
    public SoftReference<?> backSoftManager(Object o){
        SoftReference softReference = new SoftReference(o);
        if(softReference.get() != null){
            return softReference;
        }
        return null;
    }

    /**
     * weak manager
     */
     public WeakReference<?> backWeakManager(Object o){
         WeakReference weakReference = new WeakReference(o);
         if(weakReference.get()!=null){
             return weakReference;
         }
         return null;
     }

}
