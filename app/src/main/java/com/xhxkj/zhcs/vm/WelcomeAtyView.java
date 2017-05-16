package com.xhxkj.zhcs.vm;

import android.support.annotation.Nullable;

import com.xhxkj.zhcs.base.BaseView;


public interface WelcomeAtyView extends BaseView {
    /**
     * 自动登录成功，跳转至主界面
     */
    void onAutoLoginSuccess();

    /**
     * 自动登录失败，跳转至登录界面，并将显示当前手机已登录过的账号
     */
    void onAutoLoginFail(@Nullable String username);
}
