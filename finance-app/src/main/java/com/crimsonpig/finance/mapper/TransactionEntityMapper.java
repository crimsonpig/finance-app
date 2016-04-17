package com.crimsonpig.finance.mapper;

import java.sql.Date;

import com.crimsonpig.finance.entity.TransactionEntity;
import com.crimsonpig.finance.transaction.Transaction;

public class TransactionEntityMapper {

	public Transaction mapFromEntity(TransactionEntity entity){
		return new Transaction(
				entity.getTid(),
				entity.getCategory(), 
				entity.getTDate().toLocalDate(), 
				entity.getTType(), 
				entity.getAmount()
				);
	}
	
	public TransactionEntity mapToEntity(Transaction t){
		TransactionEntity entity = new TransactionEntity();
		entity.setTid(t.getTid());
		entity.setCategory(t.getCategory());
		entity.setAmount(t.getAmount());
		entity.setTType(t.getTType());
		entity.setTDate(Date.valueOf(t.getTDate()));
		return entity;
	}
}
