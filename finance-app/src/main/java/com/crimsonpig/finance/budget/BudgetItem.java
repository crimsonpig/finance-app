package com.crimsonpig.finance.budget;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetItem {

	private enum ItemType { I, E }
	
	private Long id;
	private String category;
	private BigDecimal amount;
	private ItemType itemType;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public BudgetItem(Long id, String category, BigDecimal amount, String itemType, LocalDate startDate, LocalDate endDate) {
		this.id = id;
		this.category = category;
		this.amount = amount;
		this.itemType = ItemType.valueOf(itemType);
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
		return itemType.toString();
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
	
	
	
	
}
