package com.demo.reward.service;

import java.util.List;

import com.demo.reward.dto.CustomerRequestDto;
import com.demo.reward.entity.Customer;

public interface CustomerService {
	
	public Customer createCustomer(CustomerRequestDto request);
	
	public Customer getCustomer(Long id);
	
	public List<Customer> getAllCustomer(); 

}