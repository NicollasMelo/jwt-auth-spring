package com.authentication.jwt.controllers;

import com.authentication.jwt.entities.User;
import com.authentication.jwt.repositories.UserRepository;
import com.authentication.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateSalary(
            @PathVariable int id,
            @RequestBody Map<String, Long> payload
    ) {
        Long newSalary = payload.get("salary");
        if (newSalary == null) {
            return ResponseEntity.badRequest().build();
        }

        User updatedUser = userService.updateSalary(id, newSalary);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}/salary")
    public User updateSalary(@PathVariable Integer id, @RequestBody Map<String, BigDecimal> payload) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setSalary(payload.get("salary"));
        return userRepository.save(user);
    }

}

