package com.demo.reward.service;

import java.util.List;

import com.demo.reward.dto.TransactionRequestDto;
import com.demo.reward.entity.Transaction;

public interface TransactionService {
	
	public Transaction addTransaction(TransactionRequestDto request);
	public Transaction getTransaction(Long id);
	public List<Transaction> getAllTransaction();

}