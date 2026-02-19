package com.demo.reward.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.reward.dto.CustomerRequestDto;
import com.demo.reward.entity.Customer;
import com.demo.reward.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@PostMapping("/add-customer")
	public Customer createCustomer(@RequestBody CustomerRequestDto request) {
		return customerService.createCustomer(request);
	}
	
	@GetMapping("/getCustomer")
	public List<Customer> geAlltCustomer(){
		return customerService.getAllCustomer();
	}
	
	@GetMapping("/get-customer-id/{customerId}")
	public Customer getCustomerById(@PathVariable Long customerId) {
		return customerService.getCustomer(customerId);
	}

}