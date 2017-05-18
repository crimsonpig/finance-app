package com.crimsonpig.finance.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class AmountForDateRangeTest {

	private BudgetItemAmountService subject;
	
	@Before
	public void setUp(){
		subject = new BudgetItemAmountService();
	}

	@Test
	public void testOneDayDifferenceAmount(){
		int budgetItemNumberOfDays = 1;
		int dateRangeNumberOfDays = 1; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal("500.00");
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}
	
	@Test
	public void test31DayDifferenceAmount31DayRange(){
		int budgetItemNumberOfDays = 32;
		int dateRangeNumberOfDays = 32; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal("500.00");
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}
	
	@Test
	public void test31DayDifferenceAmount15DayRange(){
		int budgetItemNumberOfDays = 32;
		int dateRangeNumberOfDays = 15; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal("234.38");
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}	
	
	@Test
	public void test31DayDifferenceAmount11DayRange(){
		int budgetItemNumberOfDays = 32;
		int dateRangeNumberOfDays = 11; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal("171.88");
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}
	
	@Test
	public void test32DayDifferenceAmount6DayRange(){
		int budgetItemNumberOfDays = 32;
		int dateRangeNumberOfDays = 6; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal("93.75");
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}		
	
}
