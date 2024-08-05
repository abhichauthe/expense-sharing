package com.expensesharing.service;

import com.expensesharing.model.Expense;
import com.expensesharing.model.ExpenseSplit;
import com.expensesharing.model.User;
import com.expensesharing.repository.ExpenseRepository;
import com.expensesharing.repository.ExpenseSplitRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseSplitRepository expenseSplitRepository;

    @Transactional
    public Expense createExpense(Expense expense, Map<Long, Double> splits) {
        expense = expenseRepository.save(expense);

        for (Map.Entry<Long, Double> entry : splits.entrySet()) {
            ExpenseSplit split = new ExpenseSplit();
            split.setExpense(expense);
            split.setUser(new User());
            split.getUser().setId(entry.getKey());

            if (expense.getSplitMethod() == Expense.SplitMethod.EQUAL) {
                split.setAmount(expense.getAmount() / splits.size());
            } else if (expense.getSplitMethod() == Expense.SplitMethod.EXACT) {
                split.setAmount(entry.getValue());
            } else if (expense.getSplitMethod() == Expense.SplitMethod.PERCENTAGE) {
                split.setPercentage(entry.getValue());
                split.setAmount(expense.getAmount() * entry.getValue() / 100);
            }

            expenseSplitRepository.save(split);
        }

        return expense;
    }

    public List<ExpenseSplit> getUserExpenses(Long userId) {
        return expenseSplitRepository.findByUserId(userId);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
