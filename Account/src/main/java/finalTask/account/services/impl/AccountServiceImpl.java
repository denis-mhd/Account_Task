package finalTask.account.services.impl;

import finalTask.account.exceptions.InvalidEntityException;
import finalTask.account.models.Account;
import finalTask.account.models.AccountType;
import finalTask.account.models.HistoryOfTransactions;
import finalTask.account.models.dto.AccountDto;
import finalTask.account.repositories.AccountsRepository;
import finalTask.account.services.AccountService;
import finalTask.account.services.HistoryOfTransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final HistoryOfTransactionService historyOfTransactionService;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountServiceImpl(AccountsRepository accountsRepository,
                              HistoryOfTransactionService historyOfTransactionService,
                              ModelMapper modelMapper) {
        this.accountsRepository = accountsRepository;
        this.historyOfTransactionService = historyOfTransactionService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createAccount(String accountNumber, LocalDateTime date,
                              double interestRate, String accountType) {

        AccountType accountType1 = switch (accountType) {
            case "deposit" -> AccountType.deposit;
            case "current" -> AccountType.current;
            default -> AccountType.undefined;
        };
        AccountDto accountDto = new AccountDto(accountNumber, date, interestRate, accountType1, null);
        Account acc = this.modelMapper.map(accountDto, Account.class);
        this.accountsRepository.save(acc);
        HistoryOfTransactions historyOfTransactions = new HistoryOfTransactions(acc);
        this.historyOfTransactionService.saveOperation(historyOfTransactions);
        acc.setHistoryOfTransactions(historyOfTransactions);
        saveOperation(acc);

    }

    @Override
    public void saveOperation(Account account) {
        this.accountsRepository.saveAndFlush(account);
    }

    @Override
    public void save(Account account) {
        this.accountsRepository.save(account);
    }

    @Override
    public AccountDto findAccountByNumber(String number) {
        Account account = this.accountsRepository.findAccountByAccountNumber(number).orElseThrow(() ->
                new InvalidEntityException("Account with number: " + number + " does not exist."));
        return this.modelMapper.map(account, AccountDto.class);
    }

    @Override
    public Account findAccountByNumberWithoutException(String number) {
        return this.accountsRepository.findAccountByAccountNumber(number).orElse(null);
    }

    @Override
    public boolean hasAvailableBalance(AccountDto accountDto, BigDecimal amount) {
        double accountBalance = accountDto.getBalance().doubleValue();
        double amountValue = amount.doubleValue();
        return accountBalance >= amountValue;
    }

    @Override
    public void accrueInterest(AccountDto accountDto) {
        BigDecimal amountToAccrue = accountDto.getBalance();
        BigDecimal interestValue = amountToAccrue.
                multiply(BigDecimal.valueOf(accountDto.getInterestRate()/100.0));
        if (accountDto.getType().name().equals("deposit")){
            accountDto.setBalance(amountToAccrue.add(amountToAccrue.
                    multiply(interestValue)));
        }else if (accountDto.getType().name().equals("current")){
            LocalDateTime from = accountDto.getDateOfOpening();
            LocalDateTime to = LocalDateTime.now();
            Duration duration = Duration.between(from, to);
            long days = duration.toDays();
            accountDto.setBalance(amountToAccrue.add(interestValue.multiply(BigDecimal.valueOf(days))));
        }
    }

    @Override
    public boolean isDepositAccount(AccountDto accountDto) {
        return accountDto.getType().name().equals("deposit");
    }

    @Override
    public boolean isDepositAccountIsOnTermDate(AccountDto accountDto) {
        int dayOfMonth = LocalDate.from(accountDto.getDateOfOpening()).getDayOfMonth();
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        return dayOfMonth == currentDayOfMonth;
    }



    @Override
    public List<AccountDto> getAllAccounts() {
        return this.accountsRepository.findAll().stream().map(account ->
                this.modelMapper.map(account, AccountDto.class)).collect(Collectors.toList());
    }

    @Override
    public int getCountOfRepository() {
        return (int) this.accountsRepository.count();
    }
}
