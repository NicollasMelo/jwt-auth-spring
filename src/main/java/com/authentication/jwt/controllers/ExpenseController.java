package com.authentication.jwt.controllers;

import com.authentication.jwt.DTOS.ExpenseDto;
import com.authentication.jwt.entities.Expense;
import com.authentication.jwt.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpensesForLoggedUser() {
        List<Expense> expenses = expenseService.findAllByLoggedUser();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable int id) {
        Expense expense = expenseService.findByIdForLoggedUser(id);
        return ResponseEntity.ok(expense);
    }

    @PostMapping
    public ResponseEntity<Expense> save(@RequestBody ExpenseDto expenseDto) {
        Expense savedExpense = expenseService.save(expenseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable int id, @RequestBody ExpenseDto dto) {
        Expense updatedExpense = expenseService.update(id, dto);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalExpensesForLoggedUser() {
        BigDecimal total = expenseService.getTotalExpensesForLoggedUser();
        return ResponseEntity.ok(total);
    }
}
