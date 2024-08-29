package com.example.splitwise.exceptions;

public class UserNotFoundExeption extends Exception{
    public UserNotFoundExeption() {
        super("User not found");
    }
}
