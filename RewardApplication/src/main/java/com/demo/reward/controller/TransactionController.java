package com.demo.reward.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.reward.dto.TransactionRequestDto;
import com.demo.reward.entity.Transaction;
import com.demo.reward.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
	private final TransactionService transactionService;
	
	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService=transactionService;
	}
	
	@PostMapping("/add-transaction")
	public Transaction createTransaction(@RequestBody TransactionRequestDto request) {
		return transactionService.addTransaction(request);
	}
	
	@GetMapping("/getTransaction")
	public List<Transaction> geAllTransaction(){
		return transactionService.getAllTransaction();
	}
	
	@GetMapping("/get-transaction-id/{transactionId}")
	public Transaction getCustomerById(@PathVariable Long transactionId) {
		return transactionService.getTransaction(transactionId);
	}

}