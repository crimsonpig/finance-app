package com.crimsonpig.finance.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.crimsonpig.finance.api.BudgetCompareRecord;
import com.crimsonpig.finance.api.BudgetComparisonResponse;
import com.crimsonpig.finance.budget.CompareRecord;
import com.crimsonpig.finance.budget.ComparisonResponse;

public class BudgetComparisonResponseMapper {

	public BudgetComparisonResponse 
		mapToApiClass(ComparisonResponse comparison){
		BudgetComparisonResponse toReturn = new BudgetComparisonResponse();
		toReturn.setExpenses(mapCompares(comparison.getExpenses()));		
		toReturn.setIncomes(mapCompares(comparison.getIncomes()));
		toReturn.setActualExpenseTotal(comparison.getActualExpenseTotal());
		toReturn.setExpectedExpenseTotal(comparison.getExpectedExpenseTotal());
		toReturn.setActualIncomeTotal(comparison.getActualIncomeTotal());
		toReturn.setExpectedIncomeTotal(comparison.getExpectedIncomeTotal());
		toReturn.setNetExpenseDifference(comparison.getNetExpenseDifference());
		toReturn.setNetIncomeDifference(comparison.getNetIncomeDifference());
		toReturn.setTotalNetDifference(comparison.getTotalNetDifference());
		return toReturn;
	}

	private List<BudgetCompareRecord> mapCompares(List<CompareRecord> items){
		return items.stream().map(item -> mapCompareRecord(item)).collect(Collectors.toList());
	}
	
	private BudgetCompareRecord mapCompareRecord(CompareRecord item){
		BudgetCompareRecord record = new BudgetCompareRecord();
		record.setCategory(item.getCategory());
		record.setActualAmount(item.getActualAmount());
		record.setExpectedAmount(item.getExpectedAmount());
		record.setNetDifference(item.getNetDifference());
		return record;
	}
	
}
