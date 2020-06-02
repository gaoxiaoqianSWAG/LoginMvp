package com.example.loginmvp.presenter;

import android.text.TextUtils;

import com.example.loginmvp.base.BasePresenter;
import com.example.loginmvp.bean.LoginBean;
import com.example.loginmvp.callback.LoginCallback;
import com.example.loginmvp.model.ImpLoginModel;

public class ImpLoginPresenter extends BasePresenter implements LoginPresenter, LoginCallback {
   private ImpLoginModel impLoginModel;

    @Override
    public void getData(String name, String pwd) {
        if (TextUtils.isEmpty(name)) {
            mView.onFail("name不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            mView.onFail("pwd不能为空");
            return;
        }

        impLoginModel.getData(this,name,pwd);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        mView.onSuccess(loginBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }

    @Override
    protected void initModel() {
        impLoginModel = new ImpLoginModel();
        addModel(impLoginModel);
    }
}
