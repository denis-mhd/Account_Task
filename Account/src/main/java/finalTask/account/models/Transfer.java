package finalTask.account.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
public class Transfer extends BaseEntity{
    private Account senderAccount;
    private Account recipientAccount;
    private BigDecimal amount;
    private LocalDateTime dateOfTransaction;

    public Transfer(Account senderAccount, Account recipientAccount, BigDecimal amount,
                    LocalDateTime dateOfTransaction) {
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
        this.amount = amount;
        setLocalDateTime(dateOfTransaction);
    }

    public Transfer() {
    }

    @ManyToOne
    public Account getAccount() {
        return senderAccount;
    }

    public void setAccount(Account account) {
        this.senderAccount = account;
    }

    @ManyToOne
    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    public LocalDateTime getLocalDateTime() {
        return dateOfTransaction;
    }

    public void setLocalDateTime(LocalDateTime localDate) {
        this.dateOfTransaction = localDate;
        if (this.dateOfTransaction == null){
            this.dateOfTransaction= LocalDateTime.now();
        }
    }
}
