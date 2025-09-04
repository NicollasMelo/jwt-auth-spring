package com.authentication.jwt.repositories;

import com.authentication.jwt.entities.Expense;
import com.authentication.jwt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    List<Expense> findByUser(User user);
    List<Expense> findByUserAndCategory(User user, String category);
    Optional<Expense> findByIdAndUserId(int id, int userId);
    List<Expense> findAllByUserId(int id );
}
