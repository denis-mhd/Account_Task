package models;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account{

    private String accountNumber;
    private LocalDateTime dateOfOpening;
    private double interestRate;
    private BigDecimal balance;
    private AccountType accountType;
    private HistoryOfTransactions historyOfTransactions;

    public Account(){
    }

    public Account(String accountNumber, LocalDateTime dateOfOpening,
                   double interestRate, BigDecimal balance,
                   AccountType accountType,
                   HistoryOfTransactions historyOfTransactions) {
        this.accountNumber = accountNumber;
        this.dateOfOpening = dateOfOpening;
        this.interestRate = interestRate;
        this.balance = depositMoney(balance);
        this.accountType = accountType;
        this.historyOfTransactions = historyOfTransactions;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public LocalDateTime getDateOfOpening() {
        return dateOfOpening;
    }

    public void setDateOfOpening(LocalDateTime dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal amount) {
        this.balance = amount;
    }


    public AccountType getType() {
        return accountType;
    }

    public void setType(AccountType accountType) {
        this.accountType = accountType;
    }


    public HistoryOfTransactions getHistoryOfTransactions() {
        return historyOfTransactions;
    }

    public void setHistoryOfTransactions(HistoryOfTransactions historyOfTransactions) {
        this.historyOfTransactions = historyOfTransactions;
    }

    public BigDecimal depositMoney(BigDecimal amount){
        return this.balance == null ? amount : this.balance.add(amount);
    }

    public BigDecimal withdrawMoney(BigDecimal amount) {
        return this.balance == null ? amount : this.balance.subtract(amount);
    }
}
