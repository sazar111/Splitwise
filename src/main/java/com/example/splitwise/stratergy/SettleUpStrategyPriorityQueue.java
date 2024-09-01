package com.example.splitwise.stratergy;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseUser;
import com.example.splitwise.models.ExpenseUserType;
import com.example.splitwise.models.User;

import java.util.*;

public class SettleUpStrategyPriorityQueue implements SettleUpStratergy{
    @Override
    public List<Expense> settleUp(List<Expense> expenses) {
        HashMap<User,Integer> netUserBalance = calculateUserBalance(expenses);
        List<Expense> settlementExpenses = settleUpUserExpenses(netUserBalance);
        return settlementExpenses;
    }

    private List<Expense> settleUpUserExpenses(HashMap<User,Integer> netUserBalance) {
        List<Expense> suggestedExpenses = new ArrayList<>();
        PriorityQueue<Map.Entry<User,Integer>> debts = new PriorityQueue<>((a, b)-> b.getValue()- a.getValue());
        PriorityQueue<Map.Entry<User,Integer>> creds = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for(User user : netUserBalance.keySet()) {
            if(netUserBalance.get(user)<0) {
              debts.add(Map.entry(user,0));
            }else if(netUserBalance.get(user)>0) {
                creds.add(Map.entry(user,0));
            }
        }

        while(!debts.isEmpty() && !creds.isEmpty()){
            Map.Entry<User,Integer> debt = debts.poll();
            Map.Entry<User,Integer> cred = creds.poll();

            int settleAmount = Math.min(debt.getValue(), cred.getValue());
            debt.setValue(debt.getValue()-settleAmount);
            cred.setValue(cred.getValue()-settleAmount);

            Expense expense= new Expense();
            ExpenseUser debtUser= new ExpenseUser();
            debtUser.setExpenseUserType(ExpenseUserType.DEBT);
            debtUser.setExpense(expense);
            debtUser.setUser(debt.getKey());

            ExpenseUser creditUser= new ExpenseUser();
            creditUser.setExpenseUserType(ExpenseUserType.CREDIT);
            creditUser.setExpense(expense);
            creditUser.setUser(cred.getKey());

            List<ExpenseUser> expenseUsers = new ArrayList<>();
            expenseUsers.add(debtUser);
            expenseUsers.add(creditUser);

            expense.setExpenseUsers(expenseUsers);
            suggestedExpenses.add(expense);
        }
        return suggestedExpenses;
    }

    private HashMap<User, Integer> calculateUserBalance(List<Expense> expenses) {
        HashMap<User,Integer> netUserBalance = new HashMap<>();

        for (Expense expense : expenses) {
            for(ExpenseUser expenseUser: expense.getExpenseUsers()) {
                if(expenseUser.getExpenseUserType() == ExpenseUserType.DEBT){
                    netUserBalance.put(expenseUser.getUser(),netUserBalance.get(expenseUser.getUser()) - expenseUser.getAmount());
                } else if (expenseUser.getExpenseUserType() == ExpenseUserType.CREDIT) {
                    netUserBalance.put(expenseUser.getUser(),netUserBalance.get(expenseUser.getUser()) + expenseUser.getAmount());
                }
            }
        }
        return netUserBalance;
    }

}
