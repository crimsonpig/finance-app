package com.crimsonpig.finance.domain;

import java.math.BigDecimal;
import java.util.List;

public class ComparisonResponse {

	private final List<CompareRecord> incomes;
	
	private final List<CompareRecord> expenses;

	public ComparisonResponse(List<CompareRecord> incomes, List<CompareRecord> expenses){
		this.incomes = incomes;
		this.expenses = expenses;
	}

	public List<CompareRecord> getIncomes() {
		return incomes;
	}

	public List<CompareRecord> getExpenses() {
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

	private BigDecimal sumExpectedTotals(List<CompareRecord> comparisons) {
		return comparisons
				.stream()
				.map(record -> record.getExpectedAmount())
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	private BigDecimal sumActualTotals(List<CompareRecord> comparisons) {
		return comparisons
				.stream()
				.map(record -> record.getActualAmount())
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	private BigDecimal sumNetDifferences(List<CompareRecord> comparisons) {
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
