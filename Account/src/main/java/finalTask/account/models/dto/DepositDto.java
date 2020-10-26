package finalTask.account.models.dto;

import finalTask.account.models.HistoryOfTransactions;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositDto extends BaseEntityDto{
    private AccountDto account;
    private BigDecimal amount;
    private LocalDateTime dateOfTransaction;
    private HistoryOfTransactions historyOfTransactions;

    public DepositDto(AccountDto account, BigDecimal amount, LocalDateTime dateOfTransaction, HistoryOfTransactions historyOfTransactions) {
        this.account = account;
        this.amount = amount;
        this.setDateOfTransaction(dateOfTransaction);
        this.historyOfTransactions = historyOfTransactions;
    }

    @NotBlank
    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
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

    @NotBlank
    public LocalDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction != null ? dateOfTransaction : LocalDateTime.now();
    }

    public HistoryOfTransactions getHistoryOfTransactions() {
        return historyOfTransactions;
    }

    public void setHistoryOfTransactions(HistoryOfTransactions historyOfTransactions) {
        this.historyOfTransactions = historyOfTransactions;
    }
}
