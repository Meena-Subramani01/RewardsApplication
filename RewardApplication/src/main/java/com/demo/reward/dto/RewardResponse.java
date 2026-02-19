package com.demo.reward.dto;

import java.util.List;

public class RewardResponse {
	
	private List<CustomerRewardResponse> customerResponse;

	
	
	public RewardResponse(List<CustomerRewardResponse> customerResponse) {
		super();
		this.customerResponse = customerResponse;
	}

	public List<CustomerRewardResponse> getCustomerResponse() {
		return customerResponse;
	}

	public void setCustomerResponse(List<CustomerRewardResponse> customerResponse) {
		this.customerResponse = customerResponse;
	}
	
	

}
