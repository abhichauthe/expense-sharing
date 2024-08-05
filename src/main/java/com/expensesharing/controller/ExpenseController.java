package com.expensesharing.controller;

import com.expensesharing.model.Expense;
import com.expensesharing.model.ExpenseSplit;
import com.expensesharing.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense,
                                                 @RequestParam Map<Long, Double> splits) {
        return ResponseEntity.ok(expenseService.createExpense(expense, splits));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseSplit>> getUserExpenses(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseService.getUserExpenses(userId));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/balance-sheet")
    public ResponseEntity<String> downloadBalanceSheet() {
        // Implement balance sheet generation and download
        return ResponseEntity.ok("Balance sheet download not implemented yet");
    }
}
