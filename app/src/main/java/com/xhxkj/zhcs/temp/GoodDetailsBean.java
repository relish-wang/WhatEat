package com.xhxkj.zhcs.temp;

/**
 * GoodDetails entity. @author MyEclipse Persistence Tools
 */

public class GoodDetailsBean implements java.io.Serializable {

	// Fields

	private Integer id;
	private OrderBean order;
	private GoodBean good;
	private Double buyValue;

	// Constructors

	/** default constructor */
	public GoodDetailsBean() {
	}

	/** full constructor */
	public GoodDetailsBean(OrderBean order, GoodBean good, Double buyValue) {
		this.order = order;
		this.good = good;
		this.buyValue = buyValue;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderBean getOrder() {
		return this.order;
	}

	public void setOrder(OrderBean order) {
		this.order = order;
	}

	public GoodBean getGood() {
		return this.good;
	}

	public void setGood(GoodBean good) {
		this.good = good;
	}

	public Double getBuyValue() {
		return this.buyValue;
	}

	public void setBuyValue(Double buyValue) {
		this.buyValue = buyValue;
	}

}