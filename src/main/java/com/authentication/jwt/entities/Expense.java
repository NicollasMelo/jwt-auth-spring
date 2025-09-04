package com.authentication.jwt.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "expense")
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private BigDecimal amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Expense() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
