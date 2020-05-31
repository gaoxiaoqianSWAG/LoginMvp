package com.example.loginmvp.api;

import com.example.loginmvp.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiSercice {
    String baseUrl="https://www.wanandroid.com/user/";
    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean>login(@Field("username") String username, @Field("password") String password);
}
