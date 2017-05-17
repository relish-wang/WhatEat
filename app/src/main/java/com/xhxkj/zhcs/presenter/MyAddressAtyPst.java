package com.xhxkj.zhcs.presenter;

import android.text.TextUtils;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.entity.AddressEntity;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.network.DeleteAddressRequest;
import com.xhxkj.zhcs.network.GetAddressesRequest;
import com.xhxkj.zhcs.network.LoginRequest;
import com.xhxkj.zhcs.network.ModifyDefaultAddressRequest;
import com.xhxkj.zhcs.vm.MyAddressAtyView;

import java.util.ArrayList;

/**
 * 我的地址
 *
 * @author 王鑫 on 2015/12/3.
 */
public class MyAddressAtyPst extends BasePst<MyAddressAtyView> {

    public void getAddressList(String sessionId) {
        if (TextUtils.isEmpty(sessionId)) {
            MyAddressAtyView view = getView();
            if (view != null) {
                LoginRequest.reLogin(UserEntity.getName(), UserEntity.getPwd());
                return;
            }
        }

        MyAddressAtyView view = getView();
        if (view != null) {
            view.showLoading(true);
        }

        GetAddressesRequest request = new GetAddressesRequest(sessionId);
        request.setOnResponseListener(new BaseRequest.OnResponseListener<ArrayList<AddressEntity>>() {
            @Override
            public void onSuccess(ArrayList<AddressEntity> addressEntities) {
                MyAddressAtyView view = getView();
                if (view != null) {
                    view.updateAddresses(addressEntities);
                    view.showLoading(false);
                }
            }

            @Override
            public void onFail(String message) {
                MyAddressAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                }
            }
        });
        request.request();

    }

    /**
     * 设置为默认收货地址
     *
     * @param sessionId 会话ID（登录凭证）
     * @param addressId 地址ID
     */
    public void setAsDefault(String sessionId, final String addressId) {

        MyAddressAtyView view = getView();
        if (view != null) {
            view.showLoading(true);
        }

        ModifyDefaultAddressRequest request = new ModifyDefaultAddressRequest(sessionId, addressId);
        request.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                MyAddressAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    getAddressList(UserEntity.getSessionId());
                }
            }

            @Override
            public void onFail(String message) {
                MyAddressAtyView view = getView();
                if (view != null) {
                    view.showMessage(message);
                    view.showLoading(false);
                }
            }
        });
        request.request();
    }

    /**
     * 删除地址
     * <p/>
     * 需要重新获取一遍地址，并重新设置默认地址（以防默认地址被删除）
     *
     * @param entity
     */
    public void deleteAddress(final AddressEntity entity) {

        if (null == entity) {
            return;
        }

        MyAddressAtyView view = getView();
        if (view != null) {
            view.showLoading(true);
        }

        DeleteAddressRequest deleteAddressRequest = new DeleteAddressRequest(entity.getId()+"");
        deleteAddressRequest.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                MyAddressAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    //万一老子把默认地址删了，还要给这个狗逼客户换一个默认地址（如果没有其他地址，那就歇菜)
                    getAddressList(UserEntity.getSessionId());

                }
            }

            @Override
            public void onFail(String message) {
                MyAddressAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    view.showMessage(message);
                }
            }
        });
        deleteAddressRequest.request();
    }
}
