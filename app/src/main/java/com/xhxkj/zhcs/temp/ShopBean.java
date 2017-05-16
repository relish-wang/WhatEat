package com.xhxkj.zhcs.temp;

import java.util.ArrayList;

/**
 * Shop entity. @author MyEclipse Persistence Tools
 */

public class ShopBean implements java.io.Serializable {

    // Fields

    private Integer id;
    private String name;
    private Integer type;
    private Integer saleCount;
    private Integer commentCount;
    private Double comment;
    private Double lng;
    private Double lat;
    private Double distance;
    private ArrayList<CollectShopBean> collectShops = new ArrayList<>();
    private ArrayList<GoodBean> goods = new ArrayList<>();

    // Constructors

    /**
     * default constructor
     */
    public ShopBean(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    /**
     * minimal constructor
     */
    public ShopBean(String name, Integer type, Integer saleCount, Double comment) {
        this.name = name;
        this.type = type;
        this.saleCount = saleCount;
        this.comment = comment;
    }

    /**
     * full constructor
     */
    public ShopBean(String name, Integer type, Integer saleCount,
                    Integer commentCount, Double comment, Double lng, Double lat,
                    ArrayList<CollectShopBean> collectShops, ArrayList<GoodBean> goods) {
        this.name = name;
        this.type = type;
        this.saleCount = saleCount;
        this.commentCount = commentCount;
        this.comment = comment;
        this.lng = lng;
        this.lat = lat;
        this.collectShops = collectShops;
        this.goods = goods;
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

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSaleCount() {
        return this.saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Double getComment() {
        return this.comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public ArrayList<CollectShopBean> getCollectShops() {
        return this.collectShops;
    }

    public void setCollectShops(ArrayList<CollectShopBean> collectShops) {
        this.collectShops = collectShops;
    }

    public ArrayList<GoodBean> getGoods() {
        return this.goods;
    }

    public void setGoods(ArrayList<GoodBean> goods) {
        this.goods = goods;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}