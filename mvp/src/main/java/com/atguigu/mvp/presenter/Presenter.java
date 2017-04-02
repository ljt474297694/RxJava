package com.atguigu.mvp.presenter;

import com.atguigu.mvp.bean.User;
import com.atguigu.mvp.model.IUserLoginModel;
import com.atguigu.mvp.model.Model;
import com.atguigu.mvp.view.IUserLoginView;

/**
 * Created by 李金桐 on 2017/4/1.
 * QQ: 474297694
 * 功能: Presenter 操作层
 */

public class Presenter implements IUserLoginPresenter {

    // 视图层接口实例
    private IUserLoginView mIUserLoginView;
    // 模型层接口实例
    private IUserLoginModel mIUserLoginModel;

    public Presenter(IUserLoginView iUserLoginView) {
        this.mIUserLoginView = iUserLoginView;
        this.mIUserLoginModel = new Model(this);
    }

    /**
     * 得到View层数据 联网验证登录
     */
    @Override
    public void login() {
        mIUserLoginView.showLoading();
        mIUserLoginModel.login(mIUserLoginView.getUserName(), mIUserLoginView.getPassword());
    }

    //清理登录信息
    @Override
    public void clearUserInfo() {
        mIUserLoginView.clearUserInfo();
    }

    //回调成功和失败信息给View层 并隐藏LoadingUI
    @Override
    public void loginSuccess(User user) {
        mIUserLoginView.hideLoading();
        mIUserLoginView.toMainActivity(user);
    }

    @Override
    public void loginFailed(String error) {
        mIUserLoginView.hideLoading();
        mIUserLoginView.showFailedError(error);
    }

}
