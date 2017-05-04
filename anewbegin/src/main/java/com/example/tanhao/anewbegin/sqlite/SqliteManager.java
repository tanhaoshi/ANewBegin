package com.example.tanhao.anewbegin.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/7.
 */

public class SqliteManager extends SQLiteOpenHelper{

    public static final String sql_Name_key = "TanHao";

    //继承后super
    public SqliteManager(Context context){
        super(context,sql_Name_key,null,1);
    }

    //数据库第一次被创建时onCreate会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("语法:"+"CREATE TABLE " +sql_Name_key +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(64), passWord VARCHAR(64))");
        db.execSQL("CREATE TABLE "+sql_Name_key +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(64), passWord VARCHAR(64))");
    }

    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
    }

}
