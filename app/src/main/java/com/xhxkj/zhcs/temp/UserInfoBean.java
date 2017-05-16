package com.xhxkj.zhcs.temp;

import java.util.ArrayList;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class UserInfoBean implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String pwd;
	private String tel;
	private String sessionId;
	private Integer resultCode;
	private String resultMessage;




	private ArrayList<OrderBean> orders = new ArrayList<>();
	private ArrayList<BuyerBean> collectBuyers = new ArrayList<>();
	private ArrayList<CartItemBean> cartItems = new ArrayList<>();
	private ArrayList<ShopBean> collectShops = new ArrayList<>();
	private ArrayList<JoinBean> joins = new ArrayList<>();
	private ArrayList<BuyCountBean> buyCounts = new ArrayList<>();
	private ArrayList<AddressBean> addresses = new ArrayList<>();
	private ArrayList<MsgBean> msgs = new ArrayList<>();

	// Constructors

	/** default constructor */
	public UserInfoBean() {
	}

	/** minimal constructor */
	public UserInfoBean(String name, String pwd, String tel) {
		this.name = name;
		this.pwd = pwd;
		this.tel = tel;
	}

	/** full constructor */
	public UserInfoBean(String name, String pwd, String tel, ArrayList<OrderBean> orders,
						ArrayList<BuyerBean> collectBuyers, ArrayList<CartItemBean> cartItems, ArrayList<ShopBean> collectShops, ArrayList<JoinBean> joins,
						ArrayList<BuyCountBean> buyCounts, ArrayList<AddressBean> addresses, ArrayList<MsgBean> msgs) {
		this.name = name;
		this.pwd = pwd;
		this.tel = tel;
		this.orders = orders;
		this.collectBuyers = collectBuyers;
		this.cartItems = cartItems;
		this.collectShops = collectShops;
		this.joins = joins;
		this.buyCounts = buyCounts;
		this.addresses = addresses;
		this.msgs = msgs;
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

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public ArrayList<OrderBean> getOrders() {
		return this.orders;
	}

	public void setOrders(ArrayList<OrderBean> orders) {
		this.orders = orders;
	}

	public ArrayList<BuyerBean> getCollectBuyers() {
		return this.collectBuyers;
	}

	public void setCollectBuyers(ArrayList<BuyerBean> collectBuyers) {
		this.collectBuyers = collectBuyers;
	}

	public ArrayList<CartItemBean> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(ArrayList<CartItemBean> cartItems) {
		this.cartItems = cartItems;
	}

	public ArrayList<ShopBean> getCollectShops() {
		return this.collectShops;
	}

	public void setCollectShops(ArrayList<ShopBean> collectShops) {
		this.collectShops = collectShops;
	}

	public ArrayList<JoinBean> getJoins() {
		return this.joins;
	}

	public void setJoins(ArrayList<JoinBean> joins) {
		this.joins = joins;
	}

	public ArrayList<BuyCountBean> getBuyCounts() {
		return this.buyCounts;
	}

	public void setBuyCounts(ArrayList<BuyCountBean> buyCounts) {
		this.buyCounts = buyCounts;
	}

	public ArrayList<AddressBean> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(ArrayList<AddressBean> addresses) {
		this.addresses = addresses;
	}

	public ArrayList<MsgBean> getMsgs() {
		return this.msgs;
	}

	public void setMsgs(ArrayList<MsgBean> msgs) {
		this.msgs = msgs;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}