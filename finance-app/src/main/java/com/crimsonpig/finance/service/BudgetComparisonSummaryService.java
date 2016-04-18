package com.crimsonpig.finance.service;

import java.util.List;

import com.crimsonpig.finance.domain.BudgetItem;
import com.crimsonpig.finance.domain.CategorizedAmount;
import com.crimsonpig.finance.domain.CompareRecord;
import com.crimsonpig.finance.domain.ComparisonResponse;
import com.crimsonpig.finance.domain.SummaryResponse;

public class BudgetComparisonSummaryService {

	private BudgetSummaryService budgetSummarizer;
	private BudgetCompareService budgetCompare;
	
	public BudgetComparisonSummaryService(){
		budgetSummarizer = new BudgetSummaryService();
		budgetCompare = new BudgetCompareService();
	}
	
	public ComparisonResponse compareBudgetWithActual(List<BudgetItem> budgetItems,
			SummaryResponse transactionSummary) {
		SummaryResponse budgetAmounts = getBudgetAmounts(budgetItems);
		List<CompareRecord> expenseComparisons = budgetCompare.compareExpectedExpensesWithActual(budgetAmounts.getExpenses(), transactionSummary.getExpenses());
		List<CompareRecord> incomeComparisons = budgetCompare.compareExpectedIncomesWithActual(budgetAmounts.getIncomes(), transactionSummary.getIncomes());
		return new ComparisonResponse(incomeComparisons, expenseComparisons);
	}
	
	private SummaryResponse getBudgetAmounts(List<BudgetItem> budgetItems){
		return budgetSummarizer.buildBudgetSummary(budgetItems);
	}

}
