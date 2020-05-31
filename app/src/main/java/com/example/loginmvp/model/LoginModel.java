package com.example.loginmvp.model;

import com.example.loginmvp.callback.LoginCallback;

public interface LoginModel {
    void getData(LoginCallback loginCallback,String userName,String passWord);
}
