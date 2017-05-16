package com.xhxkj.zhcs.vm;

import com.xhxkj.zhcs.base.BaseView;
import com.xhxkj.zhcs.entity.AddressEntity;

import java.util.ArrayList;

/**
 * 我的地址
 *
 * @author 王鑫
 */
public interface MyAddressAtyView extends BaseView {
    /**
     * 更新地址列表
     *
     * @param addresses 地址列表
     */
    void updateAddresses(ArrayList<AddressEntity> addresses);
}
