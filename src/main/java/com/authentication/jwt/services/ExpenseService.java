package com.authentication.jwt.services;

import com.authentication.jwt.DTOS.ExpenseDto;
import com.authentication.jwt.entities.Expense;
import com.authentication.jwt.entities.User;
import com.authentication.jwt.repositories.ExpenseRepository;
import com.authentication.jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Expense findById(int id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
    }

    public Expense save(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());
        expense.setCategory(expenseDto.getCategory());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User loggedUser = (User) userDetails;
            expense.setUser(loggedUser);
        }
        return expenseRepository.save(expense);
    }

    public Expense update(int id, ExpenseDto expenseDto) {
        Expense expense = findById(id);
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());
        expense.setCategory(expenseDto.getCategory());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User loggedUser = (User) userDetails;
            expense.setUser(loggedUser);
        }

        return expenseRepository.save(expense);
    }

    public void delete(int id) {
        expenseRepository.deleteById(id);
    }
    public BigDecimal getTotalExpensesByUser(int id ) {
        List<Expense> expenses = expenseRepository.findAllByUserId(id);
        if (expenses.isEmpty()) return BigDecimal.ZERO;
        return expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
