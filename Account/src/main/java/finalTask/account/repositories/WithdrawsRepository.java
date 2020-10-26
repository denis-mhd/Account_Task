package finalTask.account.repositories;

import finalTask.account.models.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawsRepository extends JpaRepository<Withdraw, Long> {
}
