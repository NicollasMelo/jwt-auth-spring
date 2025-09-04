package com.authentication.jwt.services;

import com.authentication.jwt.DTOS.ExpenseDto;
import com.authentication.jwt.entities.Expense;
import com.authentication.jwt.entities.User;
import com.authentication.jwt.repositories.ExpenseRepository;
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

    public Expense save(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());
        expense.setCategory(expenseDto.getCategory());
        expense.setUser(getLoggedUser());
        return expenseRepository.save(expense);
    }

    public Expense update(int id, ExpenseDto expenseDto) {
        Expense expense = findByIdForLoggedUser(id);
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());
        expense.setCategory(expenseDto.getCategory());
        return expenseRepository.save(expense);
    }

    public void delete(int id) {
        Expense expense = findByIdForLoggedUser(id);
        expenseRepository.delete(expense);
    }

    public List<Expense> findAllByLoggedUser() {
        User loggedUser = getLoggedUser();
        return expenseRepository.findAllByUserId(loggedUser.getId());
    }

    public Expense findByIdForLoggedUser(int id) {
        User loggedUser = getLoggedUser();
        return expenseRepository.findByIdAndUserId(id, loggedUser.getId())
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada ou não pertence ao usuário"));
    }

    public BigDecimal getTotalExpensesForLoggedUser() {
        List<Expense> expenses = findAllByLoggedUser();
        return expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return (User) userDetails;
        }
        throw new RuntimeException("Usuário não logado");
    }
}
