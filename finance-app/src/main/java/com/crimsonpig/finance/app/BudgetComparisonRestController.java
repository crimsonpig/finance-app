package com.crimsonpig.finance.app;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonpig.finance.api.BudgetComparisonResponse;
import com.crimsonpig.finance.budget.BudgetItem;
import com.crimsonpig.finance.budget.ComparisonResponse;
import com.crimsonpig.finance.mapper.BudgetComparisonResponseMapper;
import com.crimsonpig.finance.service.BudgetComparisonSummaryService;
import com.crimsonpig.finance.service.RetrieveActualItemsService;
import com.crimsonpig.finance.service.RetrieveBudgetItemsService;
import com.crimsonpig.finance.summary.SummaryResponse;

@RestController
public class BudgetComparisonRestController {


	private RetrieveBudgetItemsService retrieveBudgetItems;
	
	private RetrieveActualItemsService retrieveActualItems;
	
	private BudgetComparisonSummaryService comparisonService;
	
	private BudgetComparisonResponseMapper comparisonResponseMapper;
	
	
	public BudgetComparisonRestController(){
		retrieveBudgetItems = new RetrieveBudgetItemsService();
		retrieveActualItems = new RetrieveActualItemsService();
		comparisonService = new BudgetComparisonSummaryService();
		comparisonResponseMapper = new BudgetComparisonResponseMapper();
	}
	
	@RequestMapping(path = "/budget/comparison", method = GET)
	public BudgetComparisonResponse comparison(
			@RequestParam(name = "startDt", required = true) String startDt, 
			@RequestParam(name = "endDt", required = true) String endDt){

		List<BudgetItem> budgeted = retrieveBudgetItems.retrieveBudgetItems(startDt, endDt);
		SummaryResponse summary = retrieveActualItems.retrieveTransactionSummary(startDt, endDt);

		ComparisonResponse response = comparisonService.compareBudgetWithActual(budgeted, summary);
		
		return comparisonResponseMapper.mapToApiClass(response);
	}
	
}
