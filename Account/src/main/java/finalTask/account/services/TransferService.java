package finalTask.account.services;

import finalTask.account.exceptions.InsufficientFundsException;
import finalTask.account.models.Transfer;
import finalTask.account.models.dto.AccountDto;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TransferService {

    void transfer(AccountDto accountDto, BigDecimal amount, AccountDto recipient, LocalDateTime dateOfTransfer) throws InsufficientFundsException;

    void saveTransfer(Transfer transfer);
}
