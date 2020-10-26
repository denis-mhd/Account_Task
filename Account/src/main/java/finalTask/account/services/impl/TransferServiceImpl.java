package finalTask.account.services.impl;

import finalTask.account.exceptions.InsufficientFundsException;
import finalTask.account.models.*;
import finalTask.account.models.dto.AccountDto;
import finalTask.account.repositories.TransfersRepository;
import finalTask.account.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferServiceImpl implements TransferService {
    private final TransfersRepository transfersRepository;
    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final ModelMapper modelMapper;

    @Autowired
    public TransferServiceImpl(TransfersRepository transfersRepository, DepositService depositService,
                               WithdrawService withdrawService, ModelMapper modelMapper) {
        this.transfersRepository = transfersRepository;
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void transfer(AccountDto accountDto, BigDecimal amount, AccountDto recipient, LocalDateTime dateOfTransfer) throws InsufficientFundsException {
        this.withdrawService.withdrawAmount(accountDto.getAccountNumber(), amount);
        this.depositService.depositAmount(amount, recipient.getAccountNumber());
        saveTransfer(new Transfer(this.modelMapper.map(accountDto, Account.class),
                this.modelMapper.map(recipient, Account.class), amount,dateOfTransfer));
    }

    @Override
    public void saveTransfer(Transfer transfer) {
        this.transfersRepository.saveAndFlush(transfer);
    }
}
