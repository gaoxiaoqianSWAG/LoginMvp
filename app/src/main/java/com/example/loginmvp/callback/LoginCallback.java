package com.example.loginmvp.callback;

import com.example.loginmvp.bean.LoginBean;

public interface LoginCallback {
    void onSuccess(LoginBean loginBean);
    void onFail(String error);
}
