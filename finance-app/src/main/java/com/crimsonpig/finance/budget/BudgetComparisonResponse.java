package com.crimsonpig.finance.budget;

import java.math.BigDecimal;
import java.util.List;

public class BudgetComparisonResponse {

	private final List<BudgetCompareRecord> incomes;
	
	private final List<BudgetCompareRecord> expenses;

	public BudgetComparisonResponse(List<BudgetCompareRecord> incomes, List<BudgetCompareRecord> expenses){
		this.incomes = incomes;
		this.expenses = expenses;
	}

	public List<BudgetCompareRecord> getIncomes() {
		return incomes;
	}

	public List<BudgetCompareRecord> getExpenses() {
		return expenses;
	}
	
	public BigDecimal getExpectedIncomeTotal(){
		return sumExpectedTotals(incomes);
	}
	
	public BigDecimal getExpectedExpenseTotal(){
		return sumExpectedTotals(expenses);
	}

	public BigDecimal getActualIncomeTotal(){
		return sumActualTotals(incomes);
	}
	
	public BigDecimal getActualExpenseTotal(){
		return sumActualTotals(expenses);
	}
	
	public BigDecimal getNetIncomeDifference(){
		return sumNetDifferences(incomes);
	}
	
	public BigDecimal getNetExpenseDifference(){
		return sumNetDifferences(expenses);
	}

	private BigDecimal sumExpectedTotals(List<BudgetCompareRecord> comparisons) {
		return comparisons
				.stream()
				.map(record -> record.getExpectedAmount())
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	private BigDecimal sumActualTotals(List<BudgetCompareRecord> comparisons) {
		return comparisons
				.stream()
				.map(record -> record.getActualAmount())
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	private BigDecimal sumNetDifferences(List<BudgetCompareRecord> comparisons) {
		return comparisons
				.stream()
				.map(record -> record.getNetDifference())
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	public BigDecimal getTotalNetDifference() {
		return getNetIncomeDifference().add(getNetExpenseDifference());
	}

}
