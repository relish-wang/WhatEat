package com.xhxkj.zhcs.presenter;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.entity.AddressEntity;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.network.GetAddressesRequest;
import com.xhxkj.zhcs.vm.MainAtyView;
import com.xhxkj.zhcs.vm.MineFgmView;

import java.util.ArrayList;

/**
 * Created by 鑫 on 2015/12/5.
 */
public class MainAtyPst extends BasePst<MainAtyView> {
    public void getAddresses(String sessionId) {
        MainAtyView view = getView();
        if (view != null) {
            view.showLoading(true);
        }

        GetAddressesRequest request = new GetAddressesRequest(sessionId);
        request.setOnResponseListener(new BaseRequest.OnResponseListener<ArrayList<AddressEntity>>() {
            @Override
            public void onSuccess(ArrayList<AddressEntity> addressEntities) {

                MainAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    UserEntity.setAddresses(addressEntities);
                }
            }

            @Override
            public void onFail(String message) {

                MainAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                }
            }
        });


    }
}
