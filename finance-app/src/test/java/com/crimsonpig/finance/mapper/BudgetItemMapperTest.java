package com.crimsonpig.finance.mapper;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import com.crimsonpig.finance.api.PlannedBudgetItem;
import com.crimsonpig.finance.budget.BudgetItem;

public class BudgetItemMapperTest {

	@Test
	public void testMapFromApiObject(){
		PlannedBudgetItem planItem = new PlannedBudgetItem();
		planItem.setCategory("PAYCHECK");
		planItem.setAmount(new BigDecimal(2050));
		planItem.setItemType("I");
		planItem.setStartDate(LocalDate.of(2016, 3, 1));
		planItem.setEndDate(LocalDate.of(2016, 3, 15));
		
		BudgetItem item = new BudgetItemMapper().mapFromApiObject(planItem);
		
		assertEquals(planItem.getCategory(), item.getCategory());
		assertEquals(planItem.getAmount(), item.getAmount());
		assertEquals(planItem.getItemType(), item.getItemType());
		assertEquals(planItem.getStartDate(), item.getStartDate());
		assertEquals(planItem.getEndDate(), item.getEndDate());
	}
	
}
