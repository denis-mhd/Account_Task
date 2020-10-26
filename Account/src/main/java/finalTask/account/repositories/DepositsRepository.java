package finalTask.account.repositories;

import finalTask.account.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositsRepository extends JpaRepository<Deposit, Long> {
}
