package com.atguigu.mvp.model;

import com.atguigu.mvp.bean.User;
import com.atguigu.mvp.presenter.IUserLoginPresenter;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by 李金桐 on 2017/4/1.
 * QQ: 474297694
 * 功能: Model 模型层
 *       io操作 数据库操作等
 */

public class Model implements IUserLoginModel {
    private IUserLoginPresenter mIUserLoginPresenter;

    public Model(IUserLoginPresenter iUserLoginPresenter) {
        this.mIUserLoginPresenter = iUserLoginPresenter;
    }

    @Override
    public void login(String username, String password) {
        String url = "http://47.93.118.241:8081/android/user/login?";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("username", username)
                .addParams("password", password)
                .addParams("phone", "123123123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (mIUserLoginPresenter != null)
                            mIUserLoginPresenter.loginFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (mIUserLoginPresenter != null)
                            mIUserLoginPresenter.loginSuccess(new Gson().fromJson(response, User.class));
                    }
                });
    }
}
