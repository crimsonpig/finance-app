package com.crimsonpig.finance.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.domain.BudgetItem;

public class RetrieveBudgetItemsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RetrieveBudgetItemsService.class);

	private static final String HOST = "linuxbox";
	
	private static final String PATH = HOST + "/budget";
	
	private static final String URL_TEMPLATE = "http://" + PATH + "?startDt=%s&endDt=%s";
	
	private RestTemplate restTemplate;

	public RetrieveBudgetItemsService(){
		restTemplate = new RestTemplate();
	}
	
	public List<BudgetItem> retrieveBudgetItems(String startDt, String endDt, String category){

		String budgetUrl = String.format(URL_TEMPLATE, startDt, endDt);
		if(category != null){
			budgetUrl += "&category=" + category;
		}
		BudgetItem[] budgetItems = restTemplate.getForObject(budgetUrl, BudgetItem[].class);

		return Arrays.asList(budgetItems);
	}
	
}
