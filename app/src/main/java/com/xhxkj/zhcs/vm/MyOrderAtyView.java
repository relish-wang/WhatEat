package com.xhxkj.zhcs.vm;

import com.xhxkj.zhcs.base.BaseView;
import com.xhxkj.zhcs.entity.OrderListEntity;

/**
 * 我的订单
 * Created by 鑫 on 2015/12/1.
 */
public interface MyOrderAtyView extends BaseView {
    /**
     * 更新
     *
     * @param orderList 订单
     */
    void update(OrderListEntity orderList);


}
