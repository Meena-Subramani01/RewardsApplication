package com.demo.reward.testservice;

import com.demo.reward.entity.Customer;
import com.demo.reward.entity.Transaction;
import com.demo.reward.exception.InvalidDateException;
import com.demo.reward.repository.TransactionRepository;
import com.demo.reward.service.RewardService;
import com.demo.reward.service.RewardServiceImpl;
import com.demo.reward.dto.RewardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RewardsServiceTest {

    @InjectMocks
    private RewardServiceImpl rewardsService;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void shouldCalculatePointsAbove100() {

        Customer customer = new Customer(1L, "Test", "255345661","Chennai");

        List<Transaction> transactions = List.of(
                new Transaction(1L,
                        BigDecimal.valueOf(120),
                        LocalDate.of(2025, 1, 10),
                        customer)
        );

        when(transactionRepository
                .findByTransactionDateBetween(any(), any()))
                .thenReturn(transactions);

        RewardResponse response =
                rewardsService.getRewards(
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,3,1));

        assertEquals(90,
                response.getCustomerResponse().get(0).getTotalRewards());
    }

   
    @Test
    void shouldCalculatePointsBetween50And100() {

        Customer customer = new Customer(1L, "Test", "255345661","Chennai");

        List<Transaction> transactions = List.of(
                new Transaction(1L,
                        BigDecimal.valueOf(75),
                        LocalDate.of(2025, 1, 10),
                        customer)
        );

        when(transactionRepository
                .findByTransactionDateBetween(any(), any()))
                .thenReturn(transactions);

        RewardResponse response =
                rewardsService.getRewards(
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,3,1));

        assertEquals(25,
                response.getCustomerResponse().get(0).getTotalRewards());
    }

   
    @Test
    void shouldReturnZeroPoints() {

        Customer customer = new Customer(1L, "Test", "255345661","Chennai");

        List<Transaction> transactions = List.of(
                new Transaction(1L,
                        BigDecimal.valueOf(40),
                        LocalDate.of(2025, 1, 10),
                        customer)
        );

        when(transactionRepository
                .findByTransactionDateBetween(any(), any()))
                .thenReturn(transactions);

        RewardResponse response =
                rewardsService.getRewards(
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,3,1));

        assertEquals(0,
                response.getCustomerResponse().get(0).getTotalRewards());
    }

    
    @Test
    void shouldThrowExceptionIfStartAfterEnd() {

        assertThrows(InvalidDateException.class, () ->
                rewardsService.getRewards(
                        LocalDate.of(2025,4,1),
                        LocalDate.of(2025,1,1)));
    }

    
    @Test
    void shouldThrowExceptionIfMoreThan3Months() {

        assertThrows(InvalidDateException.class, () ->
                rewardsService.getRewards(
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,6,1)));
    }
    
    @Test
    void testFractionalTransactionAmount() {

        BigDecimal amount = new BigDecimal("120.75");

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDate.of(2025, 1, 10));
        transaction.setCustomer(new Customer(1L, "John","2435355225","Bangalore"));

        when(transactionRepository.findByTransactionDateBetween(any(), any()))
                .thenReturn(List.of(transaction));

        RewardResponse response =
                rewardsService.getRewards(
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,3,1)
                );

        double total = response.getCustomerResponse()
                               .get(0)
                               .getTotalRewards();

        assertEquals(91.5, total);
    }
    @Test
    void testMoreThanThreeMonthsRange() {

        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 5, 1);

        assertThrows(InvalidDateException.class,
                () -> rewardsService.getRewards(start, end));
    }
    
    @Test
    void testStartDateAfterEndDate() {

        LocalDate start = LocalDate.of(2025, 3, 1);
        LocalDate end = LocalDate.of(2025, 1, 1);

        assertThrows(InvalidDateException.class,
                () -> rewardsService.getRewards(start, end));
    }
}
