package com.example.loginmvp;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginmvp.base.BaseActivity;
import com.example.loginmvp.bean.LoginBean;
import com.example.loginmvp.presenter.ImpLoginPresenter;
import com.example.loginmvp.view.LoginView;

import java.util.function.Predicate;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<ImpLoginPresenter, LoginView> implements LoginView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pass)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected LoginView initMvpView() {
        return this;
    }

    @Override
    protected ImpLoginPresenter initMvpPresenter() {
        return new ImpLoginPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String pass = etPwd.getText().toString().trim();
       String name = etName.getText().toString().trim();
       mPresenter.getData(name, pass);

      //  toast("点击了登录");
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
