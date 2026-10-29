package service;

import annotation.Audit;
import exception.*;
import model.Account;
import repository.Repository;
import util.StatePersister;
import util.TransactionLog;

import java.io.IOException;
import java.nio.file.*;

public class BankService {
    private final Repository<Account> repository;
    private final Path dataFile;
    private final Path logFile;

    public BankService(Repository<Account> repository, Path dataFile, Path logFile) {
        this.repository = repository;
        this.dataFile = dataFile;
        this.logFile = logFile;
    }

    @Audit
    public Account openAccount(String number, String owner, long initial)
            throws InvalidAmountException {
        if (repository.findById(number) != null)
            throw new InvalidAmountException("Account already exists.");
        if (initial < 0) throw new InvalidAmountException("Initial balance cannot be negative.");
        Account a = new Account(number, owner, initial);
        repository.save(a);
        return a;
    }

    @Audit
    public void deposit(String number, long amount)
            throws AccountNotFoundException, InvalidAmountException {
        Account a = require(number);
        a.deposit(amount);
        log("DEPOSIT", number, amount);
    }

    @Audit
    public void withdraw(String number, long amount)
            throws AccountNotFoundException, InsufficientFundsException, InvalidAmountException {
        Account a = require(number);
        a.withdraw(amount);
        log("WITHDRAW", number, amount);
    }

    @Audit
    public void transfer(String from, String to, long amount)
            throws BankException {
        if (from.equals(to)) throw new InvalidAmountException("Accounts must be different.");
        Account a = require(from);
        Account b = require(to);
        Account first = from.compareTo(to) < 0 ? a : b;
        Account second = from.compareTo(to) < 0 ? b : a;
        synchronized (first) {
            synchronized (second) {
                a.withdraw(amount);
                b.deposit(amount);
            }
        }
        log("WITHDRAW", from, amount);
        log("DEPOSIT", to, amount);
    }

    public String statement(String number) throws AccountNotFoundException {
        return require(number).toString();
    }

    public java.util.ArrayList<Account> listAccounts() {
        return repository.findAll();
    }

    public void save() throws IOException {
        StatePersister.save(repository.findAll().toArray(new Account[0]), dataFile);
    }

    public void load() throws IOException, ClassNotFoundException {
        for (Account a : StatePersister.load(dataFile)) repository.save(a);
    }

    private Account require(String number) throws AccountNotFoundException {
        Account a = repository.findById(number);
        if (a == null) throw new AccountNotFoundException("Account not found: " + number);
        return a;
    }

    private void log(String type, String number, long amount) {
        try { TransactionLog.append(logFile, type + " " + number + " " + amount); }
        catch (IOException e) { System.out.println("Log error: " + e.getMessage()); }
    }
}