package com.demo.reward.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.reward.dto.TransactionRequestDto;
import com.demo.reward.entity.Customer;
import com.demo.reward.entity.Transaction;
import com.demo.reward.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository transactionRepository;
	private final CustomerService customerService;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository,
			CustomerService customerService) {
		this.transactionRepository = transactionRepository;
		this.customerService = customerService;
	}
	
	@Override
	public Transaction addTransaction(TransactionRequestDto request) {
		Customer customer = customerService.getCustomer(request.getCustomerId());
		
		Transaction transaction = new Transaction();
		transaction.setAmount(request.getAmount());
		transaction.setTransactionDate(request.getTransactionDate());
		transaction.setCustomer(customer);
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction getTransaction(Long id) {
		return transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found:"+id));
	}

	@Override
	public List<Transaction> getAllTransaction() {
		return transactionRepository.findAll();
	}

}