package com.crimsonpig.finance.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.crimsonpig.finance.api.FinancialSummary;
import com.crimsonpig.finance.summary.CategorizedAmount;
import com.crimsonpig.finance.summary.SummaryResponse;

public class SummaryMapper {

	public SummaryResponse mapFromApiObject(FinancialSummary summaryApi) {
		SummaryResponse summary = new SummaryResponse();
		summary.setExpenses(mapCategorizedAmountsFromApi(summaryApi.getExpenses()));
		summary.setIncomes(mapCategorizedAmountsFromApi(summaryApi.getIncomes()));
		summary.setExpensesTotal(summaryApi.getExpensesTotal());
		summary.setIncomesTotal(summaryApi.getIncomesTotal());
		summary.setNetTotal(summaryApi.getNetTotal());
		return summary;
	}

	private List<CategorizedAmount> mapCategorizedAmountsFromApi(
			List<com.crimsonpig.finance.api.CategorizedAmount> apiAmounts) {
		List<CategorizedAmount> summary = apiAmounts
				.stream()
				.map(apiAmount -> {
					return new CategorizedAmount(apiAmount.getCategory(), apiAmount.getAmount());
				})
				.collect(Collectors.toList());
		return summary;
	}

}
