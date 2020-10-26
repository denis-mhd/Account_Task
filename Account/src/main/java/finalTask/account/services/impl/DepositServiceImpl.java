package finalTask.account.services.impl;

import finalTask.account.models.Account;
import finalTask.account.models.Deposit;
import finalTask.account.models.HistoryOfTransactions;
import finalTask.account.models.dto.AccountDto;
import finalTask.account.models.dto.DepositDto;
import finalTask.account.repositories.DepositsRepository;
import finalTask.account.services.AccountService;
import finalTask.account.services.DepositService;
import finalTask.account.services.HistoryOfTransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DepositServiceImpl implements DepositService {
    private final DepositsRepository depositsRepository;
    private final HistoryOfTransactionService historyOfTransactionService;
    private final AccountService accountService;
    private final ModelMapper modelMapper;


    @Autowired
    public DepositServiceImpl(DepositsRepository depositsRepository, HistoryOfTransactionService historyOfTransactionService, AccountService accountService, ModelMapper modelMapper) {
        this.depositsRepository = depositsRepository;
        this.historyOfTransactionService = historyOfTransactionService;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void depositAmount(BigDecimal amount, String accountNumber) {
        AccountDto accountDto = this.accountService.findAccountByNumber(accountNumber);
        DepositDto depositDto = new DepositDto(accountDto, amount, LocalDateTime.now(),
                accountDto.getHistoryOfTransactions());
        this.accountService.accrueInterest(accountDto);
        accountDto.setBalance(accountDto.getBalance().equals(BigDecimal.valueOf(0)) ? amount : accountDto.getBalance().add(amount));
        this.accountService.saveOperation(this.modelMapper.map(accountDto, Account.class));
        HistoryOfTransactions historyOfTransactions = this.historyOfTransactionService
                .getByAccountId(accountDto.getId());
        depositDto.setHistoryOfTransactions(historyOfTransactions);
        Deposit deposit = this.modelMapper.map(depositDto, Deposit.class);
        deposit.getHistoryOfTransactions().getDeposits().add(deposit);
        this.historyOfTransactionService.saveOperation(deposit.getHistoryOfTransactions());
        saveOperation(deposit);
    }

    @Override
    public void saveOperation(Deposit deposit) {
        this.depositsRepository.saveAndFlush(deposit);
    }
}
