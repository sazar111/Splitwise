package com.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="SplitwiseGroup")
public class Group extends BaseModel{
    @ManyToOne
    private User createdBy;
    private String name;
    @ManyToMany
    private List<User> members;
    @OneToMany
    private List<Expense> expenses;
}
