package com.example.loginmvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.loginmvp.api.ApiSercice;
import com.example.loginmvp.common.MvpApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    //双检锁单例获取本类对象
    private static volatile HttpManager httpManager;
    private final Retrofit.Builder builder;

    public static HttpManager getHttpManager() {
        if(httpManager==null){
            synchronized (HttpManager.class){
                if(httpManager==null){
                    httpManager=new HttpManager();
                }
            }
        }
        return httpManager;
    }
    //空参构造中获取retrofit中的builder类的对象
    public HttpManager() {
        builder = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    }
//自定义ok客户端
    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(new MyCacheIntercepter())
                .addInterceptor(new MyCacheIntercepter())
                .retryOnConnectionFailure(true)
                .cache(new Cache(new File(MvpApplication.getApp().getCacheDir(), "cache"), 1024*1024*10))
                .build();
    }

    //获取服务接口对象
    public <T> T getApiSeriver(String baseUrl,Class<T> clazz){
        return builder.baseUrl(baseUrl).build().create(clazz);
    }
    //网络拦截器
    public class MyCacheIntercepter implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!isNetworkAvailable(MvpApplication.getApp())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);

            if (isNetworkAvailable(MvpApplication.getApp())) {
                int maxAge = 0;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 15 * 60;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    }
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
}
