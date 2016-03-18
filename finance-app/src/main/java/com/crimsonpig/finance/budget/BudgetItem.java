package com.crimsonpig.finance.budget;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetItem {

	private enum ItemType { I, E }
	
	private final String category;
	private final BigDecimal amount;
	private final ItemType itemType;
	private final LocalDate startDate;
	private final LocalDate endDate;
	
	public BudgetItem(String category, BigDecimal amount, String itemType, LocalDate startDate, LocalDate endDate) {
		this.category = category;
		this.amount = amount;
		this.itemType = ItemType.valueOf(itemType);
		this.startDate = startDate;
		this.endDate = endDate;
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
