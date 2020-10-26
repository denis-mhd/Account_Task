package finalTask.account.models.JsonDto;

import com.google.gson.annotations.Expose;
import finalTask.account.models.dto.AccountDto;

import java.math.BigDecimal;


public class TransferJsonDto {
    @Expose
    private AccountDto senderAccount;
    @Expose
    private AccountDto recipientAccount;
    @Expose
    private BigDecimal amount;
    @Expose
    private String dateOfTransaction;

    public TransferJsonDto() {
    }

    public TransferJsonDto(AccountDto senderAccount, AccountDto recipientAccount, BigDecimal amount, String dateOfTransaction) {
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
        this.amount = amount;
        this.dateOfTransaction = dateOfTransaction;
    }

    public AccountDto getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(AccountDto senderAccount) {
        this.senderAccount = senderAccount;
    }

    public AccountDto getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(AccountDto recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

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
}
