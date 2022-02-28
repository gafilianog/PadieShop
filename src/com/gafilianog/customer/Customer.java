package com.gafilianog.customer;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer {

    private final String userName;
    private final String fullName;
    private final String email;
    private final String password;
    private int balance;
//    private ArrayList<Integer> receiptId = new ArrayList<>();
    private final HashMap<Integer, ArrayList<Integer>> receiptLists = new HashMap<>();

    public Customer(String userName, String fullName, String email, String password) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.balance = 1000;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public HashMap<Integer, ArrayList<Integer>> getReceiptLists() {
        return receiptLists;
    }

    public void setReceiptLists(int receiptId, ArrayList<Integer> prodIdx) {
        this.receiptLists.put(receiptId, prodIdx);
    }
}
