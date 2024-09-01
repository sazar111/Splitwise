package com.example.splitwise.stratergy;

import com.example.splitwise.models.Expense;

import java.util.List;

public interface SettleUpStratergy {
    List<Expense> settleUp(List<Expense> expenses);
}
