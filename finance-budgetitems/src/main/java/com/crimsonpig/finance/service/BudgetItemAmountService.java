package com.crimsonpig.finance.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.crimsonpig.finance.domain.BudgetItem;

public class BudgetItemAmountService {

	public BudgetItem computeRelativeAmountByDateRange(BudgetItem item, LocalDate dateRangeStart, LocalDate dateRangeEnd) {
		BudgetItem output = item;
		BigDecimal totalAmount = item.getAmount();
		
		Integer budgetItemDays = calculateBudgetDays(item.getStartDate(), item.getEndDate());
		Integer dateRangeDays = calculateBudgetDays(dateRangeStart, dateRangeEnd);
		
		BigDecimal amountInDateRange = calculateAmountForDateRange(budgetItemDays, dateRangeDays, totalAmount);
		output.setAmount(amountInDateRange);
		
		return output;
	}

	BigDecimal calculateAmountForDateRange(Integer budgetItemDays, Integer dateRangeDays,
			BigDecimal totalAmount) {
		BigDecimal amountInDateRange = totalAmount;
		
		double dateRangeRatio = dateRangeDays.doubleValue() / budgetItemDays.doubleValue();
		if(dateRangeRatio < 1.0){
			BigDecimal fractionalPercentage = new BigDecimal(dateRangeRatio).setScale(16, BigDecimal.ROUND_HALF_UP);
			amountInDateRange = totalAmount.multiply(fractionalPercentage).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return amountInDateRange;
	}

	Integer calculateBudgetDays(LocalDate startDate, LocalDate endDate) {
		Long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		int days = daysBetween.intValue() + 1;
		return days;
	}

}
