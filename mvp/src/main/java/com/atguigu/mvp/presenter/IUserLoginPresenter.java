package com.atguigu.mvp.presenter;

import com.atguigu.mvp.bean.User;

/**
 * Created by 李金桐 on 2017/4/1.
 * QQ: 474297694
 * 功能: xxxx
 */

public interface IUserLoginPresenter {
    //登录成功失败的回调方法
    void loginSuccess(User user);
    void loginFailed(String error);

    //登录方法
    void login();
    //清理方法
    void clearUserInfo();

}
