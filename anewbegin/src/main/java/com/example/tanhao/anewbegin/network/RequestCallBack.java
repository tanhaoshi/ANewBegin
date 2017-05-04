package com.example.tanhao.anewbegin.network;

/**
 * Created by tanhaoshi on 2017/2/21.
 */

public interface RequestCallBack<T> {

    void onStart(T data);//请求前

    void onSuccess(T data); //请求成功

    void onError(String errorMsg, boolean pullToRefresh); //请求失败

    void onCompleted();//请求完成

    //下载
    void onProgress(long downSize, long fileSize);

    void dowloadSuccess(String path, String fileName, long fileSize);
}
