package com.xhxkj.zhcs.presenter;

import android.text.TextUtils;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.network.LoginRequest;
import com.xhxkj.zhcs.vm.LoginAtyView;

public class LoginAtyPst extends BasePst<LoginAtyView> {


    public void login(final String name, final String pwd) {
        LoginAtyView view = getView();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            if (view != null) {
                view.showMessage("用户名或密码为空");
            }
            return;
        }

        if (view != null) {
            view.showLoading(true);
        }

        LoginRequest loginRequest = new LoginRequest(name, pwd);

        loginRequest.setOnResponseListener(
                new BaseRequest.OnResponseListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        UserEntity.setName(name);
                        UserEntity.setPwd(pwd);
                        LoginAtyView view = getView();
                        if (view != null) {
                            view.showLoading(false);
                            view.onLoginSuccess();
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        LoginAtyView view = getView();
                        if (view != null) {
                            view.showLoading(false);
                            view.showMessage(message);
                        }
                    }
                });
        loginRequest.request();
    }

}
