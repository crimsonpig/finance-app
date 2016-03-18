package com.crimsonpig.finance.api;

import java.math.BigDecimal;
import java.util.List;

public class BudgetComparisonResponse {

	private List<BudgetCompareRecord> incomes;
	
	private List<BudgetCompareRecord> expenses;

	private BigDecimal expectedIncomeTotal;
	
	private BigDecimal expectedExpenseTotal;
	
	private BigDecimal actualIncomeTotal;
	
	private BigDecimal actualExpenseTotal;
	
	private BigDecimal netIncomeDifference;
	
	private BigDecimal netExpenseDifference;
	
	private BigDecimal totalNetDifference;

	public BudgetComparisonResponse() {
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

	public BigDecimal getExpectedIncomeTotal() {
		return expectedIncomeTotal;
	}

	public void setExpectedIncomeTotal(BigDecimal expectedIncomeTotal) {
		this.expectedIncomeTotal = expectedIncomeTotal;
	}

	public BigDecimal getExpectedExpenseTotal() {
		return expectedExpenseTotal;
	}

	public void setExpectedExpenseTotal(BigDecimal expectedExpenseTotal) {
		this.expectedExpenseTotal = expectedExpenseTotal;
	}

	public BigDecimal getActualIncomeTotal() {
		return actualIncomeTotal;
	}

	public void setActualIncomeTotal(BigDecimal actualIncomeTotal) {
		this.actualIncomeTotal = actualIncomeTotal;
	}

	public BigDecimal getActualExpenseTotal() {
		return actualExpenseTotal;
	}

	public void setActualExpenseTotal(BigDecimal actualExpenseTotal) {
		this.actualExpenseTotal = actualExpenseTotal;
	}

	public BigDecimal getNetIncomeDifference() {
		return netIncomeDifference;
	}

	public void setNetIncomeDifference(BigDecimal netIncomeDifference) {
		this.netIncomeDifference = netIncomeDifference;
	}

	public BigDecimal getNetExpenseDifference() {
		return netExpenseDifference;
	}

	public void setNetExpenseDifference(BigDecimal netExpenseDifference) {
		this.netExpenseDifference = netExpenseDifference;
	}

	public BigDecimal getTotalNetDifference() {
		return totalNetDifference;
	}

	public void setTotalNetDifference(BigDecimal totalNetDifference) {
		this.totalNetDifference = totalNetDifference;
	}
	
	
	
	
}
