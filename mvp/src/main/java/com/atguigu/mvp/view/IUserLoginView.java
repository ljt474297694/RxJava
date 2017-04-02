package com.atguigu.mvp.view;

import com.atguigu.mvp.bean.User;

/**
 * Created by 李金桐 on 2017/4/1.
 * QQ: 474297694
 * 功能: xxxx
 */

public interface IUserLoginView {
    //获取账号密码
    String getUserName();

    String getPassword();

    //显示隐藏加载视图
    void showLoading();

    void hideLoading();

    //登录成功与失败的处理方法
    void toMainActivity(User user);

    void showFailedError(String error);

    //清空方法
    void clearUserInfo();

}
