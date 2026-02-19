package com.demo.reward.service;

import java.time.LocalDate;


import com.demo.reward.dto.RewardResponse;

public interface RewardService {
	
	public RewardResponse getRewards(LocalDate stratDate, LocalDate endDate);

}