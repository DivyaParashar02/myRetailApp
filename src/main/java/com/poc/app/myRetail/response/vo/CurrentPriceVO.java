package com.poc.app.myRetail.response.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrentPriceVO {
	@JsonProperty(value="value")
	Double value;
	@JsonProperty(value="currency_code")
	String currencyCode;

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
		return "CurrentPriceVO [value=" + value + ", currencyCode=" + currencyCode + "]";
	}

}
