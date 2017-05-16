package com.xhxkj.zhcs.presenter;

import android.text.TextUtils;
import android.view.View;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.network.ModifyPwdRequest;
import com.xhxkj.zhcs.util.AppToast;
import com.xhxkj.zhcs.vm.ModifyPwdAtyView;

/**
 * Created by r3lish on 2016/1/22.
 */
public class ModifyPwdAtyPst extends BasePst<ModifyPwdAtyView> {

    /**
     * 修改密码
     */
    public void modifyPwd(String oldPwd, String newPwd, String repeatPwd) {
        if (!TextUtils.equals(newPwd, repeatPwd)) {
            AppToast.showShort("两次密码输入不一致");
            return;
        }
        ModifyPwdAtyView view = getView();
        if(view!=null){
            view.showLoading(true);
        }

        ModifyPwdRequest modifyPwdRequest = new ModifyPwdRequest(oldPwd, newPwd);
        modifyPwdRequest.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                ModifyPwdAtyView view = getView();
                if(view!=null){
                    view.showLoading(true);
                    view.onModifyPwdSuccess();
                }
            }

            @Override
            public void onFail(String message) {

                ModifyPwdAtyView view = getView();
                if(view!=null){
                    view.showLoading(false);
                    view.showMessage(message);
                }
            }
        });
        modifyPwdRequest.request();
    }
}
