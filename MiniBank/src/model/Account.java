package model;

import annotation.Id;
import java.io.Serializable;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class Account implements Serializable, Comparable<Account> {
    private static final long serialVersionUID = 1L;

    @Id
    private String accountNumber;
    private String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public synchronized long getBalance() { return balance; }

    public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive.");
        balance += amount;
    }

    public synchronized void withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive.");
        if (amount > balance)
            throw new InsufficientFundsException(amount - balance);
        balance -= amount;
    }

    @Override
    public int compareTo(Account other) {
        return accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Account a && accountNumber.equals(a.accountNumber);
    }

    @Override
    public int hashCode() { return accountNumber.hashCode(); }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }
}