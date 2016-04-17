package com.crimsonpig.finance.mockData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crimsonpig.finance.budget.*;
import com.crimsonpig.finance.summary.CategorizedAmount;
import com.crimsonpig.finance.summary.SummaryResponse;
import com.crimsonpig.finance.transaction.Transaction;

public class DomainObjectDataLists {

	public List<BudgetItem> getBudgetItems(){
		
		List<BudgetItem> items = new ArrayList<BudgetItem>();
		LocalDate start = LocalDate.of(2016, 3, 1);
		LocalDate midEnd = LocalDate.of(2016, 3, 15);
		LocalDate midStart = LocalDate.of(2016, 3, 16);
		LocalDate end = LocalDate.of(2016, 3, 31);
		
		items.add(new BudgetItem(1L, "GAS", new BigDecimal(200), "E", start, end));
		items.add(new BudgetItem(2L, "HOUSEHOLD", new BigDecimal(200), "E", start, end));
		items.add(new BudgetItem(3L, "FOOD", new BigDecimal(350), "E", start, end));
		items.add(new BudgetItem(4L, "PAYCHECK", new BigDecimal(2050), "I", start, midEnd));
		items.add(new BudgetItem(5L, "PAYCHECK", new BigDecimal(2050), "I", midStart, end));
		
		return items;
	}
	
	public List<CategorizedAmount> getExpenseSummary(){
		List<CategorizedAmount> expenses = new ArrayList<CategorizedAmount>();
		
		expenses.add(new CategorizedAmount("GAS", new BigDecimal(158.89)));
		expenses.add(new CategorizedAmount("HOUSEHOLD", new BigDecimal(213.44)));
		expenses.add(new CategorizedAmount("EATING OUT", new BigDecimal(289.36)));
		
		return expenses;
	}
	
	public List<CategorizedAmount> getIncomesSummary(){
		List<CategorizedAmount> incomes = new ArrayList<CategorizedAmount>();
		incomes.add(new CategorizedAmount("PAYCHECK", new BigDecimal(2050)));
		return incomes;
	}
	
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

	public List<CategorizedAmount> getExpenseItems() {
		List<CategorizedAmount> items = new ArrayList<CategorizedAmount>();
		items.add(new CategorizedAmount("GAS", new BigDecimal(200)));
		items.add(new CategorizedAmount("HOUSEHOLD", new BigDecimal(200)));
		items.add(new CategorizedAmount("FOOD", new BigDecimal(350)));
		return items;
	}

	public List<CategorizedAmount> getIncomeItems() {
		List<CategorizedAmount> items = new ArrayList<CategorizedAmount>();
		items.add(new CategorizedAmount("PAYCHECK", new BigDecimal(4100)));
		return items;
	}

	public SummaryResponse getTransactionSummary() {
		SummaryResponse response = new SummaryResponse();
		response.setExpenses(getExpenseSummary());
		response.setIncomes(getIncomesSummary());
		return response;
	}
	
}
