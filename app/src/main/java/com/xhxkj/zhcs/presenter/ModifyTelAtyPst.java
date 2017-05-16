package com.xhxkj.zhcs.presenter;

import android.text.TextUtils;

import com.xhxkj.zhcs.activity.RegisterAty;
import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.network.ModifyTelRequest;
import com.xhxkj.zhcs.vm.ModifyTelAtyView;

/**
 * 修改手机号码
 * <p/>
 * Created by 鑫 on 2015/12/2.
 */
public class ModifyTelAtyPst extends BasePst<ModifyTelAtyView> {

    /**
     * 修改手机号码
     *
     * @param sessionId 会话标识（用户登录凭证）
     * @param newTel    新号码
     */
    public void modifyTel(String sessionId, final String newTel) {
        //验证新号码是否为空
        if (TextUtils.isEmpty(newTel)) {
            ModifyTelAtyView view = getView();
            if (view != null) {
                view.showMessage("新手机号不可为空！");
            }
            return;
        }
        //验证新号码格式是否正确
        if (!RegisterAty.TEL.matcher(newTel).matches()) {
            ModifyTelAtyView view = getView();
            if (view != null) {
                view.showMessage("手机号码格式不正确！");
            }
            return;
        }

        ModifyTelAtyView view = getView();
        if (view != null) {
            view.showLoading(true);
        }
        ModifyTelRequest modifyTelRequest = new ModifyTelRequest(sessionId, newTel);
        modifyTelRequest.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                UserEntity.setTel(newTel);
                ModifyTelAtyView view = getView();
                if (view != null) {
                    view.onModifySuccess();
                    view.showLoading(false);
                }
            }

            @Override
            public void onFail(String message) {
                ModifyTelAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    view.showMessage(message);
                }
            }
        });
        modifyTelRequest.request();
    }
}
