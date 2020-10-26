package models;

import java.util.ArrayList;
import java.util.List;

public class HistoryOfTransactions {

    private Account account;
    private List<Transaction> transaction;

    public HistoryOfTransactions() {
    }

    public HistoryOfTransactions(Account account) {
        this.account = account;
        this.transaction = new ArrayList<>();
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
