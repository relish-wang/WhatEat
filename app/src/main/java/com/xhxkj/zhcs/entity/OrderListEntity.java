package com.xhxkj.zhcs.entity;

import com.xhxkj.zhcs.base.BaseEntity;

import java.util.ArrayList;

/**
 * Created by 鑫 on 2015/12/1.
 */
public class OrderListEntity  extends BaseEntity {
    private Integer pageNum;
    private Integer totalNum;
    private Integer totalPageNum;
    private ArrayList<OrderEntity> orders;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public ArrayList<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderEntity> orders) {
        this.orders = orders;
    }
}
