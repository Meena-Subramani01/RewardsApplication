package com.demo.reward.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequestDto {
	
	private Long customerId;
	private BigDecimal amount;
	private LocalDate transactionDate;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	

}