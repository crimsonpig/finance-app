package com.crimsonpig.finance.app;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonpig.finance.domain.BudgetItem;
import com.crimsonpig.finance.domain.ComparisonResponse;
import com.crimsonpig.finance.domain.SummaryResponse;
import com.crimsonpig.finance.service.BudgetComparisonSummaryService;
import com.crimsonpig.finance.service.RetrieveActualItemsService;
import com.crimsonpig.finance.service.RetrieveBudgetItemsService;

@RestController
public class BudgetComparisonRestController {


	private RetrieveBudgetItemsService retrieveBudgetItems;
	
	private RetrieveActualItemsService retrieveActualItems;
	
	private BudgetComparisonSummaryService comparisonService;
	
	public BudgetComparisonRestController(){
		retrieveBudgetItems = new RetrieveBudgetItemsService();
		retrieveActualItems = new RetrieveActualItemsService();
		comparisonService = new BudgetComparisonSummaryService();
	}
	
	@RequestMapping(path = "/budget/comparison", method = GET)
	public ComparisonResponse comparison(
			@RequestParam(name = "startDt", required = true) String startDt, 
			@RequestParam(name = "endDt", required = true) String endDt, 
			@RequestParam(name = "category", required = false) String category){

		List<BudgetItem> budgeted = retrieveBudgetItems.retrieveBudgetItems(startDt, endDt, category);
		SummaryResponse summary = retrieveActualItems.retrieveTransactionSummary(startDt, endDt, category);

		ComparisonResponse response = comparisonService.compareBudgetWithActual(budgeted, summary);
		
		return response;
	}
	
}
