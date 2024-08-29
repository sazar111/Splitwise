package com.example.splitwise.services;

import com.example.splitwise.exceptions.UserNotFoundExeption;
import com.example.splitwise.models.Expense;
import com.example.splitwise.models.User;
import com.example.splitwise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettleUpUserService {
    UserRepository  userRepository;

    SettleUpUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Expense> settleUpUser(Long userId) throws UserNotFoundExeption {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundExeption();
        }

    }
}
