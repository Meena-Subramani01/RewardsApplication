package com.demo.reward.testcontroller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.reward.controller.RewardController;
import com.demo.reward.dto.CustomerRewardResponse;
import com.demo.reward.dto.RewardResponse;
import com.demo.reward.service.RewardService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import java.util.Arrays;




@WebMvcTest(RewardController.class)
public class RewardControllerTest {
	
	Long customerId = 3L;
	 
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private RewardService rewardsService;
	
	@Test
	void returnRewardSuccessfully() throws Exception {

	    Map<String, Double> monthly = new HashMap<>();
	    monthly.put("JANUARY", (double) 90);

	    CustomerRewardResponse customerReward =
	            new CustomerRewardResponse(
	                    1L,
	                    "Meena",
	                    monthly,
	                    (double) 90
	            );

	    
	    RewardResponse response =
	            new RewardResponse(Arrays.asList(customerReward));

	    
	    Mockito.when(rewardsService.getRewards(any(), any()))
	            .thenReturn(response);

	    mockMvc.perform(get("/api/v1/rewards/calculate")
	                    .param("startDate", "2025-01-01")
	                    .param("endDate", "2025-03-01"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.customerResponse[0].customerId").value(1))
	            .andExpect(jsonPath("$.customerResponse[0].totalRewards").value(90));
	}


	
	@Test
    void shouldReturnBadRequestForInvalidDate() throws Exception {

        Mockito.when(rewardsService.getRewards(any(), any()))
                .thenThrow(new RuntimeException("Start date cannot be after end date"));

        mockMvc.perform(get("/api/v1/rewards/calculate")
                        .param("startDate", "2025-04-01")
                        .param("endDate", "2025-01-01"))
                .andExpect(status().isInternalServerError());
    }
	

}