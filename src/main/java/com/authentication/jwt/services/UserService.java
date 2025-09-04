package com.authentication.jwt.services;

import com.authentication.jwt.DTOS.ExpenseDto;
import com.authentication.jwt.entities.Expense;
import com.authentication.jwt.entities.User;
import com.authentication.jwt.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User updateSalary(Integer userId, Long newSalary) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User loggedUser = (User) userDetails;
            if (!user.getId().equals(loggedUser.getId())) {
                throw new RuntimeException("Você não pode alterar o salário de outro usuário");
            }

            user.setExpiresIn(newSalary);
        }

        return userRepository.save(user);
    }






    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }
}
