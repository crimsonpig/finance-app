package com.crimsonpig.finance.app;

import static org.springframework.web.bind.annotation.RequestMethod.*;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonpig.finance.domain.BudgetItem;
import com.crimsonpig.finance.entity.BudgetItemEntity;
import com.crimsonpig.finance.mapper.BudgetItemEntityMapper;
import com.crimsonpig.finance.repository.BudgetItemsJpaRepository;

import static com.crimsonpig.finance.entity.BudgetItemSpecification.findBudgetItems;

@RestController
public class BudgetItemsRestController {

	@Autowired
	private BudgetItemsJpaRepository budgetItemsDao;
	
	private BudgetItemEntityMapper mapper;
	
	public BudgetItemsRestController(){
		mapper = new BudgetItemEntityMapper();
	}
	
	@RequestMapping(path = "/budget", method = GET)
	public List<BudgetItem> retrieveBudgetItems(
			@RequestParam(name = "startDt", required = true) String startDt, 
			@RequestParam(name = "endDt", required = true) String endDt, 
			@RequestParam(name = "category", required = false) String category){

		List<BudgetItemEntity> entities = budgetItemsDao
				.findAll(findBudgetItems(LocalDate.parse(startDt), LocalDate.parse(endDt), category));

		List<BudgetItem> budgetItems = entities
				.stream()
				.map(entity -> mapper.mapFromEntity(entity))
				.collect(Collectors.toList());
		
		return budgetItems;
	}
	
	@RequestMapping(path = "/budget", method = POST)
	public BudgetItem saveBudgetItem(@RequestBody BudgetItem item, HttpServletResponse response){
		
		BudgetItemEntity entity = mapper.mapToEntity(item);
		BudgetItemEntity savedEntity = budgetItemsDao.save(entity);
		BudgetItem persistedItem = mapper.mapFromEntity(savedEntity);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return persistedItem;
	}
	
	@RequestMapping(path = "/budget/{id}")
	public BudgetItem deleteBudgetItem(@PathVariable String id){
		Long idValue = Long.parseLong(id);
		BudgetItemEntity entity = budgetItemsDao.findOne(idValue);
		BudgetItem deletedItem = mapper.mapFromEntity(entity);
		budgetItemsDao.delete(idValue);
		return deletedItem;
	}

}
