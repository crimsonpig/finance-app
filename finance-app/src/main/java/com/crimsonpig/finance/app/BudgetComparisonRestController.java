package com.crimsonpig.finance.app;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.crimsonpig.finance.api.BudgetComparisonResponse;
import com.crimsonpig.finance.api.FinancialSummary;
import com.crimsonpig.finance.api.PlannedBudgetItem;
import com.crimsonpig.finance.budget.BudgetItem;
import com.crimsonpig.finance.budget.ComparisonResponse;
import com.crimsonpig.finance.mapper.BudgetComparisonResponseMapper;
import com.crimsonpig.finance.mapper.BudgetItemMapper;
import com.crimsonpig.finance.mapper.SummaryMapper;
import com.crimsonpig.finance.service.BudgetComparisonSummaryService;
import com.crimsonpig.finance.service.RetrieveBudgetItemsService;
import com.crimsonpig.finance.summary.SummaryResponse;

@RestController
public class BudgetComparisonRestController {


	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	private RetrieveBudgetItemsService retrieveBudgetItems = new RetrieveBudgetItemsService();
	
	@RequestMapping(path = "/budget/comparison", method = GET)
	public BudgetComparisonResponse comparison(@RequestParam(name = "startDt", required=true) String startDt, @RequestParam(name = "endDt", required=true) String endDt){
		RestTemplate restTemplate = new RestTemplate();
		String host = "linuxbox";


		PlannedBudgetItem[] budgetItems = retrieveBudgetItems.retrieveBudgetItems(startDt, endDt);
		
		BudgetItemMapper mapper = new BudgetItemMapper();
		
		List<BudgetItem> budgeted = Stream.of(budgetItems).map(item -> mapper.mapFromApiObject(item)).collect(Collectors.toList());
		
		
		String summaryPath = "/reports/transactions";
		String transactionSummaryUrl = String.format("http://%s%s?startDt=%s&endDt=%s", host, summaryPath, startDt, endDt);
		FinancialSummary transactionSummary = restTemplate.getForObject(transactionSummaryUrl, FinancialSummary.class);
		SummaryResponse summary = new SummaryMapper().mapFromApiObject(transactionSummary);
		
		BudgetComparisonSummaryService service = new BudgetComparisonSummaryService();
		ComparisonResponse response = service.compareBudgetWithActual(budgeted, summary);
		
		BudgetComparisonResponseMapper responseMapper = new BudgetComparisonResponseMapper();
		return responseMapper.mapToApiClass(response);
	}
	
}
