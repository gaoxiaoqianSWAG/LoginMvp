package com.example.loginmvp.base;

import javax.sql.ConnectionPoolDataSource;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    public CompositeDisposable compositeDisposable;

    /**
     * 切断所有的Disposable对象
     */
    public void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void addModel(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
