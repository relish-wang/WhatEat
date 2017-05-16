package com.xhxkj.zhcs.presenter;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.entity.AddressEntity;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.network.ModifyAddressRequest;
import com.xhxkj.zhcs.vm.EditAddressAtyView;

/**
 * 修改地址
 *
 * @author 王鑫
 */
public class EditAddressAtyPst extends BasePst<EditAddressAtyView> {

    /**
     * 修改地址
     *
     * @param entity 被修改的地址
     */
    public void modifyAddress(final AddressEntity entity) {

        EditAddressAtyView view = getView();
        if (view != null) {
            view.showLoading(true);
        }

        ModifyAddressRequest modifyAddressRequest = new ModifyAddressRequest(UserEntity.getSessionId(), entity);
        modifyAddressRequest.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                EditAddressAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    UserEntity.setAddress(entity);
                    view.onModifyAddressSuccess();
                }
            }

            @Override
            public void onFail(String message) {
                EditAddressAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    view.showMessage(message);
                }
            }
        });
        modifyAddressRequest.request();
    }
}
