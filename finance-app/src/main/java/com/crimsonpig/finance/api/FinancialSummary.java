package com.crimsonpig.finance.api;

import java.math.BigDecimal;
import java.util.List;

public class FinancialSummary {

	private List<CategorizedAmount> incomes;
	
	private List<CategorizedAmount> expenses;

	public BigDecimal expensesTotal;
	
	public BigDecimal incomesTotal;
	
	public BigDecimal netTotal;
	
	public FinancialSummary(){
		
	}

	public List<CategorizedAmount> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<CategorizedAmount> expenses) {
		this.expenses = expenses;
	}

	public List<CategorizedAmount> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<CategorizedAmount> incomes) {
		this.incomes = incomes;
	}

	public BigDecimal getExpensesTotal() {
		return expensesTotal;
	}

	public void setExpensesTotal(BigDecimal expensesTotal) {
		this.expensesTotal = expensesTotal;
	}

	public BigDecimal getIncomesTotal() {
		return incomesTotal;
	}

	public void setIncomesTotal(BigDecimal incomesTotal) {
		this.incomesTotal = incomesTotal;
	}

	public BigDecimal getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(BigDecimal netTotal) {
		this.netTotal = netTotal;
	}
	
}

