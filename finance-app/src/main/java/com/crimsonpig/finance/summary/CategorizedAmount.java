package com.crimsonpig.finance.summary;

import java.math.BigDecimal;

public class CategorizedAmount {

	private final String category;
	
	private final BigDecimal amount;

	public CategorizedAmount(String category, BigDecimal amount) {
		this.category = category;
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
