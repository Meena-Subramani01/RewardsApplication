package com.demo.reward.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.reward.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}