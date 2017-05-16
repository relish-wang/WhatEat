package com.xhxkj.zhcs.temp;

/**
 * Join entity. @author MyEclipse Persistence Tools
 */

public class JoinBean implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfoBean userInfo;
	private FamilyBean family;
	private String nickname;

	// Constructors

	/** default constructor */
	public JoinBean() {
	}

	/** minimal constructor */
	public JoinBean(UserInfoBean userInfo, FamilyBean family) {
		this.userInfo = userInfo;
		this.family = family;
	}

	/** full constructor */
	public JoinBean(UserInfoBean userInfo, FamilyBean family, String nickname) {
		this.userInfo = userInfo;
		this.family = family;
		this.nickname = nickname;
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

	public FamilyBean getFamily() {
		return this.family;
	}

	public void setFamily(FamilyBean family) {
		this.family = family;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}