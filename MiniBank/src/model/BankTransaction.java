package model;

import java.io.Serializable;

public record BankTransaction(String type, String accountNumber, long amount) implements Serializable {}