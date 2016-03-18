package com.crimsonpig.finance.api;

import java.math.BigDecimal;

public class CategorizedAmount {
	
	private String category;
	
	private BigDecimal amount;
	
	public CategorizedAmount(){
		
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
