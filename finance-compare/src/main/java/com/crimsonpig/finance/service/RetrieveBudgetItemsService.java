package com.crimsonpig.finance.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.domain.BudgetItem;

@Component
public class RetrieveBudgetItemsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RetrieveBudgetItemsService.class);

	@Value("${finance.comparison.budgetitems.path}")
	private String path;
	
	private RestTemplate restTemplate;

	public RetrieveBudgetItemsService(){
		restTemplate = new RestTemplate();
	}
	
	public List<BudgetItem> retrieveBudgetItems(String startDt, String endDt, String category){
		String urlTemplate = path + "?startDt=%s&endDt=%s";
		String budgetUrl = String.format(urlTemplate, startDt, endDt);
		if(category != null){
			budgetUrl += "&category=" + category;
		}
		BudgetItem[] budgetItems = restTemplate.getForObject(budgetUrl, BudgetItem[].class);

		return Arrays.asList(budgetItems);
	}
	
}
