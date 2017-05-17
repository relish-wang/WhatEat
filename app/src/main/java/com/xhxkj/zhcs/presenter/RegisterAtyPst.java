package com.xhxkj.zhcs.presenter;

import android.text.TextUtils;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.network.RegisterRequest;
import com.xhxkj.zhcs.util.MD5Utils;
import com.xhxkj.zhcs.vm.RegisterAtyView;

/**
 * 注册Pst
 */
public class RegisterAtyPst extends BasePst<RegisterAtyView> {

    public void register(final String name, final String pwd,final String repeatPwd, final String tel) {

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)||TextUtils.isEmpty(repeatPwd)||TextUtils.isEmpty(tel)) {
            RegisterAtyView view = getView();
            if (view != null) {
                view.showMessage("字段不能为空！");
            }
            return;
        }

        RegisterAtyView view = getView();
        if (view != null) {
            view.showLoading(true);
        }
        RegisterRequest registerRequest = new RegisterRequest(name, MD5Utils.toMd5(pwd),tel);
        registerRequest.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                RegisterAtyView view = getView();
                if(view!=null) {
                    UserEntity.setTel(tel);
                    view.onRegisterSuccess();
                }
            }

            @Override
            public void onFail(String message) {
                RegisterAtyView view = getView();
                view.showLoading(false);
                view.showMessage("注册失败");
            }
        });
        registerRequest.request();
    }

}
