package com.example.loginmvp.base;

public interface BaseView<T> {

    void onSuccess(T t);
    void onFail(String error);

}
