package com.authentication.jwt.controllers;

import com.authentication.jwt.DTOS.ExpenseDto;
import com.authentication.jwt.ExpenseControl;
import com.authentication.jwt.entities.Expense;
import com.authentication.jwt.entities.User;
import com.authentication.jwt.services.ExpenseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> findAll() {
        List<Expense> expenses = expenseService.findAll();
        return ResponseEntity.ok(expenses);
    }

    @PostMapping
    public ResponseEntity<Expense> save(@RequestBody ExpenseDto expenseDto) {
        Expense saveExpense = expenseService.save(expenseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveExpense);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable int id, @RequestBody ExpenseDto updateExpense) {
        Expense expense = expenseService.update(id, updateExpense);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total/{id}")
    public BigDecimal getTotal(@PathVariable int id ) {
        return expenseService.getTotalExpensesByUser(id);
    }

}
