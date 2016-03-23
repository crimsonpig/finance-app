package com.crimsonpig.finance.service;

import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.api.FinancialSummary;
import com.crimsonpig.finance.mapper.SummaryMapper;
import com.crimsonpig.finance.summary.SummaryResponse;

public class RetrieveActualItemsService {

	private static final String HOST = "linuxbox";
	
	private static final String PATH = HOST + "/reports/transactions";
	
	private static final String URL_TEMPLATE = "http://" + PATH + "?startDt=%s&endDt=%s";
	
	private RestTemplate restTemplate;
	
	private SummaryMapper summaryMapper;
	
	public RetrieveActualItemsService(){
		restTemplate = new RestTemplate();
		summaryMapper = new SummaryMapper();
	}
	
	public SummaryResponse retrieveTransactionSummary(String startDt, String endDt){
		String transactionSummaryUrl = String.format(URL_TEMPLATE, startDt, endDt);
		FinancialSummary transactionSummary = restTemplate.getForObject(transactionSummaryUrl, FinancialSummary.class);
		return summaryMapper.mapFromApiObject(transactionSummary);
	}
	
}
