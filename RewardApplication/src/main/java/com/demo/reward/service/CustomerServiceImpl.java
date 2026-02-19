package com.demo.reward.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.reward.dto.CustomerRequestDto;
import com.demo.reward.entity.Customer;
import com.demo.reward.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer createCustomer(CustomerRequestDto request) {
		Customer customer = new Customer();
		customer.setCustomerName(request.getName());
		customer.setPhNumber(request.getPhNumber());
		customer.setAddress(request.getAddress());
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

}