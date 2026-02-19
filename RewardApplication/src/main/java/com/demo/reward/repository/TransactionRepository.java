package com.demo.reward.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.reward.entity.Customer;
import com.demo.reward.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	public List<Transaction> findByCustomer(Customer customerId);
}