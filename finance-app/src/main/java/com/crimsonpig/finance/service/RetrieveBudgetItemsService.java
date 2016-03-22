package com.crimsonpig.finance.service;

import java.time.LocalDate;

import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.api.PlannedBudgetItem;

public class RetrieveBudgetItemsService {

	private static final String HOST = "linuxbox";
	
	private static final String PATH = HOST + "/budget";
	
	private RestTemplate restTemplate;
	
	public RetrieveBudgetItemsService(){
		restTemplate = new RestTemplate();
	}
	
	public PlannedBudgetItem[] retrieveBudgetItems(String startDt, String endDt){

		String budgetUrl = String.format("http://%s?startDt=%s&endDt=%s", PATH, startDt, endDt);
		PlannedBudgetItem[] budgetItems = restTemplate.getForObject(budgetUrl, PlannedBudgetItem[].class);
		
		return budgetItems;
	}
	
}
