package com.poc.app.myRetail.response.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductVO {
	@JsonProperty(value="id")
	String productId;
	@JsonProperty(value="name")
	String name;
	@JsonProperty(value="current_price")
	CurrentPriceVO currentprice;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CurrentPriceVO getCurrentprice() {
		return currentprice;
	}

	public void setCurrentprice(CurrentPriceVO currentprice) {
		this.currentprice = currentprice;
	}

	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", name=" + name + ", currentprice=" + currentprice + "]";
	}

}
