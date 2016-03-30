package com.crimsonpig.finance.budget;

import java.math.BigDecimal;

public class CompareRecord {

	private final String category;
	private final BigDecimal expectedAmount;
	private final BigDecimal actualAmount;
	private final BigDecimal netDifference;
	
	public CompareRecord(String category, BigDecimal expectedAmount, BigDecimal actualAmount, BigDecimal netDifference) {
		this.category = category;
		this.expectedAmount = expectedAmount;
		this.actualAmount = actualAmount;
		this.netDifference = netDifference;
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
		return netDifference;
	}
}
