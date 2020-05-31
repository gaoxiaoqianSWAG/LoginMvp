package com.example.loginmvp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginmvp.bean.LoginBean;
import com.example.loginmvp.presenter.ImpLoginPresenter;
import com.example.loginmvp.view.LoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginView {
    private EditText et_name;
    private EditText et_pass;
    private Button btn_login;
    private ImpLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new ImpLoginPresenter(this);
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pass = (EditText) findViewById(R.id.et_pass);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        String name = et_name.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        presenter.getData(name, pass);

    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        int errorCode = loginBean.getErrorCode();
        if(errorCode==0){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
