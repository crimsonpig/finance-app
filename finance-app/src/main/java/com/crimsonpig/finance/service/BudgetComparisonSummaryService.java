package com.crimsonpig.finance.service;

import java.util.List;

import com.crimsonpig.finance.budget.BudgetCompareRecord;
import com.crimsonpig.finance.budget.BudgetComparisonResponse;
import com.crimsonpig.finance.budget.BudgetItem;
import com.crimsonpig.finance.summary.CategorizedAmount;
import com.crimsonpig.finance.summary.SummaryResponse;

public class BudgetComparisonSummaryService {

	private BudgetSummaryService budgetSummarizer;
	private BudgetCompareService budgetCompare;
	
	public BudgetComparisonSummaryService(){
		budgetSummarizer = new BudgetSummaryService();
		budgetCompare = new BudgetCompareService();
	}
	
	public BudgetComparisonResponse compareBudgetWithActual(List<BudgetItem> budgetItems,
			SummaryResponse transactionSummary) {
		BudgetComparisonResponse response = new BudgetComparisonResponse();
		SummaryResponse budgetAmounts = getBudgetAmounts(budgetItems);
		List<BudgetCompareRecord> expenseComparisons = calculateBudgetComparison(budgetAmounts.getExpenses(), transactionSummary.getExpenses());
		List<BudgetCompareRecord> incomeComparisons = calculateBudgetComparison(budgetAmounts.getIncomes(), transactionSummary.getIncomes());
		response.setExpenses(expenseComparisons);
		response.setIncomes(incomeComparisons);
		return response;
	}
	
	private SummaryResponse getBudgetAmounts(List<BudgetItem> budgetItems){
		return budgetSummarizer.buildBudgetSummary(budgetItems);
	}
	
	private List<BudgetCompareRecord> 
		calculateBudgetComparison(List<CategorizedAmount> planned, List<CategorizedAmount> actual){
		return budgetCompare.compareExpectedWithActual(planned, actual);
	}

}
