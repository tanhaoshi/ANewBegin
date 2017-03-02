package com.example.administrator.myapplication.Util;

import android.content.Context;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tanhaoshi on 2016/12/27.
 */
public class ThisUtil {

    public static final String firstYear = "yyyyMMdd";

    public static String handleTime(){
        SimpleDateFormat sdf = new SimpleDateFormat(firstYear);
        return sdf.format(new Date());
    }

    public static String handleChars(String message){
        return message.subSequence(message.lastIndexOf("/")+1,message.length()).toString();
    }

    /**
     * 获取屏幕的宽
     */
    public static int getWindowWidth(Context context){
        WindowManager mWindowManager = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
        int width = mWindowManager.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取屏幕高
     */
    public static int getWindowHeight(Context context){
        WindowManager windowManager = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
        int height = windowManager.getDefaultDisplay().getHeight();
        return height;
    }
}
