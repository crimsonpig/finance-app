package com.crimsonpig.finance.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.crimsonpig.finance.budget.BudgetItem;
import com.crimsonpig.finance.budget.CompareRecord;
import com.crimsonpig.finance.mockData.DomainObjectDataLists;
import com.crimsonpig.finance.summary.CategorizedAmount;

public class BudgetCompareServiceTests {

	private List<CategorizedAmount> expenseItems;
	private List<CategorizedAmount> incomeItems;
	private List<CategorizedAmount> actualExpenses;
	private List<CategorizedAmount> actualIncomes;
	
	@Before
	public void setUp(){
		DomainObjectDataLists lists = new DomainObjectDataLists();

		expenseItems = lists.getExpenseItems(); 
		incomeItems = lists.getIncomeItems();
		actualExpenses = lists.getExpenseSummary();
		actualIncomes = lists.getIncomesSummary();
	}
	
	@Test
	public void compareExpensesWithActual(){
		
		List<CompareRecord> usageRecords = new BudgetCompareService().compareExpectedWithActual(expenseItems, actualExpenses);
		
		assertEquals(4, usageRecords.size());
		Map<String,CompareRecord> mapOfRecords = usageRecords
				.stream()
				.collect(Collectors.toMap(item -> item.getCategory(), item -> item));
		
		CompareRecord gas = mapOfRecords.get("GAS");
		CompareRecord food = mapOfRecords.get("FOOD");
		CompareRecord eatingOut = mapOfRecords.get("EATING OUT");
		CompareRecord household = mapOfRecords.get("HOUSEHOLD");
		
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
	
	@Test
	public void testCompareIncomesWithActual(){
		List<CompareRecord> usageRecords = new BudgetCompareService().compareExpectedWithActual(incomeItems, actualIncomes);
		
		assertEquals(1, usageRecords.size());
		CompareRecord record = usageRecords.get(0);
		assertEquals("PAYCHECK", record.getCategory());
		assertEquals(new BigDecimal(4100), record.getExpectedAmount());
		assertEquals(new BigDecimal(4100), record.getActualAmount());
		assertEquals(new BigDecimal(0), record.getNetDifference());
		
		
	}
	
}
