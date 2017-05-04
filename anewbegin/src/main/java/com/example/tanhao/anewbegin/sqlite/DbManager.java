package com.example.tanhao.anewbegin.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tanhao.anewbegin.Constant;
import com.example.tanhao.anewbegin.modules.mvp.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 1.0
 * @version TanHao
 * Created by Administrator on 2017/3/7.
 */

public class DbManager {

    private SqliteManager mSqliteManager;
    private SQLiteDatabase mSQLiteDatabase;

    //使用volatile关键字避免了多线程访问的时候 instance中dbmanager还没赋值完就被其他线程调用
    private static volatile DbManager mDbManager;

    //定义一个私有的构造方法，避免通过构建方法创造实例
    private DbManager(Context context){
        mSqliteManager = new SqliteManager(context);
    }

    public static DbManager getInstance(Context context){
        if(mDbManager == null){
            synchronized (DbManager.class){
                if(mDbManager == null){
                    //这样使用context会造成内存泄露 因为我们不知道引用的context是来自哪里的 会造成引用的那个activity 实例以及view对象完全保存
                    //错误代码的演示
                    //mDbManager = new DbManager(context);
                    //这样引用全局唯一的context
                    mDbManager = new DbManager(context.getApplicationContext());
                }
            }
        }
        return mDbManager;
    }

    public void addUser(Map<String,String> maps){
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName",maps.get("userName"));
        contentValues.put("passWord",maps.get("passWord"));
        mSQLiteDatabase = mSqliteManager.getWritableDatabase();
        mSQLiteDatabase.insert(Constant.sql_Name_key,null,contentValues);
        mSQLiteDatabase.close();
    }

    public void deleteUser(Object[] params) {
        try {
            // 删除一条数据
            String sql = "delete from "+ Constant.sql_Name_key + "where id=?";
            mSQLiteDatabase = mSqliteManager.getWritableDatabase();
            mSQLiteDatabase.execSQL(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mSQLiteDatabase != null) {
                mSQLiteDatabase.close();
            }
        }
    }

    public void updateUser(Object[] params){
        try{
            String sql = "update"+Constant.sql_Name_key+ "set userName=?,passWord=?,where id=?";
            mSQLiteDatabase = mSqliteManager.getWritableDatabase();
            mSQLiteDatabase.execSQL(sql,params);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(mSQLiteDatabase != null){
                mSQLiteDatabase.close();
            }
        }
    }

    public List<User> backUser(String str){
        List<User> studentList=new ArrayList<User>();

        String selectQuery="SELECT * FROM "+ Constant.sql_Name_key +" where userName =  '"+ str + "'";
        mSQLiteDatabase =mSqliteManager.getReadableDatabase();
        Cursor cursor=mSQLiteDatabase.rawQuery(selectQuery,null);
        try{
            while(cursor.moveToNext()) {
                User user = new User();
                user.setUserName(cursor.getString(1));
                user.setUserPassword(cursor.getString(2));
                studentList.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
            mSqliteManager.close();
        }
        return studentList;
    }
}
