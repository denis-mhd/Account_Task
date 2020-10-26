package finalTask.account.models;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{

    private String accountNumber;
    private LocalDateTime dateOfOpening;
    private double interestRate;
    private BigDecimal balance;
    private AccountType accountType;
    private HistoryOfTransactions historyOfTransactions;


    public Account(){
    }

    public Account(String accountNumber, LocalDateTime dateOfOpening,
                   double interestRate, AccountType accountType) {
        this.accountNumber = accountNumber;
        setDateOfOpening(dateOfOpening);
        this.interestRate = interestRate;
        this.accountType = accountType;

    }

    @Column(name = "account_number", unique = true, nullable = false)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "date_of_opening")
    public LocalDateTime getDateOfOpening() {
        return dateOfOpening;
    }

    public void setDateOfOpening(LocalDateTime dateOfOpening) {
        this.dateOfOpening = dateOfOpening != null ? dateOfOpening : LocalDateTime.now();
    }

    @Column(name = "interest_rate", nullable = false)
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Column(name = "balance", nullable = false)
    @DecimalMin("0.00")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance == null ? BigDecimal.valueOf(0) : balance;
    }

    @Enumerated(value = EnumType.STRING)
    public AccountType getType() {
        return accountType;
    }

    public void setType(AccountType accountType) {
        this.accountType = accountType;
    }

    @OneToOne
    public HistoryOfTransactions getHistoryOfTransactions() {
        return historyOfTransactions;
    }

    public void setHistoryOfTransactions(HistoryOfTransactions historyOfTransactions) {
        this.historyOfTransactions = historyOfTransactions;
    }

}
