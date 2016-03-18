package com.crimsonpig.finance.api;

import java.math.BigDecimal;

public class BudgetCompareRecord {

	private String category;
	private BigDecimal expectedAmount;
	private BigDecimal actualAmount;

	public BudgetCompareRecord(){
		
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	
}