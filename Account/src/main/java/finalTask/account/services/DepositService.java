package finalTask.account.services;

import finalTask.account.models.Account;
import finalTask.account.models.Deposit;
import finalTask.account.models.dto.AccountDto;
import finalTask.account.models.dto.DepositDto;

import java.math.BigDecimal;

public interface DepositService {

    void depositAmount(BigDecimal amount, String accountNumber);

    void saveOperation(Deposit deposit);
}
