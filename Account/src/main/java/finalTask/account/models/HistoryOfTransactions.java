package finalTask.account.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "history_of_transactions")
public class HistoryOfTransactions extends BaseEntity{

    private Account account;
    @Expose
    private Set<Deposit> deposits;
    @Expose
    private Set<Withdraw> withdraws;

    public HistoryOfTransactions() {
    }

    public HistoryOfTransactions(Account account) {
        this.account = account;
        this.deposits = new HashSet<>();
        this.withdraws = new HashSet<>();
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @OneToMany(mappedBy = "historyOfTransactions",fetch = FetchType.EAGER)
    @Column(name = "deposits")
    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        this.deposits = deposits;
    }

    @OneToMany(mappedBy = "historyOfTransactions",fetch = FetchType.EAGER)
    @Column(name = "withdraws")
    public Set<Withdraw> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(Set<Withdraw> withdraws) {
        this.withdraws = withdraws;
    }

}
