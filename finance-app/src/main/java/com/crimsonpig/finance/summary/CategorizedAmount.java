package com.crimsonpig.finance.summary;

import java.math.BigDecimal;

public class CategorizedAmount {

	private String category;
	
	private BigDecimal amount;

	public CategorizedAmount(String category, BigDecimal amount) {
		this.category = category;
		this.amount = amount;
	}
	
	protected CategorizedAmount(){}

	public String getCategory() {
		return category;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
