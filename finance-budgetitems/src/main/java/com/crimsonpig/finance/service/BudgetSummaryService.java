package com.crimsonpig.finance.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.crimsonpig.finance.domain.BudgetItem;
import com.crimsonpig.finance.domain.CategorizedAmount;
import com.crimsonpig.finance.domain.SummaryResponse;

public class BudgetSummaryService {

	public SummaryResponse buildBudgetSummary(List<BudgetItem> budgetItems) {
		SummaryResponse response = new SummaryResponse();
		response.setExpenses(consolidateBudgetItems("E", budgetItems));
		response.setIncomes(consolidateBudgetItems("I", budgetItems));
		response.setExpensesTotal(calculateTotal(response.getExpenses()));
		response.setIncomesTotal(calculateTotal(response.getIncomes()));
		response.setNetTotal(response.getIncomesTotal().subtract(response.getExpensesTotal()));
		return response;
	}

	private List<CategorizedAmount> consolidateBudgetItems(String itemType, List<BudgetItem> budgetItems) {

		return getCategoriesStream(itemType, budgetItems)
				.map(category -> sumUpCategory(category, budgetItems))
				.collect(Collectors.toList());
	}

	private Stream<String> getCategoriesStream(String itemType, List<BudgetItem> budgetItems){
		return budgetItems
				.stream()
				.filter(item -> itemType.equals(item.getItemType()))
				.map(item -> item.getCategory())
				.distinct()
				.parallel();
	}

	private CategorizedAmount sumUpCategory(String category, List<BudgetItem> budgetItems){
		BigDecimal categoryTotal = budgetItems
				.stream()
				.filter(item -> category.equals(item.getCategory()))
				.map(item -> item.getAmount())
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		return new CategorizedAmount(category, categoryTotal);
	}
	
	private BigDecimal calculateTotal(List<CategorizedAmount> amounts) {
		return amounts.stream().map(item -> item.getAmount()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

}
