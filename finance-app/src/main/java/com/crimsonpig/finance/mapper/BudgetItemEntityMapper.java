package com.crimsonpig.finance.mapper;

import java.sql.Date;

import com.crimsonpig.finance.budget.BudgetItem;
import com.crimsonpig.finance.entity.BudgetItemEntity;

public class BudgetItemEntityMapper {

	public BudgetItem mapFromEntity(BudgetItemEntity entity){
		return new BudgetItem(entity.getId(), 
				entity.getCategory(), 
				entity.getAmount(), 
				entity.getItemType(), 
				entity.getStartDate().toLocalDate(), 
				entity.getEndDate().toLocalDate());
	}
	
	public BudgetItemEntity mapToEntity(BudgetItem item){
		BudgetItemEntity entity = new BudgetItemEntity();
		entity.setId(item.getId()); 
		entity.setCategory(item.getCategory());
		entity.setAmount(item.getAmount()); 
		entity.setItemType(item.getItemType()); 
		entity.setStartDate(Date.valueOf(item.getStartDate())); 
		entity.setEndDate(Date.valueOf(item.getEndDate()));
		return entity;
	}
}
