package com.example.loginmvp.view;

import com.example.loginmvp.base.BaseView;
import com.example.loginmvp.bean.LoginBean;

public interface LoginView extends BaseView<LoginBean> {
    @Override
    void onSuccess(LoginBean loginBean);

    @Override
    void onFail(String error);
}
