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
import com.crimsonpig.finance.budget.BudgetUsageRecord;
import com.crimsonpig.finance.mockData.DomainObjectDataLists;
import com.crimsonpig.finance.summary.CategorizedAmount;

public class BudgetUsgeServiceTests {

	private List<BudgetItem> expenseItems;
	private List<BudgetItem> incomeItems;
	private List<CategorizedAmount> actualExpenses;
	private List<CategorizedAmount> actualIncomes;
	
	@Before
	public void setUp(){
		DomainObjectDataLists lists = new DomainObjectDataLists();
		List<BudgetItem> budgetItems = lists.getBudgetItems();
		
		expenseItems = budgetItems.stream().filter(item -> item.getItemType().equals("E")).collect(Collectors.toList());
		incomeItems = budgetItems.stream().filter(item -> item.getItemType().equals("I")).collect(Collectors.toList());
		actualExpenses = lists.getExpenseSummary();
		actualIncomes = lists.getIncomesSummary();
	}
	
	@Test
	public void compareExpensesWithActual(){
		
		List<BudgetUsageRecord> usageRecords = new BudgetUsageService().compareExpectedWithActual(expenseItems, actualExpenses);
		
		assertEquals(4, usageRecords.size());
		Map<String,BudgetUsageRecord> mapOfRecords = usageRecords
				.stream()
				.collect(Collectors.toMap(item -> item.getCategory(), item -> item));
		
		BudgetUsageRecord gas = mapOfRecords.get("GAS");
		BudgetUsageRecord food = mapOfRecords.get("FOOD");
		BudgetUsageRecord eatingOut = mapOfRecords.get("EATING OUT");
		BudgetUsageRecord household = mapOfRecords.get("HOUSEHOLD");
		
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
		List<BudgetUsageRecord> usageRecords = new BudgetUsageService().compareExpectedWithActual(incomeItems, actualIncomes);
		
		assertEquals(1, usageRecords.size());
		BudgetUsageRecord record = usageRecords.get(0);
		assertEquals("PAYCHECK", record.getCategory());
		assertEquals(new BigDecimal(4100), record.getExpectedAmount());
		assertEquals(new BigDecimal(4100), record.getActualAmount());
		assertEquals(0, record.getNetDifference());
		
		
	}
	
}
