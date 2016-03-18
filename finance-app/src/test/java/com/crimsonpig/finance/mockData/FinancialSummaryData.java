package com.crimsonpig.finance.mockData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.crimsonpig.finance.api.*;

public class FinancialSummaryData {

	public FinancialSummary getTestSummary() {
		FinancialSummary summary = new FinancialSummary();
		List<CategorizedAmount> expenses = new ArrayList<CategorizedAmount>();
		CategorizedAmount expense1 = new CategorizedAmount();
		expense1.setCategory("GAS");
		expense1.setAmount(new BigDecimal(115.87));
		
		CategorizedAmount expense2 = new CategorizedAmount();
		expense2.setCategory("GAS");
		expense2.setAmount(new BigDecimal(115.87));
		expenses.add(expense1);
		expenses.add(expense2);
		
		List<CategorizedAmount> incomes = new ArrayList<CategorizedAmount>();
		CategorizedAmount income = new CategorizedAmount();
		income.setCategory("PAYCHECK");
		income.setAmount(new BigDecimal(4100));
		incomes.add(income);
		
		summary.setIncomes(incomes);
		summary.setExpenses(expenses);
		summary.setExpensesTotal(new BigDecimal(200));
		summary.setIncomesTotal(new BigDecimal(200));
		summary.setNetTotal(new BigDecimal(200));
		return summary;
	}

}
