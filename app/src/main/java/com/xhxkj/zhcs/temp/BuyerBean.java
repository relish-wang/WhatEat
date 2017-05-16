package com.xhxkj.zhcs.temp;

import java.util.ArrayList;

/**
 * 买手实体
 *
 * @author 王鑫
 */

public class BuyerBean implements java.io.Serializable {

    // Fields

    private Integer id;
    private String name;
    private String tel;
    private String address;
    private Integer buyCount;
    private Double comment;
    private Integer commentCount;
    private Boolean isCollected;
    private ArrayList<OrderBean> orders = new ArrayList<>();

    // Constructors

    /**
     * default constructor
     */
    public BuyerBean() {
    }

    /**
     * minimal constructor
     */
    public BuyerBean(String name, String tel, String address, Integer buyCount,
                     Integer commentCount, Boolean isCollected, Double comment) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.buyCount = buyCount;
        this.commentCount = commentCount;
        this.isCollected = isCollected;
        this.comment = comment;
    }

    /**
     * full constructor
     */
    public BuyerBean(String name, String tel, String address, Integer buyCount,
                     Double comment, Integer commentCount, Boolean isCollected, ArrayList<OrderBean> orders) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.buyCount = buyCount;
        this.comment = comment;
        this.commentCount = commentCount;
        this.isCollected = isCollected;
        this.orders = orders;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Double getComment() {
        return this.comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }

    public Integer getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Boolean isCollected() {
        return isCollected;
    }

    public void setIsCollected(Boolean isCollected) {
        this.isCollected = isCollected;
    }

    public ArrayList<OrderBean> getOrders() {
        return this.orders;
    }

    public void setOrders(ArrayList<OrderBean> orders) {
        this.orders = orders;
    }

}