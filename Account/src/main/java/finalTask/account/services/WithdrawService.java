package finalTask.account.services;

import finalTask.account.exceptions.InsufficientFundsException;
import finalTask.account.models.Account;
import finalTask.account.models.Withdraw;
import finalTask.account.models.dto.AccountDto;
import finalTask.account.models.dto.DepositDto;
import finalTask.account.models.dto.WithdrawDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface WithdrawService {

    void withdrawAmount(String accountNumber, BigDecimal amount) throws InsufficientFundsException;

    void withdraw(AccountDto accountDto, BigDecimal amount, LocalDateTime daytimeOfWithdraw) throws InsufficientFundsException;

    void saveOperation(Withdraw withdraw);
}
