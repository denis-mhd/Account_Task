package finalTask.account.services;

import finalTask.account.models.Account;
import finalTask.account.models.AccountType;
import finalTask.account.models.Deposit;
import finalTask.account.models.HistoryOfTransactions;
import finalTask.account.models.JsonDto.AccountJsonDto;
import finalTask.account.models.dto.AccountDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    void createAccount(String accountNumber, LocalDateTime date, double interestRate, String accountType);

    void saveOperation(Account account);

    void save(Account account);

    AccountDto findAccountByNumber(String number);

    Account findAccountByNumberWithoutException(String number);

    boolean hasAvailableBalance(AccountDto accountDto, BigDecimal amount);

    void accrueInterest(AccountDto accountDto);

    boolean isDepositAccount(AccountDto accountDto);

    boolean isDepositAccountIsOnTermDate(AccountDto accountDto);

    List<AccountDto> getAllAccounts();

    int getCountOfRepository();
}
