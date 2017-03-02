package com.example.administrator.myapplication.MVP.mvp.view;

import com.example.administrator.myapplication.MVP.mvp.bean.ItemData;

import java.util.ArrayList;

/**
 * Created by tanhaoshi on 2017/1/12.
 */
public interface ILoaddataView {
    void displayView(ArrayList<String> arrayList);
    void showMessage(String message);
}
