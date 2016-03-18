package com.crimsonpig.finance.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.crimsonpig.finance.mockData.DomainObjectDataLists;
import com.crimsonpig.finance.summary.CategorizedAmount;
import com.crimsonpig.finance.summary.SummaryResponse;
import com.crimsonpig.finance.transaction.Transaction;

public class TransactionSummaryServiceTests {

	private List<Transaction> transactions;
	
	@Before
	public void setUp(){
		transactions = new DomainObjectDataLists().getTransactionItems();
	}
	
	@Test
	public void testSummarizeIncomes(){
		SummaryResponse response = new TransactionSummaryService().buildTransactionSummary(transactions);
		
		assertEquals(1, response.getIncomes().size());
		CategorizedAmount incomeItem = response.getIncomes().get(0);
		assertEquals("PAYCHECK", incomeItem.getCategory());
		assertEquals(new BigDecimal(4100), incomeItem.getAmount());
		
	}
	
	@Test
	public void testSummarizeExpenses(){
		SummaryResponse response = new TransactionSummaryService().buildTransactionSummary(transactions);
		
		assertEquals(3, response.getExpenses().size());
		Map<String,CategorizedAmount> mapOfExpenses = response
				.getExpenses()
				.stream()
				.collect(Collectors.toMap(item -> item.getCategory(), item -> item));
		
		CategorizedAmount gas = mapOfExpenses.get("GAS");
		assertEquals("GAS", gas.getCategory());
		assertEquals(new BigDecimal(80), gas.getAmount());
		
		CategorizedAmount household = mapOfExpenses.get("HOUSEHOLD");
		assertEquals("HOUSEHOLD", household.getCategory());
		assertEquals(new BigDecimal(80), household.getAmount());
		
		CategorizedAmount food = mapOfExpenses.get("EATING OUT");
		assertEquals("EATING OUT", food.getCategory());
		assertEquals(new BigDecimal(80), food.getAmount());
		
	}
	
	@Test
	public void testTotals(){
		SummaryResponse response = new TransactionSummaryService().buildTransactionSummary(transactions);
		
		assertEquals(new BigDecimal(0.00), response.getExpensesTotal());
		assertEquals(new BigDecimal(0.00), response.getIncomesTotal());
		assertEquals(response.getNetTotal().setScale(2, RoundingMode.HALF_EVEN), 
				response.getIncomesTotal().subtract(response.getExpensesTotal()));
		
	}
}
