package com.xhxkj.zhcs.temp;

/**
 * 购物车单项商品条目
 *
 * @author 王鑫
 */
public class CartItemBean implements java.io.Serializable {

    // Fields

    private Integer id;
    private UserInfoBean userInfo;
    private GoodBean good;
    private Double num;

    // Constructors

    /**
     * default constructor
     */
    public CartItemBean() {
    }

    /**
     * full constructor
     */
    public CartItemBean(UserInfoBean userInfo, GoodBean good, Double num) {
        this.userInfo = userInfo;
        this.good = good;
        this.num = num;
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

    public GoodBean getGood() {
        return this.good;
    }

    public void setGood(GoodBean good) {
        this.good = good;
    }

    public Double getNum() {
        return this.num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

}