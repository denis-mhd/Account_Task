package models;

import java.math.BigDecimal;

public class Withdraw extends Transaction{

    public Withdraw(Account account, BigDecimal amount) {
        super(account, amount);
    }

    @Override
    public void saveTransaction(Account account, BigDecimal amount) {
        if (account.getType().equals(AccountType.deposit)){
            if (checkTermDate()){
                account.setBalance(account.withdrawMoney(amount));
            }
        }else {
            account.setBalance(account.depositMoney(amount));
        }

    }
}
