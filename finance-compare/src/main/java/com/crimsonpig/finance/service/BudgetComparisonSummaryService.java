package com.crimsonpig.finance.service;

import java.util.List;

import com.crimsonpig.finance.domain.CompareRecord;
import com.crimsonpig.finance.domain.ComparisonResponse;
import com.crimsonpig.finance.domain.SummaryResponse;

public class BudgetComparisonSummaryService {

	private BudgetCompareService budgetCompare;
	
	public BudgetComparisonSummaryService(){
		budgetCompare = new BudgetCompareService();
	}
	
	public ComparisonResponse compareBudgetWithActual(SummaryResponse budgetSummary,
			SummaryResponse transactionSummary) {
		List<CompareRecord> expenseComparisons = budgetCompare.compareExpectedExpensesWithActual(budgetSummary.getExpenses(), transactionSummary.getExpenses());
		List<CompareRecord> incomeComparisons = budgetCompare.compareExpectedIncomesWithActual(budgetSummary.getIncomes(), transactionSummary.getIncomes());
		return new ComparisonResponse(incomeComparisons, expenseComparisons);
	}

}
