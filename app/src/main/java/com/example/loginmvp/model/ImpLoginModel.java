package com.example.loginmvp.model;

import com.example.loginmvp.api.ApiSercice;
import com.example.loginmvp.bean.LoginBean;
import com.example.loginmvp.callback.LoginCallback;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImpLoginModel implements LoginModel {
    @Override
    public void getData(final LoginCallback loginCallback, String userName, String passWord) {
        Retrofit retrofit = new Retrofit.Builder()
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
                });
    }
}
