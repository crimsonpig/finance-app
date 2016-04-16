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

	public static Specification<BudgetItemEntity> findBudgetItems(LocalDate startDate, LocalDate endDate, String category){
		return new Specification<BudgetItemEntity>(){

			public Predicate toPredicate(Root<BudgetItemEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate startDatePredicate = builder.lessThanOrEqualTo(root.get(BudgetItemEntity_.startDate), Date.valueOf(endDate));
				Predicate endDatePredicate = builder.greaterThanOrEqualTo(root.get(BudgetItemEntity_.endDate), Date.valueOf(startDate));
				if(category == null){
					return builder.and(startDatePredicate, endDatePredicate);
				} else {
				Predicate categoryPredicate = builder.equal(root.get(BudgetItemEntity_.category), category);
					return builder.and(startDatePredicate, endDatePredicate, categoryPredicate);
				}
			}
			
		};
	}

}
