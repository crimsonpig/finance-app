package com.crimsonpig.finance.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.crimsonpig.finance.domain.BudgetItem;

public class BudgetItemAmountServiceTest {

	private BudgetItemAmountService amountService;
	
	@Before
	public void setUp(){
		amountService = new BudgetItemAmountService();
	}
	
	@Test
	public void testZeroExpenseItemAmountSameDay(){
		BudgetItem item = new BudgetItem(1L, "FOOD", BigDecimal.ZERO, "E", LocalDate.of(2017, 5, 17), LocalDate.of(2017, 5, 17));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 17), LocalDate.of(2017, 5, 17));
		assertEquals(BigDecimal.ZERO, adjusted.getAmount());
	}
	
	@Test
	public void testZeroExpenseItemAmountMoreThanOneDay(){
		BudgetItem item = new BudgetItem(1L, "FOOD", BigDecimal.ZERO, "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		assertEquals(BigDecimal.ZERO, adjusted.getAmount());
	}
	
	@Test
	public void testZeroExpenseItemAmountMoreThanOneDayButOneDay(){
		BudgetItem item = new BudgetItem(1L, "FOOD", BigDecimal.ZERO, "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 2));
		assertEquals(BigDecimal.ZERO, adjusted.getAmount());
	}

	@Test
	public void testZeroExpenseItemAmountMoreThanOneDayBut11DayDateRange(){
		BudgetItem item = new BudgetItem(1L, "FOOD", BigDecimal.ZERO, "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 10));
		assertEquals(BigDecimal.ZERO, adjusted.getAmount());
	}	
	
	@Test
	public void testZeroExpenseItemAmountMoreThanOneDayBut6DayDateRange(){
		BudgetItem item = new BudgetItem(1L, "FOOD", BigDecimal.ZERO, "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 15), LocalDate.of(2017, 5, 20));
		assertEquals(BigDecimal.ZERO, adjusted.getAmount());
	}		
	
	@Test
	public void testZeroExpenseItemAmountMoreThanOneDayBut16DayDateRange(){
		BudgetItem item = new BudgetItem(1L, "FOOD", BigDecimal.ZERO, "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 15));
		assertEquals(BigDecimal.ZERO, adjusted.getAmount());
	}			
	
	@Test
	public void testZeroExpenseItemAmountMoreThanOneDayButLargerDateRange1(){
		BudgetItem item = new BudgetItem(1L, "FOOD", BigDecimal.ZERO, "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 6, 30));
		assertEquals(BigDecimal.ZERO, adjusted.getAmount());
	}	

	@Test
	public void testZeroExpenseItemAmountMoreThanOneDayButLargerDateRange2(){
		BudgetItem item = new BudgetItem(1L, "FOOD", BigDecimal.ZERO, "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 4, 1), LocalDate.of(2017, 5, 31));
		assertEquals(BigDecimal.ZERO, adjusted.getAmount());
	}		

	@Test
	public void testZeroExpenseItemAmountMoreThanOneDayButLargestDateRange(){
		BudgetItem item = new BudgetItem(1L, "FOOD", BigDecimal.ZERO, "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 4, 1), LocalDate.of(2017, 6, 30));
		assertEquals(BigDecimal.ZERO, adjusted.getAmount());
	}
	
	@Test
	public void testNonZeroExpenseItemAmountSameDay(){
		BudgetItem item = new BudgetItem(1L, "FOOD", new BigDecimal("50.00"), "E", LocalDate.of(2017, 5, 17), LocalDate.of(2017, 5, 17));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 17), LocalDate.of(2017, 5, 17));
		assertEquals(new BigDecimal("50.00"), adjusted.getAmount());
	}
	
	@Test
	public void testNonZeroExpenseItemAmountMoreThanOneDay(){
		BudgetItem item = new BudgetItem(1L, "FOOD", new BigDecimal("500.00"), "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		assertEquals(new BigDecimal("500.00"), adjusted.getAmount());
	}
	
	@Test
	public void testNonZeroExpenseItemAmountMoreThanOneDayButOneDay(){
		BudgetItem item = new BudgetItem(1L, "FOOD", new BigDecimal(500), "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 2));
		assertEquals(new BigDecimal("31.25"), adjusted.getAmount());
	}

	@Test
	public void testNonZeroExpenseItemAmountMoreThanOneDayBut11DayDateRange(){
		BudgetItem item = new BudgetItem(1L, "FOOD", new BigDecimal(500), "E", LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31));
		BudgetItem adjusted = amountService.computeRelativeAmountByDateRange(item, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 10));
		assertEquals(new BigDecimal("171.88"), adjusted.getAmount());
	}	
		
}
