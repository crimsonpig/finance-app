package com.crimsonpig.finance.app;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonpig.finance.budget.BudgetItem;
import com.crimsonpig.finance.entity.BudgetItemEntity;
import com.crimsonpig.finance.repository.BudgetItemsJpaRepository;

import static com.crimsonpig.finance.entity.BudgetItemSpecification.findBudgetItems;

@RestController
public class BudgetItemsRestController {

	@Autowired
	private BudgetItemsJpaRepository budgetItemsDao;
	
	@RequestMapping(path = "/budget", method = GET)
	public List<BudgetItem> retrieveBudgetItems(
			@RequestParam(name = "startDt", required = true) String startDt, 
			@RequestParam(name = "endDt", required = true) String endDt, 
			@RequestParam(name = "category", required = false) String category){

		List<BudgetItemEntity> entities = budgetItemsDao.findAll(findBudgetItems(LocalDate.parse(startDt), LocalDate.parse(endDt), category));

		List<BudgetItem> budgetItems = entities.stream().map(entity -> {
			BudgetItem item = new BudgetItem(entity.getId(), entity.getCategory(), entity.getAmount(), entity.getItemType(), entity.getStartDate().toLocalDate(), entity.getEndDate().toLocalDate());
			return item;
		}).collect(Collectors.toList());
		
		return budgetItems;
	}

}
