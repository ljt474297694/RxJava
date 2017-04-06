package com.atguigu.retrofitandrxjava;

import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 李金桐 on 2017/4/4.
 * QQ: 474297694
 * 功能: RetrofitUtils 创建业务接口实例 工具类
 */

public class RetrofitUtils<T> {
    /**
     * @param baseUrl     baseUrl
     * @param serviceClass 需要创建的业务接口的class 必须与T相同
     * @return
     */
    public T createRetrofitServes(String baseUrl, Class serviceClass) {
        if (TextUtils.isEmpty(baseUrl) || serviceClass == null) {
            throw new NullPointerException();
        }
        return (T) new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(serviceClass);
    }
}
