package finalTask.account.services;

import finalTask.account.models.Account;
import finalTask.account.models.Deposit;
import finalTask.account.models.HistoryOfTransactions;

public interface HistoryOfTransactionService {

    HistoryOfTransactions getByAccountId(Long id);

    void saveOperation(HistoryOfTransactions historyOfTransactions);
}
