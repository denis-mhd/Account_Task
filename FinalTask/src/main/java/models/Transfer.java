package models;

import java.math.BigDecimal;

public class Transfer{
    private Deposit deposit;
    private Withdraw withdraw;
    private BigDecimal amount;

    public Transfer(Deposit deposit, Withdraw withdraw, BigDecimal amount) {
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.amount = amount;
    }

    void saveTransfer(Deposit deposit, Withdraw withdraw, BigDecimal amount){
        this.deposit.saveTransaction(this.deposit.getAccount(), amount);
        this.withdraw.saveTransaction(this.withdraw.getAccount(), amount);
    }
}
