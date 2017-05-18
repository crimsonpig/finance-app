package com.crimsonpig.finance.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class BudgetItemNumberOfDaysTest {


	private BudgetItemAmountService subject;
	
	@Before
	public void setUp(){
		subject = new BudgetItemAmountService();
	}

	@Test
	public void testOneDayBudgetItemDays(){
		LocalDate startDate = LocalDate.of(2017, 5, 17);
		LocalDate endDate = LocalDate.of(2017, 5, 17);
		int expectedDays = 1;
		int budgetItemDays = subject.calculateBudgetDays(startDate, endDate);
		assertEquals(expectedDays, budgetItemDays);
	}
	
	@Test
	public void testOneWeekBudgetItemDays(){
		LocalDate startDate = LocalDate.of(2017, 5, 1);
		LocalDate endDate = LocalDate.of(2017, 5, 7);
		//7 days because we include the first day
		int expectedDays = 7;
		int budgetItemDays = subject.calculateBudgetDays(startDate, endDate);
		assertEquals(expectedDays, budgetItemDays);
	}	
	
	@Test
	public void testHalfMonthBudgetItemDays(){
		LocalDate startDate = LocalDate.of(2017, 5, 1);
		LocalDate endDate = LocalDate.of(2017, 5, 15);
		//15 days because we include the first day
		int expectedDays = 15;
		int budgetItemDays = subject.calculateBudgetDays(startDate, endDate);
		assertEquals(expectedDays, budgetItemDays);
	}
	
	@Test
	public void testOneMonthBudgetItemDays(){
		LocalDate startDate = LocalDate.of(2017, 5, 1);
		LocalDate endDate = LocalDate.of(2017, 5, 31);
		//31 days because we include the first day
		int expectedDays = 31;
		int budgetItemDays = subject.calculateBudgetDays(startDate, endDate);
		assertEquals(expectedDays, budgetItemDays);
	}
	
	@Test
	public void testTwoMonthBudgetItemDays(){
		LocalDate startDate = LocalDate.of(2017, 5, 1);
		LocalDate endDate = LocalDate.of(2017, 6, 30);
		//61 days because we include the first day
		int expectedDays = 61;
		int budgetItemDays = subject.calculateBudgetDays(startDate, endDate);
		assertEquals(expectedDays, budgetItemDays);
	}
	
	@Test
	public void testTwoYearLeapYearBudgetItemDays(){
		LocalDate startDate = LocalDate.of(2016, 1, 1);
		LocalDate endDate = LocalDate.of(2017, 12, 31);
		int expectedDays = 731;
		int budgetItemDays = subject.calculateBudgetDays(startDate, endDate);
		assertEquals(expectedDays, budgetItemDays);
	}
	
	@Test
	public void testTwoYearNonLeapYearBudgetItemDays(){
		LocalDate startDate = LocalDate.of(2017, 1, 1);
		LocalDate endDate = LocalDate.of(2018, 12, 31);
		int expectedDays = 730;
		int budgetItemDays = subject.calculateBudgetDays(startDate, endDate);
		assertEquals(expectedDays, budgetItemDays);
	}
	
}
