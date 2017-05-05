package com.crimsonpig.finance.mockData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crimsonpig.finance.domain.Transaction;

public class DomainObjectDataLists {

	public List<Transaction> getTransactionItems(){
		List<Transaction> transactions = new ArrayList<Transaction>(14);
		
		transactions.add(new Transaction(1L, "GAS", LocalDate.of(2016, 3, 2), "E", new BigDecimal(26.08)));
		transactions.add(new Transaction(2L, "HOUSEHOLD", LocalDate.of(2016, 3, 2), "E", new BigDecimal(5.97)));
		transactions.add(new Transaction(3L, "EATING OUT", LocalDate.of(2016, 3, 8), "E", new BigDecimal(18.96)));
		transactions.add(new Transaction(4L, "HOUSEHOLD", LocalDate.of(2016, 3, 8), "E", new BigDecimal(10.18)));
		transactions.add(new Transaction(5L, "HOUSEHOLD", LocalDate.of(2016, 3, 13), "E", new BigDecimal(20.20)));
		transactions.add(new Transaction(6L, "GAS", LocalDate.of(2016, 3, 12), "E", new BigDecimal(35.13)));
		transactions.add(new Transaction(7L, "GAS", LocalDate.of(2016, 3, 22), "E", new BigDecimal(44.00)));
		transactions.add(new Transaction(8L, "PAYCHECK", LocalDate.of(2016, 3, 14), "I", new BigDecimal(2050.00)));
		transactions.add(new Transaction(9L, "HOUSEHOLD", LocalDate.of(2016, 3, 20), "E", new BigDecimal(75.00)));
		transactions.add(new Transaction(10L, "EATING OUT", LocalDate.of(2016, 3, 21), "E", new BigDecimal(60.22)));
		transactions.add(new Transaction(11L, "HOUSEHOLD", LocalDate.of(2016, 3, 27), "E", new BigDecimal(11.00)));
		transactions.add(new Transaction(12L, "EATING OUT", LocalDate.of(2016, 3, 7), "E", new BigDecimal(8.54)));
		transactions.add(new Transaction(13L, "EATING OUT", LocalDate.of(2016, 3, 26), "E", new BigDecimal(53.05)));
		transactions.add(new Transaction(14L, "PAYCHECK", LocalDate.of(2016, 3, 30), "I", new BigDecimal(2050.00)));
		return transactions;
	}

}
