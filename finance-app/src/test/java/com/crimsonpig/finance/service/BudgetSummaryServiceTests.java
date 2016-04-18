package com.crimsonpig.finance.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.crimsonpig.finance.domain.BudgetItem;
import com.crimsonpig.finance.domain.CategorizedAmount;
import com.crimsonpig.finance.domain.SummaryResponse;
import com.crimsonpig.finance.mockData.DomainObjectDataLists;

public class BudgetSummaryServiceTests {

	private List<BudgetItem> budgetItems;
	
	@Before
	public void setUp(){
		budgetItems = new DomainObjectDataLists().getBudgetItems();
	}
	
	@Test
	public void testConvertIncomeItemsToSummary(){
		SummaryResponse response = new BudgetSummaryService().buildBudgetSummary(budgetItems);
		assertEquals(1, response.getIncomes().size());
		CategorizedAmount incomeItem = response.getIncomes().get(0);
		assertEquals("PAYCHECK", incomeItem.getCategory());
		assertEquals(new BigDecimal(4100), incomeItem.getAmount());
	}
	
	@Test
	public void testTotals(){
		SummaryResponse response = new BudgetSummaryService().buildBudgetSummary(budgetItems);
		assertEquals(new BigDecimal(750), response.getExpensesTotal());
		assertEquals(new BigDecimal(4100), response.getIncomesTotal());
		assertEquals(new BigDecimal(3350), response.getNetTotal());
	}
	
	@Test
	public void testConvertExpenseItemsToSummary(){
		SummaryResponse response = new BudgetSummaryService().buildBudgetSummary(budgetItems);
		assertEquals(3, response.getExpenses().size());

		Map<String, CategorizedAmount> expensesMap = response
				.getExpenses()
				.stream()
				.collect(Collectors.toMap(item -> item.getCategory(), item -> item));

		CategorizedAmount gas = expensesMap.get("GAS");
		assertEquals("GAS", gas.getCategory());
		assertEquals(new BigDecimal(200), gas.getAmount());
		
		CategorizedAmount household = expensesMap.get("HOUSEHOLD");
		assertEquals("HOUSEHOLD", household.getCategory());
		assertEquals(new BigDecimal(200), household.getAmount());
		
		CategorizedAmount food = expensesMap.get("FOOD");
		assertEquals("FOOD", food.getCategory());
		assertEquals(new BigDecimal(350), food.getAmount());
		
	}
	
}
