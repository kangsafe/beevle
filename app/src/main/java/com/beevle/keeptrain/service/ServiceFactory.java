package com.beevle.keeptrain.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * 创建Retrofit服务
 * <p>
 * Created by wangchenlong on 16/1/4.
 */
public class ServiceFactory {
    public static <T> T createServiceFrom(final Class<T> serviceClass, String endpoint) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create(gson)) // 添加Gson转换器
                .build();
        return adapter.create(serviceClass);
    }
}