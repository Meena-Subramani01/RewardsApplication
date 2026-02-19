package com.demo.reward.testservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.reward.entity.Customer;
import com.demo.reward.entity.Transaction;
import com.demo.reward.repository.TransactionRepository;
import com.demo.reward.service.CustomerService;
import com.demo.reward.service.RewardServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {
	
	@Mock private TransactionRepository transactionRepository;
	@Mock private CustomerService customerService;
	
	@InjectMocks private RewardServiceImpl rewardsService;
	
	@Test
	void testCalculateRewards() {
		Long customerId =3L;
		Customer c= new Customer();
		
		Transaction t1 = new Transaction();
		t1.setAmount(120.0);
		t1.setCustomer(c);
		t1.setTransactionDate(LocalDate.of(2025, 1, 10));
		
		when(customerService.getCustomer(customerId)).thenReturn(c);
		when(transactionRepository.findByCustomer(c)).thenReturn(List.of(t1));
		
		Map<String, Object> result = rewardsService.getRewards(customerId);
		
		assertThat(result.get("customerId")).isEqualTo(customerId);
		
		Map<Month, Integer> monthly = (Map<Month, Integer>)result.get("monthlyRewards");
		assertThat(monthly.get(Month.JANUARY)).isEqualTo(90);
		assertThat(result.get("totalRewards")).isEqualTo(90);
	}

}