package com.crimsonpig.finance.service;

import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.api.FinancialSummary;

public class RetrieveActualItemsService {

	private static final String HOST = "linuxbox";
	
	private static final String PATH = HOST + "/reports/transactions";
	
	private RestTemplate restTemplate;
	
	public RetrieveActualItemsService(){
		restTemplate = new RestTemplate();
	}
	
	public FinancialSummary retrieveTransactionSummary(String startDt, String endDt){
		String transactionSummaryUrl = String.format("http://%s?startDt=%s&endDt=%s", PATH, startDt, endDt);
		FinancialSummary transactionSummary = restTemplate.getForObject(transactionSummaryUrl, FinancialSummary.class);
		return transactionSummary;
	}
	
}
