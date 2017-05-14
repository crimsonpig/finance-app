package com.crimsonpig.finance.mockData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crimsonpig.finance.domain.BudgetItem;
import com.crimsonpig.finance.domain.CategorizedAmount;
import com.crimsonpig.finance.domain.SummaryResponse;

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
	
	public List<CategorizedAmount> getIncomeItemsSummary(){
		List<CategorizedAmount> incomeItems = new ArrayList<CategorizedAmount>();
		incomeItems.add(new CategorizedAmount("PAYCHECK", new BigDecimal(4100)));
		return incomeItems;
	}
	
	public List<CategorizedAmount> getExpenseItemsSummary(){
		List<CategorizedAmount> expenseItems = new ArrayList<CategorizedAmount>();
		expenseItems.add(new CategorizedAmount("GAS", new BigDecimal(200)));
		expenseItems.add(new CategorizedAmount("FOOD", new BigDecimal(350)));
		expenseItems.add(new CategorizedAmount("HOUSEHOLD", new BigDecimal(200)));
		return expenseItems;
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
	
	public SummaryResponse getBudgetSummary(){
		SummaryResponse response = new SummaryResponse();
		response.setExpenses(getExpenseItemsSummary());
		response.setIncomes(getIncomeItemsSummary());
		return response;		
	}
	
}
