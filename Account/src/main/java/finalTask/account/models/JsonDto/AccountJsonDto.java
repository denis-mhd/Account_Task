package finalTask.account.models.JsonDto;

import com.google.gson.annotations.Expose;
import finalTask.account.models.AccountType;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class AccountJsonDto {
    @Expose
    private String accountNumber;
    @Expose
    private String dateOfOpening;
    @Expose
    private double interestRate;
    @Expose
    private BigDecimal balance;
    @Expose
    private AccountType accountType;
    @Expose
    private HistoryOfTransactionsJsonDto historyOfTransactions;

    public AccountJsonDto(){
    }

    public AccountJsonDto(String accountNumber, String dateOfOpening,
                          double interestRate, AccountType accountType, HistoryOfTransactionsJsonDto historyOfTransactions) {
        this.accountNumber = accountNumber;
        this.dateOfOpening = dateOfOpening;
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

    @NotNull
    public String getDateOfOpening() {
        return dateOfOpening;
    }

    public void setDateOfOpening(String dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
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


    public HistoryOfTransactionsJsonDto getHistoryOfTransactions() {
        return historyOfTransactions;
    }

    public void setHistoryOfTransactions(HistoryOfTransactionsJsonDto historyOfTransactions) {
        this.historyOfTransactions = historyOfTransactions;
    }

}
