package com.xhxkj.zhcs.temp;

/**
 * 购买次数实体
 * @author 王鑫
 */

public class BuyCountBean implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfoBean userInfo;
	private GoodBean good;
	private Integer count;

	// Constructors

	/** default constructor */
	public BuyCountBean() {
	}

	/** full constructor */
	public BuyCountBean(UserInfoBean userInfo, GoodBean good, Integer count) {
		this.userInfo = userInfo;
		this.good = good;
		this.count = count;
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

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}