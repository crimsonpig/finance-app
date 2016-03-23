package com.crimsonpig.finance.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.api.PlannedBudgetItem;
import com.crimsonpig.finance.budget.BudgetItem;
import com.crimsonpig.finance.mapper.BudgetItemMapper;

public class RetrieveBudgetItemsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RetrieveBudgetItemsService.class);

	private static final String HOST = "linuxbox";
	
	private static final String PATH = HOST + "/budget";
	
	private static final String URL_TEMPLATE = "http://" + PATH + "?startDt=%s&endDt=%s";
	
	private RestTemplate restTemplate;
	
	private BudgetItemMapper mapper;
	
	public RetrieveBudgetItemsService(){
		restTemplate = new RestTemplate();
		mapper = new BudgetItemMapper();
	}
	
	public List<BudgetItem> retrieveBudgetItems(String startDt, String endDt){

		String budgetUrl = String.format(URL_TEMPLATE, startDt, endDt);

		PlannedBudgetItem[] budgetItems = restTemplate.getForObject(budgetUrl, PlannedBudgetItem[].class);

		return Stream.of(budgetItems)
				.map(item -> mapper.mapFromApiObject(item))
				.collect(Collectors.toList());
	}
	
}
