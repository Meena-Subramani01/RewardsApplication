package com.demo.reward.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.reward.service.RewardService;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
	
	private final RewardService rewardService;
	
	@Autowired
	public RewardController(RewardService rewardService) {
		this.rewardService = rewardService;
	}
	
	@GetMapping("/get-rewards/{customerId}")
	public Map<String, Object> getRewards(@PathVariable Long customerId){
		return rewardService.getRewards(customerId);
	}

}