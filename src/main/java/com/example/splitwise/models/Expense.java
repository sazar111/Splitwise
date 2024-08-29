package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private int amount;
    private String description;
    @ManyToOne
    private User createdBy;
    @OneToMany
    private List<ExpenseUser> expenseUsers;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
}
