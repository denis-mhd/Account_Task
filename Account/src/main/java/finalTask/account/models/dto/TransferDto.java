package finalTask.account.models.dto;

import finalTask.account.models.Account;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferDto {
    private Account senderAccount;
    private Account recipientAccount;
    private BigDecimal amount;
    private LocalDateTime dateOfTransaction;

    public TransferDto() {
    }

    public TransferDto(Account senderAccount, Account recipientAccount, BigDecimal amount, LocalDateTime dateOfTransaction) {
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
        this.amount = amount;
        setLocalDateTime(dateOfTransaction);
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
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
    public LocalDateTime getLocalDateTime() {
        return dateOfTransaction;
    }


    public void setLocalDateTime(LocalDateTime localDate) {
        this.dateOfTransaction = dateOfTransaction != null ? dateOfTransaction : LocalDateTime.now();
    }
}
