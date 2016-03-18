package com.crimsonpig.finance.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.crimsonpig.finance.budget.CompareRecord;
import com.crimsonpig.finance.budget.ComparisonResponse;
import com.crimsonpig.finance.api.BudgetCompareRecord;
import com.crimsonpig.finance.api.BudgetComparisonResponse;
import com.crimsonpig.finance.mockData.MockComparisonData;

public class BudgetComparisonResponseMapperTest {

	private ComparisonResponse comparison;
	
	@Before
	public void setUp(){
		comparison = new MockComparisonData().getComparisonResponse();
	}
	
	@Test
	public void testMapComparisonToPublicClass(){
		
		BudgetComparisonResponseMapper mapper = new BudgetComparisonResponseMapper();
		BudgetComparisonResponse response = mapper.mapToApiClass(comparison);

		assertEquals(comparison.getExpenses().size(), response.getExpenses().size());
		Map<String, CompareRecord> comparisonExpenseRecords = comparison.getExpenses().stream().collect(Collectors.toMap(item -> item.getCategory(), item -> item));
		Map<String, BudgetCompareRecord> expenseRecords = response.getExpenses().stream().collect(Collectors.toMap(item -> item.getCategory(), item -> item));
		
		assertEquals(comparisonExpenseRecords.get("FOOD").getCategory(), expenseRecords.get("FOOD").getCategory());
		assertEquals(comparisonExpenseRecords.get("FOOD").getActualAmount(), expenseRecords.get("FOOD").getActualAmount());
		assertEquals(comparisonExpenseRecords.get("FOOD").getExpectedAmount(), expenseRecords.get("FOOD").getExpectedAmount());
		assertEquals(comparisonExpenseRecords.get("FOOD").getNetDifference(), expenseRecords.get("FOOD").getNetDifference());
		
		assertEquals(comparisonExpenseRecords.get("HOUSEHOLD").getCategory(), expenseRecords.get("HOUSEHOLD").getCategory());
		assertEquals(comparisonExpenseRecords.get("HOUSEHOLD").getActualAmount(), expenseRecords.get("HOUSEHOLD").getActualAmount());
		assertEquals(comparisonExpenseRecords.get("HOUSEHOLD").getExpectedAmount(), expenseRecords.get("HOUSEHOLD").getExpectedAmount());
		assertEquals(comparisonExpenseRecords.get("HOUSEHOLD").getNetDifference(), expenseRecords.get("HOUSEHOLD").getNetDifference());
		
		assertEquals(comparisonExpenseRecords.get("FOOD").getCategory(), expenseRecords.get("FOOD").getCategory());
		assertEquals(comparisonExpenseRecords.get("FOOD").getActualAmount(), expenseRecords.get("FOOD").getActualAmount());
		assertEquals(comparisonExpenseRecords.get("FOOD").getExpectedAmount(), expenseRecords.get("FOOD").getExpectedAmount());
		assertEquals(comparisonExpenseRecords.get("FOOD").getNetDifference(), expenseRecords.get("FOOD").getNetDifference());
		
	}
	
	@Test
	public void incomesTest(){
		BudgetComparisonResponseMapper mapper = new BudgetComparisonResponseMapper();
		BudgetComparisonResponse response = mapper.mapToApiClass(comparison);
		
		assertEquals(comparison.getIncomes().size(), response.getIncomes().size());
		BudgetCompareRecord incomeRecord = response.getIncomes().get(0);
		CompareRecord compareIncomeRecord = comparison.getIncomes().get(0);
		assertEquals(compareIncomeRecord.getCategory(), incomeRecord.getCategory());
		assertEquals(compareIncomeRecord.getActualAmount(), incomeRecord.getActualAmount());
		assertEquals(compareIncomeRecord.getExpectedAmount(), incomeRecord.getExpectedAmount());
		assertEquals(compareIncomeRecord.getNetDifference(), incomeRecord.getNetDifference());
	}
	
	@Test
	public void totalsTest(){
		
		BudgetComparisonResponseMapper mapper = new BudgetComparisonResponseMapper();
		BudgetComparisonResponse response = mapper.mapToApiClass(comparison);
		assertEquals(comparison.getActualExpenseTotal(), response.getActualExpenseTotal());
		assertEquals(comparison.getExpectedExpenseTotal(), response.getExpectedExpenseTotal());
		assertEquals(comparison.getNetExpenseDifference(), response.getNetExpenseDifference());
		
		assertEquals(comparison.getActualIncomeTotal(), response.getActualIncomeTotal());
		assertEquals(comparison.getExpectedIncomeTotal(), response.getExpectedIncomeTotal());
		assertEquals(comparison.getNetIncomeDifference(), response.getNetIncomeDifference());
		
		assertEquals(comparison.getTotalNetDifference(), response.getTotalNetDifference());
	}
}
