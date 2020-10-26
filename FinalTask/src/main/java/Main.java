import models.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Account account = new Account();
        account.setAccountNumber("1234");
        account.setBalance(BigDecimal.valueOf(100.50));
        account.setDateOfOpening(LocalDateTime.now());
        account.setType(AccountType.deposit);
        account.setInterestRate(3);

        HistoryOfTransactions historyOfTransactions = new HistoryOfTransactions(account);

        account.setHistoryOfTransactions(historyOfTransactions);

        Transaction transaction = new Deposit(account,BigDecimal.valueOf(200));
        Transaction transaction1 = new Withdraw(account, BigDecimal.valueOf(50));
        account.getHistoryOfTransactions().getTransaction().add(transaction);
        account.getHistoryOfTransactions().getTransaction().add(transaction1);
        transaction.saveTransaction(account, BigDecimal.valueOf(200));
        transaction1.saveTransaction(account,BigDecimal.valueOf(50));

        System.out.println(account.getAccountNumber() + " " + account.getBalance() + " " + account.getInterestRate() + " "
        + " " + account.getHistoryOfTransactions().getTransaction().get(0).getLocalDateTime());
    }
}
