package com.example.splitwise.dto;

import com.example.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDto {
    List<Expense> suggestedExpenses;
    ResponseStatus responseStatus;
}
