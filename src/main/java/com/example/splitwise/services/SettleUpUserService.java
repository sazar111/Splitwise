package com.example.splitwise.services;

import com.example.splitwise.exceptions.UserNotFoundExeption;
import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseUser;
import com.example.splitwise.models.User;
import com.example.splitwise.repositories.ExpenseUserRepository;
import com.example.splitwise.repositories.UserRepository;
import com.example.splitwise.stratergy.SettleUpStratergy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SettleUpUserService {
    UserRepository  userRepository;
    ExpenseUserRepository expenseUserRepository;
    SettleUpStratergy settleUpStratergy;

    SettleUpUserService(UserRepository userRepository,ExpenseUserRepository expenseUserRepository,SettleUpStratergy settleUpStratergy) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.settleUpStratergy = settleUpStratergy;
    }

    public List<Expense> settleUpUser(Long userId) throws UserNotFoundExeption {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundExeption();
        }
        List<ExpenseUser> expenses= expenseUserRepository.findByUser(userOptional.get());
        Set<Expense> expenseToSettle = new HashSet<>();
        for(ExpenseUser expenseUser : expenses){
            expenseToSettle.add(expenseUser.getExpense());
        }

        List<Expense> suggestedExpense= settleUpStratergy.settleUp((List<Expense>) expenseToSettle);
        return suggestedExpense;
    }
}
