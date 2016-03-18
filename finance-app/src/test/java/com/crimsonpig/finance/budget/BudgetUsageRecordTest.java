package com.crimsonpig.finance.budget;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class BudgetUsageRecordTest {

	@Test
	public void testNetDifferenceBudgetUsageRecord(){
		BudgetUsageRecord usageRecord = new BudgetUsageRecord("GAS", new BigDecimal(150), new BigDecimal(100.25));
		assertEquals(new BigDecimal(49.75) ,usageRecord.getNetDifference());
	}
	
}
