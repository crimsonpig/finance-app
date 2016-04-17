package com.crimsonpig.finance.service;

import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.summary.SummaryResponse;

public class RetrieveActualItemsService {

	private static final String HOST = "linuxbox";
	
	private static final String PATH = HOST + "/reports/transactions";
	
	private static final String URL_TEMPLATE = "http://" + PATH + "?startDt=%s&endDt=%s";
	
	private RestTemplate restTemplate;

	public RetrieveActualItemsService(){
		restTemplate = new RestTemplate();
	}
	
	public SummaryResponse retrieveTransactionSummary(String startDt, String endDt){
		String transactionSummaryUrl = String.format(URL_TEMPLATE, startDt, endDt);
		SummaryResponse transactionSummary = restTemplate.getForObject(transactionSummaryUrl, SummaryResponse.class);
		return transactionSummary;
	}
	
}
