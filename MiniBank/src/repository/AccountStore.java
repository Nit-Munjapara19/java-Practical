package repository;

import model.Account;
import java.util.*;

public class AccountStore {
    private final HashMap<String, Account> accounts = new HashMap<>();
    private final ArrayList<String> history = new ArrayList<>();

    public synchronized void add(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public synchronized Account get(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public synchronized ArrayList<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    public synchronized ArrayList<Account> sortedByBalance() {
        ArrayList<Account> list = getAll();
        list.sort(Comparator.comparingLong(Account::getBalance).reversed());
        return list;
    }

    public synchronized void addHistory(String entry) { history.add(entry); }
    public synchronized ArrayList<String> getHistory() { return new ArrayList<>(history); }
}