package com.crimsonpig.finance.mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.crimsonpig.finance.api.FinancialSummary;
import com.crimsonpig.finance.mockData.FinancialSummaryData;
import com.crimsonpig.finance.summary.SummaryResponse;

public class SummaryMapperTest {

	private FinancialSummary summaryApi;
	
	@Before
	public void setUp(){
		summaryApi = new FinancialSummaryData().getTestSummary();
	}
	
	@Test
	public void testMapFromApiObjectTotals(){
		SummaryMapper mapper = new SummaryMapper();
		
		SummaryResponse summary = mapper.mapFromApiObject(summaryApi);
		
		assertEquals(summaryApi.getExpensesTotal(), summary.getExpensesTotal());
		assertEquals(summaryApi.getIncomesTotal(), summary.getIncomesTotal());
		assertEquals(summaryApi.getNetTotal(), summary.getNetTotal());
	}
	
	@Test
	public void testMapIncomeFromApiObjectTotals(){
		SummaryMapper mapper = new SummaryMapper();
		
		SummaryResponse summary = mapper.mapFromApiObject(summaryApi);
		assertEquals(summaryApi.getIncomes().size(), summary.getIncomes().size());
		
		assertEquals(summaryApi.getIncomes().get(0).getCategory(), summary.getIncomes().get(0).getCategory());
		assertEquals(summaryApi.getIncomes().get(0).getAmount(), summary.getIncomes().get(0).getAmount());
	}
	
	@Test
	public void testMapExpensesFromApiObjectTotals(){
		SummaryMapper mapper = new SummaryMapper();
		
		SummaryResponse summary = mapper.mapFromApiObject(summaryApi);
		
		assertEquals(summaryApi.getExpenses().size(), summary.getExpenses().size());
		
		assertEquals(summaryApi.getExpenses().get(0).getCategory(), summary.getExpenses().get(0).getCategory());
		assertEquals(summaryApi.getExpenses().get(0).getAmount(), summary.getExpenses().get(0).getAmount());
		
		assertEquals(summaryApi.getExpenses().get(1).getCategory(), summary.getExpenses().get(1).getCategory());
		assertEquals(summaryApi.getExpenses().get(1).getAmount(), summary.getExpenses().get(1).getAmount());
	}
}
