package finalTask.account.services.impl;

import finalTask.account.exceptions.InsufficientFundsException;
import finalTask.account.models.Account;
import finalTask.account.models.HistoryOfTransactions;
import finalTask.account.models.Withdraw;
import finalTask.account.models.dto.AccountDto;
import finalTask.account.models.dto.WithdrawDto;
import finalTask.account.repositories.WithdrawsRepository;
import finalTask.account.services.AccountService;
import finalTask.account.services.HistoryOfTransactionService;
import finalTask.account.services.WithdrawService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WithdrawServiceImpl implements WithdrawService {
    private final WithdrawsRepository withdrawsRepository;
    private final AccountService accountService;
    private final HistoryOfTransactionService historyOfTransactionService;
    private final ModelMapper modelMapper;

    @Autowired
    public WithdrawServiceImpl(WithdrawsRepository withdrawsRepository, AccountService accountService,
                               HistoryOfTransactionService historyOfTransactionService, ModelMapper modelMapper) {
        this.withdrawsRepository = withdrawsRepository;
        this.accountService = accountService;
        this.historyOfTransactionService = historyOfTransactionService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void withdrawAmount(String accountNumber, BigDecimal amount) throws InsufficientFundsException {
        AccountDto accountDto = this.accountService.findAccountByNumber(accountNumber);
        if (this.accountService.isDepositAccount(accountDto)) {
            if (this.accountService.isDepositAccountIsOnTermDate(accountDto)) {
                withdraw(accountDto, amount, LocalDateTime.now());
            }
        } else {
            withdraw(accountDto, amount, LocalDateTime.now());
        }
    }

    @Override
    public void withdraw(AccountDto accountDto, BigDecimal amount, LocalDateTime daytimeOfWithdraw) throws InsufficientFundsException {
        if (this.accountService.hasAvailableBalance(accountDto, amount)) {
            accountDto.setBalance(accountDto.getBalance().subtract(amount));
            WithdrawDto withdrawDto = new WithdrawDto(accountDto, amount, daytimeOfWithdraw, null);
            HistoryOfTransactions historyOfTransactions = this.historyOfTransactionService.getByAccountId(accountDto.getId());
            withdrawDto.setHistoryOfTransactions(historyOfTransactions);
            Withdraw withdraw = this.modelMapper.map(withdrawDto, Withdraw.class);
            saveOperation(withdraw);
            historyOfTransactions.getWithdraws().add(withdraw);
            this.historyOfTransactionService.saveOperation(withdraw.getHistoryOfTransactions());
            this.accountService.saveOperation(this.modelMapper.map(accountDto, Account.class));
        }else{
            throw new InsufficientFundsException("Insufficient funds.");
        }
    }

    @Override
    public void saveOperation(Withdraw withdraw) {
        this.withdrawsRepository.saveAndFlush(withdraw);
    }
}
