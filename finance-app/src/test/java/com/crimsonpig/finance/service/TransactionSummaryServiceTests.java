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
		assertEquals(new BigDecimal(105.21).doubleValue(), gas.getAmount().doubleValue(), 0.001);
		
		CategorizedAmount household = mapOfExpenses.get("HOUSEHOLD");
		assertEquals("HOUSEHOLD", household.getCategory());
		assertEquals(new BigDecimal(122.35).doubleValue(), household.getAmount().doubleValue(), 0.001);
		
		CategorizedAmount food = mapOfExpenses.get("EATING OUT");
		assertEquals("EATING OUT", food.getCategory());
		assertEquals(new BigDecimal(140.77).doubleValue(), food.getAmount().doubleValue(), 0.001);
		
	}
	
	@Test
	public void testTotals(){
		SummaryResponse response = new TransactionSummaryService().buildTransactionSummary(transactions);
		
		assertEquals(new BigDecimal(368.33).doubleValue(), response.getExpensesTotal().doubleValue(), 0.001);
		assertEquals(new BigDecimal(4100), response.getIncomesTotal());
		assertEquals(new BigDecimal(3731.67).doubleValue(), response.getNetTotal().doubleValue(), 0.001);
		
	}
}
