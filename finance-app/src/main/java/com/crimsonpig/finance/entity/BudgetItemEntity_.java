package com.crimsonpig.finance.entity;

import javax.persistence.metamodel.StaticMetamodel;

import java.sql.Date;

import javax.persistence.metamodel.SingularAttribute;

@StaticMetamodel(BudgetItemEntity.class)
public class BudgetItemEntity_ {

	public static volatile SingularAttribute<BudgetItemEntity, Date> startDate;
	
	public static volatile SingularAttribute<BudgetItemEntity, Date> endDate;
	
	public static volatile SingularAttribute<BudgetItemEntity, String> category;
}
