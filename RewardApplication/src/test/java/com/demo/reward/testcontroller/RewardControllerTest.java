package com.demo.reward.testcontroller;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.reward.controller.RewardController;
import com.demo.reward.service.RewardService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {
	
	Long customerId = 3L;
	 
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private RewardService rewardsService;
	
	@Test
	void testGetRewardsApi() throws Exception{
		
		when(rewardsService.getRewards(3L))
			.thenReturn(Map.of(
					"customerId",3L,
					"monthlyRewards",Map.of("JANUARY",90),
					"totalRewards",90
					));
		mockMvc.perform(get("/api/rewards/get-rewards/{customerId}",customerId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").value(3))
				.andExpect(jsonPath("$.totalRewards").value(90));
	}
	

}