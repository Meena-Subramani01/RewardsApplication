package com.demo.reward.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.reward.dto.RewardResponse;
import com.demo.reward.service.RewardService;

@RestController
@RequestMapping("/api/v1/rewards")
public class RewardController {
	
	private final RewardService rewardService;
	
	@Autowired
	public RewardController(RewardService rewardService) {
		this.rewardService = rewardService;
	}
	
	@GetMapping("/calculate")
	public RewardResponse getRewards(@RequestParam LocalDate startDate, 
			@RequestParam LocalDate endDate){
		return rewardService.getRewards(startDate, endDate);
	}

}