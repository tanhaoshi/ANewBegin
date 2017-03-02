package com.example.administrator.myapplication.MVP.mvp.biz;

import com.example.administrator.myapplication.MVP.mvp.bean.ItemData;

import java.util.ArrayList;

/**
 * Created by tanhaoshi on 2017/1/12.
 */
public interface RequestData {

    void success(ArrayList<String> arrayList);

    void faild();
}
