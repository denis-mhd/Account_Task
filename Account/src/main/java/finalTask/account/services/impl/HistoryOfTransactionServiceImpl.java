package finalTask.account.services.impl;

import finalTask.account.models.HistoryOfTransactions;
import finalTask.account.repositories.HistoryOfTransactionsRepository;
import finalTask.account.services.HistoryOfTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryOfTransactionServiceImpl implements HistoryOfTransactionService {
    private final HistoryOfTransactionsRepository historyOfTransactionsRepository;


    @Autowired
    public HistoryOfTransactionServiceImpl(HistoryOfTransactionsRepository historyOfTransactionsRepository) {
        this.historyOfTransactionsRepository = historyOfTransactionsRepository;
    }


    @Override
    public HistoryOfTransactions getByAccountId(Long id) {
        return this.historyOfTransactionsRepository.findByAccount_Id(id);
    }

    @Override
    public void saveOperation(HistoryOfTransactions historyOfTransactions) {
        this.historyOfTransactionsRepository.saveAndFlush(historyOfTransactions);
    }
}
