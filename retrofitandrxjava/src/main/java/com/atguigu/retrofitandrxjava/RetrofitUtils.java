package com.atguigu.retrofitandrxjava;

import android.text.TextUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 李金桐 on 2017/4/4.
 * QQ: 474297694
 * 功能: RetrofitUtils 创建业务接口实例 工具类
 */

public class RetrofitUtils<T> {
    /**
     * @param baseUrl     baseUrl
     * @param servesClass 需要创建的业务接口的class 必须与类泛型相通
     * @return
     */
    public T createRetrofitServes(String baseUrl, Class servesClass) {
        if (TextUtils.isEmpty(baseUrl) || servesClass == null) {
            throw new NullPointerException();
        }

        return (T) new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(servesClass);

    }
}
