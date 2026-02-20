package com.demo.reward.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.reward.dto.CustomerRewardResponse;
import com.demo.reward.dto.RewardResponse;
import com.demo.reward.entity.Customer;
import com.demo.reward.entity.Transaction;
import com.demo.reward.exception.InvalidDateException;
import com.demo.reward.exception.TransactionNotFoundException;
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
	public RewardResponse getRewards(LocalDate stratDate, LocalDate endDate) {
		validateDate(stratDate, endDate);
		
		List<Transaction> transactionList = transactionRepository.findByTransactionDateBetween(stratDate, endDate);
		if (transactionList.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found");
        }
		Map<Customer, List<Transaction>> customerGroup = transactionList.stream()
							.collect(Collectors.groupingBy(Transaction::getCustomer));
		
		List<CustomerRewardResponse> response = customerGroup.entrySet()
					.stream().map(entry -> calculateCustomerRewards(entry.getKey(), 
									entry.getValue())).toList();
		
		
		
		return new RewardResponse(response);
	}
	
	private void validateDate(LocalDate startDate, LocalDate endDate) {
		if(startDate.isAfter(endDate)) {
			throw new InvalidDateException("Start date cannot be after end date");
		}
		if(ChronoUnit.MONTHS.between(startDate, endDate) >3) {
			throw new InvalidDateException("Date range cannot exceed 3 months");
		}
	}
	
	
	private CustomerRewardResponse calculateCustomerRewards(Customer customer, 
					List<Transaction> transactionList) {
		Map<String, Double> monthly = transactionList.stream()
						.collect(Collectors.groupingBy(
								t -> t.getTransactionDate().getMonth().name(),
								Collectors.summingDouble(t ->calculatePoints(t.getAmount()))
								));
		Double total = monthly.values().stream().mapToDouble(Double::doubleValue).sum();
		return new CustomerRewardResponse(customer.getCustomerId(), customer.getCustomerName(),
				monthly, total);
		
	}
	
	private double calculatePoints(BigDecimal amount) {
		double amt = amount.doubleValue();
		double points=0;
		
		if(amt > 100) {
			points += (amt-100)*2;
			amt =100;
			
		}
		if(amt >50) {
			points += (amt-50);
		}
		return points;
	}

	
}