package com.crimsonpig.finance.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecification {

	private static final Predicate[] predArr = new Predicate[0];
	
	public static Specification<TransactionEntity> findTransactions(LocalDate startDate, LocalDate endDate, String category){
		return new Specification<TransactionEntity>(){

			@Override
			public Predicate toPredicate(Root<TransactionEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>(3);
				
				Predicate startDatePredicate = builder.greaterThanOrEqualTo(root.get("tDate"), Date.valueOf(startDate));
				Predicate endDatePredicate = builder.lessThanOrEqualTo(root.get("tDate"), Date.valueOf(endDate));
				
				predicates.add(startDatePredicate);
				predicates.add(endDatePredicate);
				
				if(category != null){
					Predicate categoryPredicate = builder.equal(root.get("category"), category);
					predicates.add(categoryPredicate);
				} 
				
				return builder.and(predicates.toArray(predArr));
			}
			
		};
	}
}
