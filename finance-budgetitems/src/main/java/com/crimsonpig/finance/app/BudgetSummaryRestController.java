package com.crimsonpig.finance.app;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonpig.finance.domain.BudgetItem;
import com.crimsonpig.finance.domain.SummaryResponse;
import com.crimsonpig.finance.entity.BudgetItemEntity;
import com.crimsonpig.finance.entity.BudgetItemSpecification;
import com.crimsonpig.finance.mapper.BudgetItemEntityMapper;
import com.crimsonpig.finance.repository.BudgetItemsJpaRepository;
import com.crimsonpig.finance.service.BudgetItemAmountService;
import com.crimsonpig.finance.service.BudgetSummaryService;

@RestController
public class BudgetSummaryRestController {

	@Autowired
	private BudgetItemsJpaRepository budgetItemsDao;

	private BudgetSummaryService summarizer;
	
	private BudgetItemAmountService amountAdjuster;

	private BudgetItemEntityMapper mapper;

	public BudgetSummaryRestController() {
		summarizer = new BudgetSummaryService();
		mapper = new BudgetItemEntityMapper();
		amountAdjuster = new BudgetItemAmountService();
	}

	@RequestMapping(path = "/reports/budget", method = GET)
	public SummaryResponse retrieveBudgetSummary(
			@RequestParam(name = "startDt", required = true) String startDt,
			@RequestParam(name = "endDt", required = true) String endDt,
			@RequestParam(name = "category", required = false) String category) {

		LocalDate startDate = LocalDate.parse(startDt);
		LocalDate endDate = LocalDate.parse(endDt);

		List<BudgetItemEntity> budgetItemEntities = budgetItemsDao
				.findAll(BudgetItemSpecification.findBudgetItems(startDate, endDate, category));

		List<BudgetItem> budgetItems = budgetItemEntities.stream()
				.map(entity -> mapper.mapFromEntity(entity))
				.map(budgetItem -> amountAdjuster.computeRelativeAmountByDateRange(budgetItem, startDate, endDate))
				.collect(Collectors.toList());

		SummaryResponse summary = summarizer.buildBudgetSummary(budgetItems);
		
		return summary;
	}

}
