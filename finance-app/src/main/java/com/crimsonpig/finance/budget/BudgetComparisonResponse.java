package com.crimsonpig.finance.budget;

import java.math.BigDecimal;
import java.util.List;

public class BudgetComparisonResponse {

	private List<BudgetCompareRecord> incomes;
	
	private List<BudgetCompareRecord> expenses;
	
	public BudgetComparisonResponse(){
		
	}

	public List<BudgetCompareRecord> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<BudgetCompareRecord> incomes) {
		this.incomes = incomes;
	}

	public List<BudgetCompareRecord> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<BudgetCompareRecord> expenses) {
		this.expenses = expenses;
	}

}
