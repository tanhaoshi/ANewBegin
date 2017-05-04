package com.example.tanhao.anewbegin.network;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/31.
 */

public class GsonConverterFactroy extends Converter.Factory{

    private final Gson mGson;

    public static GsonConverterFactroy create(){
        return create(new Gson());
    }

    public static GsonConverterFactroy create(Gson gson){
        return new GsonConverterFactroy(gson);
    }

    private GsonConverterFactroy(Gson gson){
        if (gson == null) throw new NullPointerException("gson == null");
        this.mGson = gson;
    }

    /*
     转化 发送给服务器的数据
     */
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = mGson.getAdapter(TypeToken.get(type));
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    /*
     * 转化 服务器返回的数据
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.responseBodyConverter(type, annotations, retrofit);
    }
}
