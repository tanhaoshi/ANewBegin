package com.example.tanhao.anewbegin.sqlite;

import com.example.User;
import com.example.UserDao;
import com.example.tanhao.anewbegin.App;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/26.
 */

public class DbUtils {

    public static void insertUser(User user){
        UserDao userDao = App.getUserDao();
        long count = userDao.insert(user);
    }

    public static void updateUser(User user) {
        UserDao userDao = App.getUserDao();
        userDao.refresh(user);
    }

    public static void deleteUser(User user) {
        UserDao userDao = App.getUserDao();
        userDao.delete(user);
    }

    public static long queryUser(String name){
        QueryBuilder<User> queryBuilder = App.getUserDao().queryBuilder();
        queryBuilder.where(UserDao.Properties.UserName.eq(name));
        long count = queryBuilder.count();
        return count;
    }
}
