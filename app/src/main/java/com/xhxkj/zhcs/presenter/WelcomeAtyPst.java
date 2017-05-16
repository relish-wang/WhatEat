package com.xhxkj.zhcs.presenter;

import android.text.TextUtils;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.entity.AddressEntity;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.network.GetAddressesRequest;
import com.xhxkj.zhcs.network.LoginRequest;
import com.xhxkj.zhcs.vm.WelcomeAtyView;

import java.util.ArrayList;

public class WelcomeAtyPst extends BasePst<WelcomeAtyView> {

    public void autoLogin() {
        // 本地获取用户名密码
        final String username = UserEntity.getName();
        String password = UserEntity.getPwd();

        // 用户名或密码为空，跳转至登录界面
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            WelcomeAtyView view = getView();
            if (view != null) {
                view.onAutoLoginFail(username);
            }
            return;
        }

        LoginRequest loginRequest = new LoginRequest(username, password);
        loginRequest.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                WelcomeAtyView view = getView();
                if (view != null) {
                    view.onAutoLoginSuccess();
                }
            }

            @Override
            public void onFail(String message) {
                WelcomeAtyView view = getView();
                if (view != null) {
                    view.showMessage(message);
                    view.onAutoLoginFail(username);
                }
            }
        });
        loginRequest.request();
    }
}
