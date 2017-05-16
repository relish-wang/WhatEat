package com.xhxkj.zhcs.temp;

import com.xhxkj.zhcs.util.TimeUtils;

/**
 * 订单
 * <p/>
 * Created by 鑫 on 2015/11/9.
 * @deprecated
 */
public class OrderBeanDe implements Comparable<OrderBeanDe> {
    private String date;
    private long dateLong;
    private boolean hasEvaluated;
    private int ivResId;
    private String storeName;
    private String goods;

    public OrderBeanDe(long dateLong, boolean hasEvaluated, int ivResId, String storeName, String goods) {
        this.dateLong = dateLong;
        this.date = TimeUtils.longToTimeHMFormat(dateLong);
        this.hasEvaluated = hasEvaluated;
        this.ivResId = ivResId;
        this.storeName = storeName;
        this.goods = goods;
    }

    public long getDateLong() {
        return dateLong;
    }

    public void setDateLong(long dateLong) {
        this.dateLong = dateLong;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isHasEvaluated() {
        return hasEvaluated;
    }

    public void setHasEvaluated(boolean hasEvaluated) {
        this.hasEvaluated = hasEvaluated;
    }

    public int getIvResId() {
        return ivResId;
    }

    public void setIvResId(int ivResId) {
        this.ivResId = ivResId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    @Override
    public int compareTo(OrderBeanDe orderBean) {

        return 0;
    }
}
