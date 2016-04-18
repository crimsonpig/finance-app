package com.crimsonpig.finance.mapper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import com.crimsonpig.finance.domain.BudgetItem;
import com.crimsonpig.finance.entity.BudgetItemEntity;

public class BudgetItemEntityMapperTest {

	@Test
	public void testMapFromEntity(){
		BudgetItemEntity entity = getEntity();
		BudgetItemEntityMapper mapper = new BudgetItemEntityMapper();
		BudgetItem item = mapper.mapFromEntity(entity);
		BudgetItem expected = getItem();
		assertEquals(expected.getId(), item.getId());
		assertEquals(expected.getCategory(), item.getCategory());
		assertEquals(expected.getAmount(), item.getAmount());
		assertEquals(expected.getItemType(), item.getItemType());
		assertEquals(expected.getStartDate(), item.getStartDate());
		assertEquals(expected.getEndDate(), item.getEndDate());
	}
	
	@Test
	public void testMapToEntity(){
		BudgetItem item = getItem();
		BudgetItemEntityMapper mapper = new BudgetItemEntityMapper();
		BudgetItemEntity entity = mapper.mapToEntity(item);
		BudgetItemEntity expected = getEntity();
		assertEquals(expected.getId(), entity.getId());
		assertEquals(expected.getCategory(), entity.getCategory());
		assertEquals(expected.getAmount(), entity.getAmount());
		assertEquals(expected.getItemType(), entity.getItemType());
		assertEquals(expected.getStartDate(), entity.getStartDate());
		assertEquals(expected.getEndDate(), entity.getEndDate());
	}
	
	private BudgetItemEntity getEntity(){
		BudgetItemEntity entity = new BudgetItemEntity();
		entity.setId(1L);
		entity.setCategory("FOOD");
		entity.setAmount(new BigDecimal(200.00));
		entity.setItemType("E");
		entity.setStartDate(Date.valueOf("2016-04-01"));
		entity.setEndDate(Date.valueOf("2016-04-30"));
		return entity;
	}
	
	private BudgetItem getItem(){
		return new BudgetItem(
				1L, "FOOD", new BigDecimal(200.00), "E", 
				LocalDate.of(2016, 4, 1), LocalDate.of(2016, 4, 30));
	}
	
}
