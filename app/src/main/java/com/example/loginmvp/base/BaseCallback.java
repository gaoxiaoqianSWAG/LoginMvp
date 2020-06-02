package com.example.loginmvp.base;

public interface  BaseCallback <T> {

    void onSuccess(T t);
    void onFail(String error);

}
