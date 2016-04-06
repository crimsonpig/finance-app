package com.crimsonpig.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimsonpig.finance.entity.BudgetItemEntity;

public interface BudgetItemsJpaRepository extends JpaRepository<BudgetItemEntity, Long> {

}
