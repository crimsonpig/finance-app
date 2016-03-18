package com.crimsonpig.finance.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.crimsonpig.finance.budget.BudgetItem;
import com.crimsonpig.finance.summary.CategorizedAmount;
import com.crimsonpig.finance.summary.SummaryResponse;
import com.crimsonpig.finance.transaction.Transaction;

public class TransactionSummaryService {

	public SummaryResponse buildTransactionSummary(List<Transaction> transactions) {
		SummaryResponse response = new SummaryResponse();
		response.setExpenses(consolidateTransaction("E", transactions));
		response.setIncomes(consolidateTransaction("I", transactions));
		response.setExpensesTotal(calculateTotal(response.getExpenses()));
		response.setIncomesTotal(calculateTotal(response.getIncomes()));
		response.setNetTotal(response.getIncomesTotal().subtract(response.getExpensesTotal()));
		return response;
	}

	private List<CategorizedAmount> consolidateTransaction(String itemType, List<Transaction> transactions) {
		return getCategoriesStream(itemType, transactions)
				.map(category -> sumUpCategory(category, transactions))
				.collect(Collectors.toList());
	}
	
	private Stream<String> getCategoriesStream(String type, List<Transaction> transactions){
		return transactions
				.stream()
				.filter(item -> type.equals(item.getTType()))
				.map(item -> item.getCategory())
				.distinct()
				.parallel();
	}
	
	private CategorizedAmount sumUpCategory(String category, List<Transaction> transactions){
		BigDecimal categoryTotal = transactions
				.stream()
				.filter(transaction -> category.equals(transaction.getCategory()))
				.map(transaction -> transaction.getAmount())
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		return new CategorizedAmount(category, categoryTotal);
	}

	private BigDecimal calculateTotal(List<CategorizedAmount> amounts) {
		return amounts.stream().map(item -> item.getAmount()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}
}
