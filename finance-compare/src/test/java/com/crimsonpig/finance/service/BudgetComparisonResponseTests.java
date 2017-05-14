package com.crimsonpig.finance.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.crimsonpig.finance.domain.CompareRecord;
import com.crimsonpig.finance.domain.ComparisonResponse;
import com.crimsonpig.finance.domain.SummaryResponse;
import com.crimsonpig.finance.mockData.DomainObjectDataLists;

public class BudgetComparisonResponseTests {

	private SummaryResponse budgetItemsSummary;
	private SummaryResponse transactionSummary;
	
	@Before
	public void setUp(){
		DomainObjectDataLists lists = new DomainObjectDataLists();
		budgetItemsSummary = lists.getBudgetSummary();
		transactionSummary = lists.getTransactionSummary();
	}
	
	@Test
	public void testTotals(){
		ComparisonResponse response = new BudgetComparisonSummaryService()
				.compareBudgetWithActual(budgetItemsSummary, transactionSummary);
		
		assertEquals(750, response.getExpectedExpenseTotal().doubleValue(), 0.001);
		assertEquals(4100, response.getExpectedIncomeTotal().doubleValue(), 0.001);
		assertEquals(661.69, response.getActualExpenseTotal().doubleValue(), 0.001);
		assertEquals(2050, response.getActualIncomeTotal().doubleValue(), 0.001);
		assertEquals(-2050, response.getNetIncomeDifference().doubleValue(), 0.001);
		assertEquals(88.31, response.getNetExpenseDifference().doubleValue(), 0.001);
		assertEquals(-1961.69, response.getTotalNetDifference().doubleValue(), 0.001);
	}
	
	@Test
	public void testIncomeComparison(){
		
		ComparisonResponse response = new BudgetComparisonSummaryService()
				.compareBudgetWithActual(budgetItemsSummary, transactionSummary);
		
		List<CompareRecord> incomesComparison = response.getIncomes();
		assertEquals(1, incomesComparison.size());
		CompareRecord incomeComparison = incomesComparison.get(0);
		assertEquals("PAYCHECK", incomeComparison.getCategory());
		assertEquals(4100.0, incomeComparison.getExpectedAmount().doubleValue(), 0.001);
		assertEquals(2050.0, incomeComparison.getActualAmount().doubleValue(), 0.001);
		assertEquals(-2050.0, incomeComparison.getNetDifference().doubleValue(), 0.001);

	}
	
	
	@Test
	public void testExpenseComparison(){
		
		ComparisonResponse response = new BudgetComparisonSummaryService()
				.compareBudgetWithActual(budgetItemsSummary, transactionSummary);

		List<CompareRecord> expensesComparison = response.getExpenses();
		assertEquals(4, expensesComparison.size());
		Map<String,CompareRecord> mapOfExpenses = expensesComparison
				.stream()
				.collect(Collectors.toMap(item -> item.getCategory(), item -> item));
		
		CompareRecord gas = mapOfExpenses.get("GAS");
		CompareRecord food = mapOfExpenses.get("FOOD");
		CompareRecord eatingOut = mapOfExpenses.get("EATING OUT");
		CompareRecord household = mapOfExpenses.get("HOUSEHOLD");
		
		assertEquals("GAS", gas.getCategory());
		assertEquals(new BigDecimal(200), gas.getExpectedAmount());
		assertEquals(new BigDecimal(158.89), gas.getActualAmount());
		assertEquals(new BigDecimal(41.11).setScale(2, RoundingMode.HALF_EVEN), gas.getNetDifference().setScale(2, RoundingMode.HALF_EVEN));
		
		assertEquals("FOOD", food.getCategory());
		assertEquals(new BigDecimal(350), food.getExpectedAmount());
		assertEquals(new BigDecimal(0), food.getActualAmount());
		assertEquals(new BigDecimal(350), food.getNetDifference());
		
		assertEquals("EATING OUT", eatingOut.getCategory());
		assertEquals(new BigDecimal(0), eatingOut.getExpectedAmount());
		assertEquals(new BigDecimal(289.36), eatingOut.getActualAmount());
		assertEquals(new BigDecimal(-289.36), eatingOut.getNetDifference());
		
		assertEquals("HOUSEHOLD", household.getCategory());
		assertEquals(new BigDecimal(200), household.getExpectedAmount());
		assertEquals(new BigDecimal(213.44), household.getActualAmount());
		assertEquals(new BigDecimal(-13.44).setScale(2, RoundingMode.HALF_EVEN), household.getNetDifference().setScale(2, RoundingMode.HALF_EVEN));
		
	}
}
