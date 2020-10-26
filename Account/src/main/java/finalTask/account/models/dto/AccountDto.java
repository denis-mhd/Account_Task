package finalTask.account.models.dto;

import finalTask.account.models.AccountType;
import finalTask.account.models.HistoryOfTransactions;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class AccountDto extends BaseEntityDto{

    private String accountNumber;
    private LocalDateTime dateOfOpening;
    private double interestRate;
    private BigDecimal balance;
    private AccountType accountType;
    private HistoryOfTransactions historyOfTransactions;

    public AccountDto(){
    }

    public AccountDto(String accountNumber, LocalDateTime dateOfOpening,
                      double interestRate, AccountType accountType, HistoryOfTransactions historyOfTransactions) {
        this.accountNumber = accountNumber;
        setDateOfOpening(dateOfOpening);
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.historyOfTransactions = historyOfTransactions;
    }

    @NotNull
    @Length(min = 4, max = 4)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public LocalDateTime getDateOfOpening() {
        return dateOfOpening;
    }

    public void setDateOfOpening(LocalDateTime dateOfOpening) {
        this.dateOfOpening = dateOfOpening != null ? dateOfOpening : LocalDateTime.now();
    }

    @NotNull
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @NotNull
    @DecimalMin("0.00")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance == null ? BigDecimal.valueOf(0) : balance;
    }


    @NotNull
    public AccountType getType() {
        return accountType;
    }

    public void setType(AccountType accountType) {
        this.accountType = accountType;
    }


    public HistoryOfTransactions getHistoryOfTransactions() {
        return historyOfTransactions;
    }

    public void setHistoryOfTransactions(HistoryOfTransactions historyOfTransactions) {
        this.historyOfTransactions = historyOfTransactions;
    }

}
