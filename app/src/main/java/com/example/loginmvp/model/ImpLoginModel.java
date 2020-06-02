package com.example.loginmvp.model;

import com.example.loginmvp.api.ApiSercice;
import com.example.loginmvp.base.BaseModel;

import com.example.loginmvp.base.BaseObserver;
import com.example.loginmvp.bean.LoginBean;
import com.example.loginmvp.callback.LoginCallback;
import com.example.loginmvp.utils.HttpManager;
import com.example.loginmvp.utils.RxUtil;


public class ImpLoginModel extends BaseModel implements LoginModel {
    @Override
    public void getData(final LoginCallback loginCallback, String userName, String passWord) {
        HttpManager.getHttpManager().getApiSeriver(ApiSercice.baseUrl, ApiSercice.class)
                .login(userName, passWord)
                .compose(RxUtil.observableTransformer())
                .subscribe(new BaseObserver<LoginBean>(this) {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        int errorCode = loginBean.getErrorCode();
                        if (errorCode == 0) {
                            loginCallback.onSuccess(loginBean);
                        } else {
                            loginCallback.onFail(loginBean.getErrorMsg());
                        }
                    }

                    @Override
                    public void onFail(String error) {
                        loginCallback.onFail(error);
                    }
                });
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSercice.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiSercice apiSercice = retrofit.create(ApiSercice.class);
        Observable<LoginBean> observable = apiSercice.login(userName, passWord);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        int errorCode = loginBean.getErrorCode();
                        if(errorCode==0){
                            loginCallback.onSuccess(loginBean);
                        }else{
                            loginCallback.onFail(loginBean.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }
}
