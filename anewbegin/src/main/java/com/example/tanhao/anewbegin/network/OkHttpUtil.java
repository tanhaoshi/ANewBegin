package com.example.tanhao.anewbegin.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tanhao.anewbegin.App;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/10.
 */

public class OkHttpUtil {

    //设置有网络有效缓存时间为半小时
    private static final long CACHE_MAX_AGE = 60 * 1;
    //设置无网络缓存为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24;

    /**
     * 无网络情况
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    /**
     * 有网络情况
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age="+CACHE_MAX_AGE;

   public static OkHttpUtil getInstance(){
       return new OkHttpUtil();
   }

    //okhttp缓存 只对 get 请求有效 又因为get请求是query
    public OkHttpClient getOkHttpClient(){
        //okhttp 配置网络拦截器可以有三种方式
        Cache cache = new Cache(new File(App.getInstance().getCacheDir().getAbsolutePath(),"HttpCache"),1024 * 1024 * 10);
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(mLoginInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .build();
    }

    //判断当前是否拥有网络
    public static boolean isNetWorkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnected()){
                if(networkInfo.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }

     //okHttp中注册的拦截器可以分成两类,分别是程序拦截器和网络拦截器
     //第一个拦截器 配置日志打印 也为程序拦截器
     //两者最大的不同就是程序拦截器只会对网络请求调用一次,
     //然后拦截器返回的数据会直接从缓存中读取;而网络拦截器会进行两次的网络请求。
    private final Interceptor mLoginInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //请求开始时间
            long start = System.nanoTime();
           // KLog.i(String.format());
            Log.i("AddLogInterceptor:",String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
            //请求结束时间
            long end = System.nanoTime();
            Response response = chain.proceed(request);
            Log.i("AddLogInterceptor","请求的时间是:"+(end-start)/1e6d+"," +
                    "响应的连接是:"+response.request().url()+",响应头是:"+response.headers());
            return response;
        }
    };
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!isNetWorkAvailable()) {
                //没网的时候就读缓存
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);

            if (isNetWorkAvailable()) {
                //有网的时候读接口上的 @Headers 里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                //没网的时候
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    //根据网络状况获取缓存情况
    @NonNull
    public static String getCacheControl(){
        return isNetWorkAvailable() ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE ;
    }
}
