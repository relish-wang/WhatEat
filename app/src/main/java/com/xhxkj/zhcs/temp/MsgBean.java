package com.xhxkj.zhcs.temp;

import java.sql.Timestamp;

/**
 * Msg entity. @author MyEclipse Persistence Tools
 */

public class MsgBean implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfoBean userInfo;
	private FamilyBean family;
	private String chatContent;
	private Timestamp date;

	// Constructors

	/** default constructor */
	public MsgBean() {
	}

	/** full constructor */
	public MsgBean(UserInfoBean userInfo, FamilyBean family, String chatContent,
				   Timestamp date) {
		this.userInfo = userInfo;
		this.family = family;
		this.chatContent = chatContent;
		this.date = date;
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

	public String getChatContent() {
		return this.chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}