package Main.Repository;

import Main.Entity.Articles;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ArticlesRepository extends CrudRepository<Articles, Integer>
{
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM Operations op WHERE op.article_id = ?1", nativeQuery = true)
  void deleteOperationsByArticleId(Integer id);
}
