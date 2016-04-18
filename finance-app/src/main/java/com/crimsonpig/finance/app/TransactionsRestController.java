package com.crimsonpig.finance.app;

import static com.crimsonpig.finance.entity.TransactionSpecification.findTransactions;
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

import com.crimsonpig.finance.domain.Transaction;
import com.crimsonpig.finance.entity.TransactionEntity;
import com.crimsonpig.finance.mapper.TransactionEntityMapper;
import com.crimsonpig.finance.repository.TransactionsJpaRepository;

@RestController
public class TransactionsRestController {

	@Autowired
	private TransactionsJpaRepository transactionsDao;
	
	private TransactionEntityMapper mapper;
	
	public TransactionsRestController(){
		mapper = new TransactionEntityMapper();
	}
	
	@RequestMapping(path = "/transactions", method = GET)
	public List<Transaction> retrieveBudgetItems(
			@RequestParam(name = "startDt", required = true) String startDt, 
			@RequestParam(name = "endDt", required = true) String endDt, 
			@RequestParam(name = "category", required = false) String category){

		List<TransactionEntity> entities = transactionsDao
				.findAll(findTransactions(LocalDate.parse(startDt), LocalDate.parse(endDt), category));

		List<Transaction> transactions = entities
				.stream()
				.map(entity -> mapper.mapFromEntity(entity))
				.collect(Collectors.toList());
		
		return transactions;
	}
	
	@RequestMapping(path = "/transactions", method = POST)
	public Transaction saveTransaction(@RequestBody Transaction transaction, HttpServletResponse response){
		TransactionEntity entity = mapper.mapToEntity(transaction);
		TransactionEntity savedEntity = transactionsDao.save(entity);
		Transaction persistedTransaction = mapper.mapFromEntity(savedEntity);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return persistedTransaction;
	}
	
	@RequestMapping(path = "/transactions/{tid}", method = DELETE)
	public Transaction deleteTransaction(@PathVariable String tid){
		Long tidValue = Long.parseLong(tid);
		TransactionEntity entity = transactionsDao.findOne(tidValue);
		Transaction deletedTransaction = mapper.mapFromEntity(entity);
		transactionsDao.delete(tidValue);
		return deletedTransaction;
	}
	
}
