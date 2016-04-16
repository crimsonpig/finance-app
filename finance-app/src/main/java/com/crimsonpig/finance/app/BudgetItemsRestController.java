package com.crimsonpig.finance.app;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonpig.finance.entity.BudgetItemEntity;
import com.crimsonpig.finance.repository.BudgetItemsJpaRepository;

import static com.crimsonpig.finance.entity.BudgetItemSpecification.findByDates;

@RestController
public class BudgetItemsRestController {

	@Autowired
	private BudgetItemsJpaRepository budgetItemsDao;
	
	@RequestMapping(path = "/budget", method = GET)
	public String retrieveBudgetItems(
			@RequestParam(name = "startDt", required = true) String startDt, 
			@RequestParam(name = "endDt", required = true) String endDt, 
			@RequestParam(name = "category", required = false) String category){
		
		StringBuilder sb = new StringBuilder();
		
		budgetItemsDao.findAll(findByDates(LocalDate.parse(startDt), LocalDate.parse(endDt))).forEach(item -> populate(sb,item));
		
		return sb.toString();
	}

	private void populate(StringBuilder sb, BudgetItemEntity item) {
		sb.append(item.getCategory());
		sb.append(item.getStartDate());
		sb.append(item.getEndDate());
		sb.append(item.getItemType());
		sb.append(item.getAmount());
		sb.append("&nbsp;&nbsp;");
		
	}
}
