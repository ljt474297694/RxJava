package com.atguigu.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.atguigu.mvp.R;
import com.atguigu.mvp.bean.User;
import com.atguigu.mvp.presenter.Presenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能: MVP设计模式
 * View层(视图层)
 */
public class LoginActivity extends AppCompatActivity implements IUserLoginView {

    @Bind(R.id.id_et_username)
    EditText idEtUsername;
    @Bind(R.id.id_et_password)
    EditText idEtPassword;
    @Bind(R.id.id_btn_login)
    Button idBtnLogin;
    @Bind(R.id.id_btn_clear)
    Button idBtnClear;
    @Bind(R.id.id_pb_loading)
    ProgressBar idPbLoading;
    /**
     * 操作层的实例
     */
    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /**
         * 初始化视图 加载数据
         */
        initView();
        initData();
    }

    private void initData() {
        /**
         * 得到操作层的引用 传入View层接口实例
         */
        mPresenter = new Presenter(this);
    }


    private void initView() {
        ButterKnife.bind(this);
    }


    @Override
    public String getUserName() {
        return idEtUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return idEtPassword.getText().toString().trim();
    }

    @Override
    public void showLoading() {
        idPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        idPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
//        Toast.makeText(LoginActivity.this, "" + user.toString(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class).putExtra("user", user));
    }

    @Override
    public void showFailedError(String error) {
        Toast.makeText(LoginActivity.this, "" + error, Toast.LENGTH_SHORT).show();
        Log.e("TAG", "LoginActivity showFailedError()" + error);
    }

    @Override
    public void clearUserInfo() {
        idEtUsername.setText("");
        idEtPassword.setText("");
    }


    @OnClick({R.id.id_btn_login, R.id.id_btn_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_login:
                mPresenter.login();//调用操作层登录方法
                break;
            case R.id.id_btn_clear:
                mPresenter.clearUserInfo();//清理EditText
                break;
        }
    }
}
