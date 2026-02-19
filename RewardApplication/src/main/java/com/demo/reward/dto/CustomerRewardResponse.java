package com.demo.reward.dto;

import java.math.BigDecimal;
import java.util.Map;

public class CustomerRewardResponse {
	
	private Long customerId;
	private String customerName;
	private Map<String, BigDecimal> monthlyRewards;
	private BigDecimal totalRewards;
	
	public CustomerRewardResponse(Long customerId, String customerName,Map<String, BigDecimal> monthlyRewards, BigDecimal totalRewards) {
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

	public Map<String, BigDecimal> getMonthlyRewards() {
		return monthlyRewards;
	}

	public void setMonthlyRewards(Map<String, BigDecimal> monthlyRewards) {
		this.monthlyRewards = monthlyRewards;
	}

	public BigDecimal getTotalRewards() {
		return totalRewards;
	}

	public void setTotalRewards(BigDecimal totalRewards) {
		this.totalRewards = totalRewards;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	

}
