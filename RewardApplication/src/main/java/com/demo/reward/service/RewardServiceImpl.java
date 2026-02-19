package com.demo.reward.service;

import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.reward.entity.Customer;
import com.demo.reward.entity.Transaction;
import com.demo.reward.repository.TransactionRepository;

@Service
public class RewardServiceImpl implements RewardService{

	private final TransactionRepository transactionRepository;
	private final CustomerService customerService;
	
	
	@Autowired
	public RewardServiceImpl(TransactionRepository transactionRepository, 
			CustomerService customerService) {
		this.transactionRepository = transactionRepository;
		this.customerService = customerService;
	}
	
	@Override
	public Map<String, Object> getRewards(Long customerId) {
		Customer customer = customerService.getCustomer(customerId);
		List<Transaction> transactionList = transactionRepository.findByCustomer(customer);
		Map<Month, Integer> monthlyRewards = new LinkedHashMap<>();
		int totalRewards = 0;
		
		for(Transaction t : transactionList) {
			int points = calculatePoints(t.getAmount());
			Month month = t.getTransactionDate().getMonth();
			
			monthlyRewards.put(month, 
					monthlyRewards.getOrDefault(month, 0)+points);
			totalRewards +=points;
		}
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("customerId", customerId);
		response.put("monthlyRewards", monthlyRewards);
		response.put("totalRewards", totalRewards);
		
		return response;
	}
	
	private int calculatePoints(double amount) {
		int points =0;
		if(amount >100) {
			points +=(amount-100)*2;
			amount=100;
		}
		if(amount >50) {
			points+=(amount-50);
		}
		
		return points;
	}
	
}