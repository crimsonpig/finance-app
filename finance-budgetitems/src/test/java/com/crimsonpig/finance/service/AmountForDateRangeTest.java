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
		BigDecimal expectedAmount = new BigDecimal(500);
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}
	
	@Test
	public void test31DayDifferenceAmount31DayRange(){
		int budgetItemNumberOfDays = 31;
		int dateRangeNumberOfDays = 31; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal(500);
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}
	
	@Test
	public void test31DayDifferenceAmount61DayRange(){
		int budgetItemNumberOfDays = 31;
		int dateRangeNumberOfDays = 61; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal(500);
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}
	
	@Test
	public void test61DayDifferenceAmount365DayRange(){
		int budgetItemNumberOfDays = 61;
		int dateRangeNumberOfDays = 365; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal(500);
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}
	
	@Test
	public void test31DayDifferenceAmount15DayRange(){
		int budgetItemNumberOfDays = 31;
		int dateRangeNumberOfDays = 15; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal("241.94");
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}	
	
	@Test
	public void test31DayDifferenceAmount11DayRange(){
		int budgetItemNumberOfDays = 31;
		int dateRangeNumberOfDays = 11; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal("177.42");
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}
	
	@Test
	public void test31DayDifferenceAmount6DayRange(){
		int budgetItemNumberOfDays = 31;
		int dateRangeNumberOfDays = 6; 
		BigDecimal budgetItemAmount = new BigDecimal(500);
		BigDecimal expectedAmount = new BigDecimal("96.77");
		BigDecimal actualAmount = subject.calculateAmountForDateRange(budgetItemNumberOfDays, dateRangeNumberOfDays, budgetItemAmount);
		assertEquals(expectedAmount, actualAmount);
	}		
	
}
