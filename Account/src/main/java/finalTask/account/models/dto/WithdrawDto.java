package finalTask.account.models.dto;
import finalTask.account.models.HistoryOfTransactions;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawDto extends BaseEntityDto{
    private AccountDto accountDto;
    private BigDecimal amount;
    private LocalDateTime dateOfTransaction;
    private HistoryOfTransactions historyOfTransactions;

    public WithdrawDto(AccountDto accountDto, BigDecimal amount, LocalDateTime dateOfTransaction, HistoryOfTransactions historyOfTransactions) {
        this.accountDto = accountDto;
        this.amount = amount;
        this.setDateOfTransaction(dateOfTransaction);
        this.historyOfTransactions = historyOfTransactions;
    }

    @NotBlank
    public AccountDto getAccountDto() {
        return accountDto;
    }

    public void setAccountDto(AccountDto accountDto) {
        this.accountDto = accountDto;
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
