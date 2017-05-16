package com.xhxkj.zhcs.presenter;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.network.AddAddressRequest;
import com.xhxkj.zhcs.vm.AddAddressAtyView;

/**
 * 新增收货地址
 */
public class AddAddressAtyPst extends BasePst<AddAddressAtyView> {


    public void addAddress(String name, String tel, String address) {
        AddAddressAtyView view = getView();
        if (view != null) {
            view.showLoading(true);
        }

        AddAddressRequest addAddressRequest = new AddAddressRequest(name, tel, address);
        addAddressRequest.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                AddAddressAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    view.onAddAddressSuccess();
                }
            }

            @Override
            public void onFail(String message) {
                AddAddressAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    view.showMessage(message);
                }
            }
        });
        addAddressRequest.request();
    }
}
