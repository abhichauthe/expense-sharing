package com.expensesharing.util;

import com.expensesharing.model.Expense;
import java.util.Map;

public class ExpenseValidator {
    public static void validatePercentageSplit(Map<Long, Double> splits) {
        double totalPercentage = splits.values().stream().mapToDouble(Double::doubleValue).sum();
        if (Math.abs(totalPercentage - 100) > 0.01) {
            throw new IllegalArgumentException("Percentages must add up to 100%");
        }
    }

    public static void validateExpenseSplit(Expense expense, Map<Long, Double> splits) {
        if (expense.getSplitMethod() == Expense.SplitMethod.PERCENTAGE) {
            validatePercentageSplit(splits);
        }
        // Add more validation as needed
    }
}