package com.example.tanhao.anewbegin.utils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/2/27.
 */

public class User {

    String name;
    String age;

    @Inject
    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
