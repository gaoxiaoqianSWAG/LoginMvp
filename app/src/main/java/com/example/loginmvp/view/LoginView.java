package com.example.loginmvp.view;

import com.example.loginmvp.bean.LoginBean;

public interface LoginView {
    void onSuccess(LoginBean loginBean);
    void onFail(String error);
}
