package com.crimsonpig.finance.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.domain.SummaryResponse;

@Component
public class RetrieveBudgetItemsService {

	@Value("${finance.comparison.budgetitems.path}")
	private String path;
	
	private RestTemplate restTemplate;

	public RetrieveBudgetItemsService(){
		restTemplate = new RestTemplate();
	}
	
	public SummaryResponse retrieveBudgetItems(String startDt, String endDt, String category){
		String urlTemplate = path + "?startDt=%s&endDt=%s";
		String budgetSummaryUrl = String.format(urlTemplate, startDt, endDt);
		if(category != null){
			budgetSummaryUrl += "&category=" + category;
		}
		SummaryResponse budgetSummary = restTemplate.getForObject(budgetSummaryUrl, SummaryResponse.class);

		return budgetSummary;
	}
	
}
