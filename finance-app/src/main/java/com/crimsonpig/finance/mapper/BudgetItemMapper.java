package com.crimsonpig.finance.mapper;

import com.crimsonpig.finance.api.PlannedBudgetItem;
import com.crimsonpig.finance.budget.BudgetItem;

public class BudgetItemMapper {

	public BudgetItem mapFromApiObject(PlannedBudgetItem apiItem){
		return new BudgetItem(apiItem.getCategory(), apiItem.getAmount(), apiItem.getItemType(), apiItem.getStartDate(), apiItem.getEndDate());
	}
	

}
