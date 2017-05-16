package com.xhxkj.zhcs.temp;

/**
 *
 * 地址实体
 * @author 王鑫
 */

public class AddressBean implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfoBean userInfo;
	private String name;
	private String tel;
	private String address;

	// Constructors

	/** default constructor */
	public AddressBean() {
	}

	/** full constructor */
	public AddressBean(UserInfoBean userInfo, String name, String tel, String address) {
		this.userInfo = userInfo;
		this.name = name;
		this.tel = tel;
		this.address = address;
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

}