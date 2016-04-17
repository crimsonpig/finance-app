package com.crimsonpig.finance.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`TRANSACTIONS`")
public class TransactionEntity {

	@Id
	@GeneratedValue
	@Column(name = "`TID`")
	private Long tid;

	@Column(name = "`T_DATE`")
	private Date tDate;

	@Column(name = "`T_TYPE`")
	private String tType;
	
	@Column(name = "`CATEGORY`")
	private String category;
	
	@Column(name = "`AMOUNT`")
	private BigDecimal amount;
	
	public TransactionEntity(){}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Date getTDate() {
		return tDate;
	}

	public void setTDate(Date tDate) {
		this.tDate = tDate;
	}

	public String getTType() {
		return tType;
	}

	public void setTType(String tType) {
		this.tType = tType;
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
