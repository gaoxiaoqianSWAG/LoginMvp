package com.example.loginmvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loginmvp.common.MvpApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter,V extends BaseView> extends Fragment {

    private Unbinder bind;
    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate((Integer) getlayoutId(), container, false);
        bind = ButterKnife.bind(this, view);

        mPresenter=initMvpPresenter();
        if(mPresenter==null){
            mPresenter.setmView(initMvpView());
        }

        initView();
        initData();
        initListener();
        return view;

    }

    protected abstract V initMvpView();

    protected abstract P initMvpPresenter();


    private void initListener() {

    }

    private void initData() {

    }

    private void initView() {

    }

    protected abstract Object getlayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

}
