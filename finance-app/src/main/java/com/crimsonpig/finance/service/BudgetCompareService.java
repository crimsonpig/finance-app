package com.crimsonpig.finance.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.crimsonpig.finance.budget.CompareRecord;
import com.crimsonpig.finance.summary.CategorizedAmount;

public class BudgetCompareService {

	public List<CompareRecord> compareExpectedWithActual(List<CategorizedAmount> budgetItems, List<CategorizedAmount> actualItems) {

		return getCategoriesStream(budgetItems.stream(), actualItems.stream())
				.map(category -> buildRecord(category, budgetItems.stream(), actualItems.stream()))
				.collect(Collectors.toList());
	}

	private Stream<String> getCategoriesStream(Stream<CategorizedAmount> budgetItems, Stream<CategorizedAmount> actualItems){
		return Stream.concat(
				budgetItems.map(item -> item.getCategory()), 
				actualItems.map(item -> item.getCategory()))
				.distinct().parallel();
	}

	private CompareRecord buildRecord(String category, Stream<CategorizedAmount> budgetItems, Stream<CategorizedAmount> actualItems){
		BigDecimal expectedAmount = getAmountFromItemCategory(category, budgetItems);
		BigDecimal actualAmount = getAmountFromItemCategory(category, actualItems);
		return new CompareRecord(category, expectedAmount, actualAmount, expectedAmount.subtract(actualAmount));
	}

	private BigDecimal getAmountFromItemCategory(String category, Stream<CategorizedAmount> items) {
		return items
				.filter(item -> category.equals(item.getCategory()))
				.map(item -> item.getAmount())
				.findFirst()
				.orElse(BigDecimal.ZERO);
	}

}
