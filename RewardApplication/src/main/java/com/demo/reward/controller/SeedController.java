package com.demo.reward.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.reward.entity.Customer;
import com.demo.reward.entity.Transaction;
import com.demo.reward.repository.CustomerRepository;
import com.demo.reward.repository.TransactionRepository;

@RestController
@RequestMapping("/api/v1")
public class SeedController {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public SeedController(CustomerRepository customerRepository,
                          TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/seed")
    public String seedData() {

        // Create customers
        Customer c1 = new Customer(null, "John","7654321890","Chennai");
        Customer c2 = new Customer(null, "Ravi", "5432176898","Mumbai");

        customerRepository.saveAll(List.of(c1));

        // Create transactions
        Transaction t1 = new Transaction(
                null,
                BigDecimal.valueOf(120),
                LocalDate.of(2025, 1, 10),
                c1
        );

        Transaction t2 = new Transaction(
                null,
                BigDecimal.valueOf(75),
                LocalDate.of(2025, 2, 5),
                c1
        );

        Transaction t3 = new Transaction(
                null,
                BigDecimal.valueOf(200),
                LocalDate.of(2025, 1, 15),
                c2
        );

        transactionRepository.saveAll(List.of(t1,t2,t3));

        return "Data Seeded Successfully!";
    }
}

