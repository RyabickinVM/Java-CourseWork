package Main.Repository;

import Main.Entity.Operations;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OperationsRepository extends CrudRepository<Operations, Integer>
{
  @Query(value = "SELECT Operations.*, Articles.name FROM Operations INNER JOIN Articles ON article_id = " +
         "Articles.id WHERE Articles.name = :name", nativeQuery = true)
  List<Operations> findOperationsByArticleName(@Param("name") String name);

  @Query(value = "SELECT op FROM Operations op INNER JOIN Balance bl ON op.credit = bl.credit " +
          "WHERE op.credit > op.debit")
  List<Operations> findOperationsByGreaterCredit();

  @Query(value = "SELECT op FROM Operations op INNER JOIN Balance bl ON op.debit = bl.debit " +
          "WHERE op.debit > op.credit")
  List<Operations> findOperationsByGreaterDebit();

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM Operations WHERE article_id IN (SELECT id FROM Articles WHERE name = ?1)", nativeQuery = true)
  void deleteOperationByArticle(String name);

  @Query(value = "SELECT Operations.*, Articles.id FROM Operations INNER JOIN Articles ON article_id = " +
          "Articles.id WHERE Articles.id = :id", nativeQuery = true)
  List<Operations> findOperationsByArticleId(Integer id);

  @Query(value = "SELECT amount FROM Balance bl INNER JOIN Operations op ON bl.id = op.balance_id " +
          "WHERE op.id = ?1", nativeQuery = true)
  Integer findAmountByOperationId(Integer id);
}
