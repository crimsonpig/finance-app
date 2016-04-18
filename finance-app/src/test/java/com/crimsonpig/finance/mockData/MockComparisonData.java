package com.crimsonpig.finance.mockData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.crimsonpig.finance.domain.CompareRecord;
import com.crimsonpig.finance.domain.ComparisonResponse;

public class MockComparisonData {

	public List<CompareRecord> getIncomeComparisons() {
		List<CompareRecord> incomes = new ArrayList<CompareRecord>(1);
		incomes.add(new CompareRecord("PAYCHECK", new BigDecimal(4100), new BigDecimal(4100), BigDecimal.ZERO));
		return incomes;
	}

	public List<CompareRecord> getExpenseComparisons() {
		List<CompareRecord> expenses = new ArrayList<CompareRecord>(1);
		expenses.add(new CompareRecord("GAS", new BigDecimal(200), new BigDecimal(187.98), new BigDecimal(12.02)));
		expenses.add(new CompareRecord("HOUSEHOLD", new BigDecimal(200), new BigDecimal(107.25), new BigDecimal(92.75)));
		expenses.add(new CompareRecord("FOOD", new BigDecimal(350), new BigDecimal(358.99), new BigDecimal(-8.99)));
		return expenses;
	}

	public ComparisonResponse getComparisonResponse() {
		List<CompareRecord> incomeComparisons = getIncomeComparisons();
		List<CompareRecord> expenseComparisons = getExpenseComparisons();
		return new ComparisonResponse(incomeComparisons, expenseComparisons);
	}

}
