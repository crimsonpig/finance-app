package com.crimsonpig.finance.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

	private Long id;
	
	private String category;
	
	private LocalDate tDate;
	
	private String tType;
	
	private BigDecimal amount;

	public Transaction(Long id, String category, LocalDate tDate, String tType, BigDecimal amount) {
		this.id = id;
		this.category = category;
		this.tDate = tDate;
		this.tType = tType;
		this.amount = amount;
	}
	
	protected Transaction(){}

	public Long getId(){
		return id;
	}
	
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
