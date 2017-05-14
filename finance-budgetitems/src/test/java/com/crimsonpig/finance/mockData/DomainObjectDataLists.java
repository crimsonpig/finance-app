package com.crimsonpig.finance.mockData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crimsonpig.finance.domain.BudgetItem;

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

}
