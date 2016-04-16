package com.crimsonpig.finance.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crimsonpig.finance.entity.BudgetItemEntity;

public interface BudgetItemsJpaRepository extends JpaRepository<BudgetItemEntity, Long>, JpaSpecificationExecutor<BudgetItemEntity> {

	@Query("select b from BudgetItemEntity b where b.startDate <= :endDt and b.endDate >= :startDt")
	List<BudgetItemEntity> findByStartAndEndDates(@Param("startDt") Date startDate, @Param("endDt") Date endDate);
}
