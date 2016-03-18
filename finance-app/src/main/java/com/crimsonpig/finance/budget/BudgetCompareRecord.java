package com.crimsonpig.finance.budget;

import java.math.BigDecimal;

public class BudgetCompareRecord {

	private final String category;
	private final BigDecimal expectedAmount;
	private final BigDecimal actualAmount;
	
	public BudgetCompareRecord(String category, BigDecimal expectedAmount, BigDecimal actualAmount) {
		this.category = category;
		this.expectedAmount = expectedAmount;
		this.actualAmount = actualAmount;
	}

	public String getCategory() {
		return category;
	}

	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public BigDecimal getNetDifference(){
		return expectedAmount.subtract(actualAmount);
	}
}
