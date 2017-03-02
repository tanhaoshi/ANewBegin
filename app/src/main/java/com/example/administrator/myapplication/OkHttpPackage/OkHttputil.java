package com.example.administrator.myapplication.OkHttpPackage;

import android.content.Context;
import android.util.Log;

import com.example.administrator.myapplication.App;
import com.example.administrator.myapplication.ListenerPackage.OkhttpclientRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tanhaoshi on 2016/12/13.
 */
public class OkHttputil {

    //继承 封装
    private static OkHttputil mOkHttputil;
    private static final byte[] LOCKER = new byte[0];
    private static Context context;
    private static final String TAG = "OkHttputil";
    private static final MediaType JSON = MediaType.parse("App/json; charset=utf-8");

    public OkHttputil(Context context){
        this.context  = context.getApplicationContext();
     }

    /**
     *  获得单例实例
     */
    public static OkHttputil getInstance (Context context){
            synchronized (LOCKER){
                if(mOkHttputil == null){
                    mOkHttputil = new OkHttputil(context);
                }
            }
        return mOkHttputil;
    }

    /**
     * 设置请求头
     */
    private Headers SetHeaders(Map<String, String> headersParams){
        Headers headers=null;
        okhttp3.Headers.Builder headersbuilder=new okhttp3.Headers.Builder();

        if(headersParams != null)
        {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = "";
           // Request
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                headersbuilder.add(key, headersParams.get(key));
                Log.i("get http", "get_headers===" + key + "====" + headersParams.get(key));
            }
        }
        headers = headersbuilder.build();

        return headers;
    }

    /**
     * get请求
     */
    private void get(String url,final OkhttpclientRequest mClient){
        synchronized (context) {
            try {
                Request mRequest = new Request.Builder().url(url).build();
                Call mCall = App.mOkHttpClient.newCall(mRequest);
                mCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mClient.ResponseFailure(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        mClient.ResponseSussce(response.body().toString());
                        Log.i(TAG, "Sussce");
                    }
                });
                }catch(Exception e){
                try {
                    throw new Exception(e.getMessage().toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * post请求
     * @param url
     * @param mClient
     */
    public void post(String url,String postdata, final OkhttpclientRequest mClient) throws Exception{
            RequestBody mRequestBody = RequestBody.create(JSON, postdata);
            Request mRequest = new Request.Builder()
                    .url(url).post(mRequestBody).build();
            Call mCall = App.mOkHttpClient.newCall(mRequest);
            /**
             * 1.第一种请求的方法 异步
              */
            mCall.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    mClient.ResponseFailure(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    mClient.ResponseSussce(response.body().toString());
                }
            });
            /**
             * 2.第二者方法 同步
             */
//            try{
//                Response mResponse = App.mOkHttpClient.newCall(mRequest).execute();
//                if(mResponse.isSuccessful()){
//                    mClient.ResponseSussce(mResponse.toString());
//                }else{
//                    throw new IOException("Unexpected code " + mResponse);
//                }
//            }catch(Exception e){
//               e.printStackTrace();
//            }
        }


    /**
     *
     * 基于okhttp的文件上传(图片),适用于java EE sping框架
     */
     public void fileTopPost(String url,String name,String imageName,final OkhttpclientRequest mClient){
         //     /storage/emulated/0/DCIM/Camera/IMG_20161225_113329.jpg  相册里面选择
         //     /storage/emulated/0   基本路径
         //     /storage/emulated/0/DCIM/Camera/1482805890886.jpg  拍照后的路径
         File file = new File(App.imagePath, name);
         RequestBody body = new MultipartBody.Builder()
                 .addFormDataPart("qqfile", name, RequestBody.create(MediaType.parse("media/type"), file))
                 .addFormDataPart("imageName",imageName)
                 .build();

         Request request = new Request.Builder().url(url).post(body).build();

         Call mCall = App.mOkHttpClient.newCall(request);

         mCall.enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
                 mClient.ResponseFailure(e.getMessage().toString());
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 mClient.ResponseSussce(response.body().toString());
             }
         });
     }


    public void httpclient(String url,OkhttpclientRequest mClient){
           get(url,mClient);
    }

    public void imageTop(String url,String name,String imageName,OkhttpclientRequest mClient){
        fileTopPost(url,name,imageName,mClient);
    }

    public void httppost(String url, String postdata, final OkhttpclientRequest mClient){
        try {
            post(url,postdata,mClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在Java泛型中

     ？代表不确定的java类型

     T代表java类型

     K V 代表java键值中的key和value



     K —— 键，比如映射的键。
     V —— 值，比如 List 和 Set 的内容，或者 Map 中的值。
     E —— 异常类。
     T —— 泛型。

     */
}
