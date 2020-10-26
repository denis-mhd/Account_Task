package finalTask.account.models.JsonDto;

import com.google.gson.annotations.Expose;
import finalTask.account.models.Deposit;
import finalTask.account.models.dto.BaseEntityDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HistoryOfTransactionsJsonDto {

    private AccountJsonDto account;
    @Expose
    private Set<DepositJsonDto> deposits;
    @Expose
    private Set<WithdrawJsonDto> withdraws;

    public HistoryOfTransactionsJsonDto() {
    }

    public HistoryOfTransactionsJsonDto(AccountJsonDto account, Set<DepositJsonDto> deposits, Set<WithdrawJsonDto> withdraws) {
        this.account = account;
        this.deposits = deposits;
        this.withdraws = withdraws;
    }

    public AccountJsonDto getAccount() {
        return account;
    }

    public void setAccount(AccountJsonDto account) {
        this.account = account;
    }

    public Set<DepositJsonDto> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<DepositJsonDto> deposits) {
        this.deposits = deposits;
    }

    public Set<WithdrawJsonDto> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(Set<WithdrawJsonDto> withdraws) {
        this.withdraws = withdraws;
    }
}
