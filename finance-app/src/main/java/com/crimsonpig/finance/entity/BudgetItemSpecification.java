package com.crimsonpig.finance.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class BudgetItemSpecification {
//    budgetItems
//    .filter(_.startDate <= params.endDt)
//    .filter(_.endDate >= params.startDt))

	public static Specification<BudgetItemEntity> findByDates(LocalDate startDate, LocalDate endDate){
		return new Specification<BudgetItemEntity>(){

			public Predicate toPredicate(Root<BudgetItemEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.and(builder.lessThanOrEqualTo(root.get(BudgetItemEntity_.startDate), Date.valueOf(endDate)), 
						builder.greaterThanOrEqualTo(root.get(BudgetItemEntity_.endDate), Date.valueOf(startDate)));
			}
			
		};
	}

}
