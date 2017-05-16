package com.xhxkj.zhcs.temp;

import java.util.ArrayList;

/**
 * Good entity. @author MyEclipse Persistence Tools
 */

public class GoodBean implements java.io.Serializable {

    // Fields

    private Integer id;
    private ShopBean shop;
    private String name;
    private String unit;
    private Double value;
    private Double comment;
    private Integer commentCount;
    private Short type;
    private ArrayList<BuyCountBean> buyCounts = new ArrayList<>();
    private ArrayList<CartItemBean> cartItems = new ArrayList<>();
    private ArrayList<GoodDetailsBean> goodDetailses = new ArrayList<>();

    // Constructors

    /**
     * default constructor
     */
    public GoodBean() {
    }

    /**
     * minimal constructor
     */
    public GoodBean(ShopBean shop, String name, String unit, Double value) {
        this.shop = shop;
        this.name = name;
        this.unit = unit;
        this.value = value;
    }

    /**
     * full constructor
     */
    public GoodBean(ShopBean shop, String name, String unit, Double value,
                    Double comment, Integer commentCount, Short type, ArrayList<BuyCountBean> buyCounts,
                    ArrayList<CartItemBean> cartItems, ArrayList<GoodDetailsBean> goodDetailses) {
        this.shop = shop;
        this.name = name;
        this.unit = unit;
        this.value = value;
        this.comment = comment;
        this.commentCount = commentCount;
        this.type = type;
        this.buyCounts = buyCounts;
        this.cartItems = cartItems;
        this.goodDetailses = goodDetailses;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShopBean getShop() {
        return this.shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Integer getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public ArrayList<BuyCountBean> getBuyCounts() {
        return this.buyCounts;
    }

    public void setBuyCounts(ArrayList<BuyCountBean> buyCounts) {
        this.buyCounts = buyCounts;
    }

    public ArrayList<CartItemBean> getCartItems() {
        return this.cartItems;
    }

    public void setCartItems(ArrayList<CartItemBean> cartItems) {
        this.cartItems = cartItems;
    }

    public ArrayList<GoodDetailsBean> getGoodDetailses() {
        return this.goodDetailses;
    }

    public void setGoodDetailses(ArrayList<GoodDetailsBean> goodDetailses) {
        this.goodDetailses = goodDetailses;
    }

}