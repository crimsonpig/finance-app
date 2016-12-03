package com.crimsonpig.finance.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.domain.SummaryResponse;

@Component
public class RetrieveActualItemsService {

	@Value("${finance.comparison.actualitems.path}")
	private String path;
	
	private RestTemplate restTemplate;

	public RetrieveActualItemsService(){
		restTemplate = new RestTemplate();
	}
	
	public SummaryResponse retrieveTransactionSummary(String startDt, String endDt, String category){
		String urlTemplate = path + "?startDt=%s&endDt=%s";
		String transactionSummaryUrl = String.format(urlTemplate, startDt, endDt);
		if(category != null){
			transactionSummaryUrl += "&category=" + category;
		}
		SummaryResponse transactionSummary = restTemplate.getForObject(transactionSummaryUrl, SummaryResponse.class);
		return transactionSummary;
	}
	
}
