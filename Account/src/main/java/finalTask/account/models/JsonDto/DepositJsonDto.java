package finalTask.account.models.JsonDto;

import com.google.gson.annotations.Expose;
import finalTask.account.models.HistoryOfTransactions;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositJsonDto {
    private AccountJsonDto account;
    @Expose
    private BigDecimal amount;
    @Expose
    private String dateOfTransaction;
    private HistoryOfTransactionsJsonDto historyOfTransactions;

    public DepositJsonDto(){
    }

    public DepositJsonDto(AccountJsonDto account, BigDecimal amount, String dateOfTransaction, HistoryOfTransactionsJsonDto historyOfTransactions) {
        this.account = account;
        this.amount = amount;
        this.dateOfTransaction = dateOfTransaction;
        this.historyOfTransactions = historyOfTransactions;
    }

    public AccountJsonDto getAccount() {
        return account;
    }

    public void setAccount(AccountJsonDto account) {
        this.account = account;
    }

    @NotNull
    @DecimalMin("0.00")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public HistoryOfTransactionsJsonDto getHistoryOfTransactions() {
        return historyOfTransactions;
    }

    public void setHistoryOfTransactions(HistoryOfTransactionsJsonDto historyOfTransactions) {
        this.historyOfTransactions = historyOfTransactions;
    }
}
