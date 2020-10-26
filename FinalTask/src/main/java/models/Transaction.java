package models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Transaction {
    private final Account account;
    private final BigDecimal amount;
    private final LocalDateTime localDateTime;

    protected Transaction(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
        this.localDateTime = LocalDateTime.now();
    }


    public abstract void saveTransaction(Account account, BigDecimal amount);

    public boolean checkTermDate(){
        int dayOfOpeningAccount = this.account.getDateOfOpening().getDayOfMonth();
        int dayOfTransaction = LocalDateTime.now().getDayOfMonth();
        return dayOfOpeningAccount == dayOfTransaction;
    }

    public Account getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
