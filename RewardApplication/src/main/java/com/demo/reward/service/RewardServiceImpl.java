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
		Map<String, BigDecimal> monthly = transactionList.stream()
						.collect(Collectors.groupingBy(
								t -> t.getTransactionDate().getMonth().name(),
								Collectors.mapping(t -> calculatePoints(t.getAmount()), 
										Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
										)));
		BigDecimal total = monthly.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		return new CustomerRewardResponse(customer.getCustomerId(), customer.getCustomerName(),
				monthly, total);
		
	}
	
	private BigDecimal calculatePoints(BigDecimal amount) {
		BigDecimal points = BigDecimal.ZERO;
		
		if(amount.compareTo(BigDecimal.valueOf(100))>0) {
			points = points.add(amount.subtract(BigDecimal.valueOf(100)))
					.multiply(BigDecimal.valueOf(2));
			points = points.add(BigDecimal.valueOf(50));
		}
		else if(amount.compareTo(BigDecimal.valueOf(50)) >0) {
			points = points.add(amount.subtract(BigDecimal.valueOf(50)));
		}
		return points;
	}

	
}