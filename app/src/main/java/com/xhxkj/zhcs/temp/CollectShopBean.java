package com.xhxkj.zhcs.temp;

/**
 * 收藏的商店实体
 *
 * @author 王鑫
 */
public class CollectShopBean implements java.io.Serializable {

    // Fields

    private Integer id;
    private ShopBean shop;
    private UserInfoBean userInfo;

    // Constructors

    /**
     * default constructor
     */
    public CollectShopBean() {
    }

    /**
     * full constructor
     */
    public CollectShopBean(ShopBean shop, UserInfoBean userInfo) {
        this.shop = shop;
        this.userInfo = userInfo;
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

    public UserInfoBean getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

}