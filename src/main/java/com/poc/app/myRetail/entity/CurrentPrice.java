package com.poc.app.myRetail.entity;

public class CurrentPrice {
	private Double value;
	private String currencyCode;

	public CurrentPrice() {

	}

	public CurrentPrice(Double value, String currencyCode) {
		this.value = value;
		this.currencyCode = currencyCode;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "CurrentPrice [value=" + value + ", currencyCode=" + currencyCode + "]";
	}

}
