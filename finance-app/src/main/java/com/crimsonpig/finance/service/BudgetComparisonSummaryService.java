package com.crimsonpig.finance.service;

import java.util.List;

import com.crimsonpig.finance.budget.CompareRecord;
import com.crimsonpig.finance.budget.ComparisonResponse;
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
