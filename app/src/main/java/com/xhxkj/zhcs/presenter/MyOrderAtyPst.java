package com.xhxkj.zhcs.presenter;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.entity.OrderListEntity;
import com.xhxkj.zhcs.network.GetOrdersRequest;
import com.xhxkj.zhcs.vm.MyOrderAtyView;

/**
 * 我的订单
 * Created by 鑫 on 2015/12/1.
 */
public class MyOrderAtyPst extends BasePst<MyOrderAtyView> {

    public void update(String sessionId, Integer currentPage, Integer pageSize) {
        MyOrderAtyView view = getView();
        if (view != null) {
            view.showLoading(true);
        }

        GetOrdersRequest request = new GetOrdersRequest(sessionId, currentPage, pageSize);
        request.setOnResponseListener(new BaseRequest.OnResponseListener<OrderListEntity>() {
            @Override
            public void onSuccess(OrderListEntity orderListEntity) {
                MyOrderAtyView view = getView();
                if (view == null) return;
                view.update(orderListEntity);
                view.showLoading(false);
            }

            @Override
            public void onFail(String message) {
                MyOrderAtyView view = getView();
                if (view != null) {
                    view.showLoading(false);
                    view.showMessage(message);
                }
            }
        });
        request.request();
    }

}
