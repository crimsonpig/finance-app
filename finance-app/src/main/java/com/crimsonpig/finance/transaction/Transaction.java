package com.crimsonpig.finance.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

	private String category;
	
	private LocalDate tDate;
	
	private String tType;
	
	private BigDecimal amount;

	public Transaction(String category, LocalDate tDate, String tType, BigDecimal amount) {
		this.category = category;
		this.tDate = tDate;
		this.tType = tType;
		this.amount = amount;
	}
	
	protected Transaction(){}

	public String getCategory() {
		return category;
	}

	public LocalDate gettDate() {
		return tDate;
	}

	public String getTType() {
		return tType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
