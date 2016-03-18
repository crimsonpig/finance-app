package com.crimsonpig.finance.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
	
	private enum TType {E, I}
	
	private final String category;
	
	private final LocalDate tDate;
	
	private final TType tType;
	
	private final BigDecimal amount;

	public Transaction(String category, LocalDate tDate, String tType, BigDecimal amount) {
		this.category = category;
		this.tDate = tDate;
		this.tType = TType.valueOf(tType);
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public LocalDate gettDate() {
		return tDate;
	}

	public String gettType() {
		return tType.toString();
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
