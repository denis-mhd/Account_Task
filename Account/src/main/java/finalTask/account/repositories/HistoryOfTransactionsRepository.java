package finalTask.account.repositories;

import finalTask.account.models.HistoryOfTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryOfTransactionsRepository extends JpaRepository<HistoryOfTransactions, Long> {

    HistoryOfTransactions findByAccount_Id(Long id);
}
