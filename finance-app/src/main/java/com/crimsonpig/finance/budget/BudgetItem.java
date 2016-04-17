package com.crimsonpig.finance.budget;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetItem {

	private Long id;
	private String category;
	private BigDecimal amount;
	private String itemType;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public BudgetItem(Long id, String category, BigDecimal amount, String itemType, LocalDate startDate, LocalDate endDate) {
		this.id = id;
		this.category = category;
		this.amount = amount;
		this.itemType = itemType;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	protected BudgetItem(){
	}

	public Long getId(){
		return id;
	}
	
	public String getCategory() {
		return category;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getItemType() {
		return itemType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	
	
}
