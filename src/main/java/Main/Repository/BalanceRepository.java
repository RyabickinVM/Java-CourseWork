package Main.Repository;

import Main.Entity.Balance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface BalanceRepository extends CrudRepository<Balance, Integer>
{
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM Operations op WHERE op.balance_id = ?1", nativeQuery = true)
  void deleteOperationsByBalanceId(Integer id);
}