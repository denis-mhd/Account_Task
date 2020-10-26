package finalTask.account.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdraws")
public class Withdraw extends BaseEntity{
    private Account account;
    private BigDecimal amount;
    private LocalDateTime dateOfTransaction;
    private HistoryOfTransactions historyOfTransactions;

    public Withdraw() {
    }


    @OneToOne
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "date_of_transaction")
    public LocalDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
        if (this.dateOfTransaction == null){
            this.dateOfTransaction = LocalDateTime.now();
        }
    }

    @ManyToOne
    public HistoryOfTransactions getHistoryOfTransactions() {
        return historyOfTransactions;
    }

    public void setHistoryOfTransactions(HistoryOfTransactions historyOfTransactions) {
        this.historyOfTransactions = historyOfTransactions;
    }
}
