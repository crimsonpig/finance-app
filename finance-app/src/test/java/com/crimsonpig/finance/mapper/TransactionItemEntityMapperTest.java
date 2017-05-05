package com.crimsonpig.finance.mapper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import com.crimsonpig.finance.domain.Transaction;
import com.crimsonpig.finance.entity.TransactionEntity;

public class TransactionItemEntityMapperTest {

	@Test
	public void testMapEntityToTransaction(){
		TransactionEntity entity = getTransactionEntity();
		TransactionEntityMapper mapper = new TransactionEntityMapper();
		Transaction t = mapper.mapFromEntity(entity);
		Transaction expected = getTransaction();
		assertEquals(expected.getTid(), t.getTid());
		assertEquals(expected.getCategory(), t.getCategory());
		assertEquals(expected.getAmount(), t.getAmount());
		assertEquals(expected.gettType(), t.gettType());
		assertEquals(expected.gettDate(), t.gettDate());
	}
	
	@Test
	public void testMapTransactionToEntity(){
		Transaction t = getTransaction();
		TransactionEntityMapper mapper = new TransactionEntityMapper();
		TransactionEntity entity = mapper.mapToEntity(t);
		TransactionEntity expected = getTransactionEntity();
		assertEquals(expected.getTid(), entity.getTid());
		assertEquals(expected.getCategory(), entity.getCategory());
		assertEquals(expected.getAmount(), entity.getAmount());
		assertEquals(expected.getTType(), entity.getTType());
		assertEquals(expected.getTDate(), entity.getTDate());
	}
	
	private Transaction getTransaction(){
		return new Transaction(14L, "PAYCHECK", LocalDate.of(2016, 3, 30), "I", new BigDecimal(2050.00));
	}
	
	private TransactionEntity getTransactionEntity(){
		TransactionEntity entity = new TransactionEntity();
		entity.setTid(14L);
		entity.setCategory("PAYCHECK");
		entity.setTDate(Date.valueOf("2016-03-30"));
		entity.setTType("I");
		entity.setAmount(new BigDecimal(2050.00));
		return entity;
	}
}
