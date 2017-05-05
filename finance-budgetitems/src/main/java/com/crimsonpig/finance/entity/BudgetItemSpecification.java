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

public class BudgetItemSpecification {
//    budgetItems
//    .filter(_.startDate <= params.endDt)
//    .filter(_.endDate >= params.startDt))

	private static final Predicate[] predArr = new Predicate[0];
	
	public static Specification<BudgetItemEntity> findBudgetItems(LocalDate startDate, LocalDate endDate, String category){

		return new Specification<BudgetItemEntity>(){

			public Predicate toPredicate(Root<BudgetItemEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				List<Predicate> predicates = new ArrayList<Predicate>(3);
				
				Predicate startDatePredicate = builder.lessThanOrEqualTo(root.get("startDate"), Date.valueOf(endDate));
				Predicate endDatePredicate = builder.greaterThanOrEqualTo(root.get("endDate"), Date.valueOf(startDate));
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
