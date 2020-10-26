package models;

import java.math.BigDecimal;


public class Deposit extends Transaction{

    public Deposit(Account account, BigDecimal amount) {
        super(account, amount);
    }

    @Override
    public void saveTransaction(Account account, BigDecimal amount) {
        if (account.getType().equals(AccountType.deposit)){
            if (checkTermDate()){
                account.setBalance(account.depositMoney(amount));
            }
        }else {
            account.setBalance(account.depositMoney(amount));
        }

    }


}
