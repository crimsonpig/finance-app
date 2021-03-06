package com.crimsonpig.finance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

	private Long tid;
	
	private String category;

	private LocalDate tDate;

	private String tType;
	
	private BigDecimal amount;

	public Transaction(Long tid, String category, LocalDate tDate, String tType, BigDecimal amount) {
		this.tid = tid;
		this.category = category;
		this.tDate = tDate;
		this.tType = tType;
		this.amount = amount;
	}
	
	protected Transaction(){}

	public Long getTid(){
		return tid;
	}
	
	public String getCategory() {
		return category;
	}

	public LocalDate gettDate() {
		return tDate;
	}

	public String gettType() {
		return tType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
