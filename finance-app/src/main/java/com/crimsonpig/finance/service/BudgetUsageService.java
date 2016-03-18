package com.crimsonpig.finance.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.crimsonpig.finance.budget.BudgetItem;
import com.crimsonpig.finance.budget.BudgetUsageRecord;
import com.crimsonpig.finance.summary.CategorizedAmount;

public class BudgetUsageService {

	public List<BudgetUsageRecord> compareExpectedWithActual(List<BudgetItem> budgetItems, List<CategorizedAmount> actualItems) {

		return getCategoriesStream(budgetItemToCategorizedAmount(budgetItems), actualItems.stream())
				.map(category -> buildRecord(category, budgetItemToCategorizedAmount(budgetItems), actualItems.stream()))
				.collect(Collectors.toList());
	}

	private Stream<String> getCategoriesStream(Stream<CategorizedAmount> budgetItems, Stream<CategorizedAmount> actualItems){
		return Stream.concat(
				budgetItems.map(item -> item.getCategory()), 
				actualItems.map(item -> item.getCategory()))
				.distinct().parallel();
	}
	
	private Stream<CategorizedAmount> budgetItemToCategorizedAmount(List<BudgetItem> budgetItems) {
		return budgetItems.stream().map(item -> new CategorizedAmount(item.getCategory(), item.getAmount()));
	}
	
	private BudgetUsageRecord buildRecord(String category, Stream<CategorizedAmount> budgetItems, Stream<CategorizedAmount> actualItems){
		BigDecimal expectedAmount = getAmountFromItemCategory(category, budgetItems);
		BigDecimal actualAmount = getAmountFromItemCategory(category, actualItems);
		return new BudgetUsageRecord(category, expectedAmount, actualAmount);
	}

	private BigDecimal getAmountFromItemCategory(String category, Stream<CategorizedAmount> items) {
		return items
				.filter(item -> category.equals(item.getCategory()))
				.map(item -> item.getAmount())
				.findFirst()
				.orElse(BigDecimal.ZERO);
	}

}
