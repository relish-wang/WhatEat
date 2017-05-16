package com.xhxkj.zhcs.temp;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class OrderBean implements java.io.Serializable {

    // Fields

    private Integer id;
    private UserInfoBean userInfo;
    private BuyerBean buyerBean;
    private Timestamp date;
    private Double value;
    private Double comment;
    private String userId;
    private Short status;
    private ArrayList<GoodDetailsBean> goodDetailses = new ArrayList<>();

    // Constructors

    /**
     * default constructor
     */
    public OrderBean() {
    }

    /**
     * minimal constructor
     */
    public OrderBean(Timestamp date, Double value, String userId, Short status) {
        this.date = date;
        this.value = value;
        this.userId = userId;
        this.status = status;
    }

    /**
     * full constructor
     */
    public OrderBean(UserInfoBean userInfo, BuyerBean buyerBean, Timestamp date, Double value,
                     Double comment, String userId, Short status, ArrayList<GoodDetailsBean> goodDetailses) {
        this.userInfo = userInfo;
        this.buyerBean = buyerBean;
        this.date = date;
        this.value = value;
        this.comment = comment;
        this.userId = userId;
        this.status = status;
        this.goodDetailses = goodDetailses;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserInfoBean getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public BuyerBean getBuyerBean() {
        return this.buyerBean;
    }

    public void setBuyerBean(BuyerBean buyerBean) {
        this.buyerBean = buyerBean;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getComment() {
        return this.comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public ArrayList<GoodDetailsBean> getGoodDetailses() {
        return this.goodDetailses;
    }

    public void setGoodDetailses(ArrayList<GoodDetailsBean> goodDetailses) {
        this.goodDetailses = goodDetailses;
    }

}