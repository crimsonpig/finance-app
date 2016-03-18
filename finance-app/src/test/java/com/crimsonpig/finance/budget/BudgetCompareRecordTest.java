package com.crimsonpig.finance.budget;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class BudgetCompareRecordTest {

	@Test
	public void testNetDifferenceBudgetUsageRecord(){
		BudgetCompareRecord usageRecord = new BudgetCompareRecord("GAS", new BigDecimal(150), new BigDecimal(100.25));
		assertEquals(new BigDecimal(49.75) ,usageRecord.getNetDifference());
	}
	
}
