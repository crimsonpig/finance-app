package com.crimsonpig.finance.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

	private String category;
	
	private LocalDate tDate;
	
	private String tType;
	
	private BigDecimal amount;

	public Transaction() {

	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getTDate() {
		return tDate;
	}

	public void setTDate(LocalDate tDate) {
		this.tDate = tDate;
	}

	public String getTType() {
		return tType;
	}

	public void setTType(String tType) {
		this.tType = tType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}	
	
}
