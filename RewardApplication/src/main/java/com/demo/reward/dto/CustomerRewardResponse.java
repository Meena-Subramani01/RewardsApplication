package com.demo.reward.dto;

import java.math.BigDecimal;
import java.util.Map;

public class CustomerRewardResponse {
	
	private Long customerId;
	private String customerName;
	private Map<String, Double> monthlyRewards;
	private Double totalRewards;
	
	public CustomerRewardResponse(Long customerId, String customerName,Map<String, Double> monthlyRewards, Double totalRewards) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.monthlyRewards = monthlyRewards;
		this.totalRewards = totalRewards;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Map<String, Double> getMonthlyRewards() {
		return monthlyRewards;
	}

	public void setMonthlyRewards(Map<String, Double> monthlyRewards) {
		this.monthlyRewards = monthlyRewards;
	}

	public Double getTotalRewards() {
		return totalRewards;
	}

	public void setTotalRewards(Double totalRewards) {
		this.totalRewards = totalRewards;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	

}
