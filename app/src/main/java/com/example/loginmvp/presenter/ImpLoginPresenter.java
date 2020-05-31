package com.example.loginmvp.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.loginmvp.bean.LoginBean;
import com.example.loginmvp.callback.LoginCallback;
import com.example.loginmvp.model.ImpLoginModel;
import com.example.loginmvp.view.LoginView;

public class ImpLoginPresenter implements LoginPresenter, LoginCallback {

    private LoginView loginView;
    private final ImpLoginModel impLoginModel;

    public ImpLoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        impLoginModel = new ImpLoginModel();
    }

    @Override
    public void getData(String userName, String pwd) {
        if (TextUtils.isEmpty(userName)) {
           loginView.onFail("账号错误");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            loginView.onFail("密码错误");
            return;
        }
        impLoginModel.getData(this, userName, pwd);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        loginView.onSuccess(loginBean);
    }

    @Override
    public void onFail(String error) {
        loginView.onFail(error);
    }
}
