package com.crimsonpig.finance.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import com.crimsonpig.finance.domain.BudgetItem;

public class BudgetItemAmountService {

	public BudgetItem computeRelativeAmountByDateRange(BudgetItem item, LocalDate dateRangeStart, LocalDate dateRangeEnd) {
		BudgetItem output = item;
		BigDecimal totalAmount = item.getAmount();
		
		if(!BigDecimal.ZERO.equals(totalAmount)){
			Period budgetItemPeriod = Period.between(item.getStartDate(), item.getEndDate());
			Period dateRangePeriod = Period.between(dateRangeStart, dateRangeEnd);
	
			//Same day would make this zero, but we need to include that day and so we have one.
			Integer budgetItemDays = budgetItemPeriod.getDays() + 1;
			Integer dateRangeDays = dateRangePeriod.getDays() + 1;
			
			double dateRangeRatio = dateRangeDays.doubleValue() / budgetItemDays.doubleValue();
			
			BigDecimal fractionalPercentage = new BigDecimal(dateRangeRatio).setScale(16, BigDecimal.ROUND_HALF_UP);
			BigDecimal amountInDateRange = totalAmount.multiply(fractionalPercentage).setScale(2, BigDecimal.ROUND_HALF_UP);
			output.setAmount(amountInDateRange);
		}
		return output;
	}

	BigDecimal calculateAmountForDateRange(Integer budgetItemDays, Integer dateRangeDays,
			BigDecimal totalAmount) {
		double dateRangeRatio = dateRangeDays.doubleValue() / budgetItemDays.doubleValue();
		
		BigDecimal fractionalPercentage = new BigDecimal(dateRangeRatio).setScale(16, BigDecimal.ROUND_HALF_UP);
		BigDecimal amountInDateRange = totalAmount.multiply(fractionalPercentage).setScale(2, BigDecimal.ROUND_HALF_UP);
		return amountInDateRange;
	}

	Integer calculateBudgetDays(LocalDate startDate, LocalDate endDate) {
		Long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		int days = daysBetween.intValue() + 1;
		return days;
	}

}
