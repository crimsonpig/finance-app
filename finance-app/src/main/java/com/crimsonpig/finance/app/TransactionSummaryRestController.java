package com.crimsonpig.finance.app;

import static com.crimsonpig.finance.entity.TransactionSpecification.findTransactions;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonpig.finance.domain.SummaryResponse;
import com.crimsonpig.finance.domain.Transaction;
import com.crimsonpig.finance.entity.TransactionEntity;
import com.crimsonpig.finance.mapper.TransactionEntityMapper;
import com.crimsonpig.finance.repository.TransactionsJpaRepository;
import com.crimsonpig.finance.service.TransactionSummaryService;

@RestController
public class TransactionSummaryRestController {

	@Autowired
	private TransactionsJpaRepository transactionsDao;
	
	private TransactionSummaryService summarizer;
	
	private TransactionEntityMapper mapper;
	
	public TransactionSummaryRestController(){
		summarizer = new TransactionSummaryService();
		mapper = new TransactionEntityMapper();
	}
	
	@RequestMapping(path = "/reports/transactions", method = GET)
	public SummaryResponse retrieveTransactionSummary(
			@RequestParam(name = "startDt", required = true) String startDt, 
			@RequestParam(name = "endDt", required = true) String endDt, 
			@RequestParam(name = "category", required = false) String category){
		
		List<TransactionEntity> entities = transactionsDao
				.findAll(findTransactions(LocalDate.parse(startDt), LocalDate.parse(endDt), category));
		
		List<Transaction> transactions = entities
				.stream()
				.map(entity -> mapper.mapFromEntity(entity))
				.collect(Collectors.toList());
		
		SummaryResponse summary = summarizer.buildTransactionSummary(transactions);
		
		return summary;
	}
	
}
