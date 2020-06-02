package com.example.loginmvp.callback;

import com.example.loginmvp.base.BaseCallback;
import com.example.loginmvp.bean.LoginBean;

public interface LoginCallback extends BaseCallback<LoginBean> {
    @Override
    void onSuccess(LoginBean loginBean);

    @Override
    void onFail(String error);
}
