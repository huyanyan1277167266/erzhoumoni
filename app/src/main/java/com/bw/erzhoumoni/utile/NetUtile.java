package com.bw.erzhoumoni.utile;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:10:12
 *@Description:
 * */public class NetUtile {
     String BASE_URL="http://mobile.bwstudent.com/small/";
     private Apis mapis;

    private NetUtile(){
         initNetUtile();
    }
    private static class SingleInstance{
         private static NetUtile INSTANCE=new NetUtile();
    }

    public static NetUtile getInstance() {
        return SingleInstance.INSTANCE;
    }
//  网络请求
    public boolean iswork(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }
    private void initNetUtile() {
        //添加拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient clinet = builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit.Builder retrofitbuilder = new Retrofit.Builder();
        Retrofit retrofit = retrofitbuilder.baseUrl(BASE_URL)
                .client(clinet)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapis = retrofit.create(Apis.class);
    }

    public Apis getMapis() {
        return mapis;
    }
}
