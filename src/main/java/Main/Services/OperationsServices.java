package Main.Services;

import Main.Entity.Articles;
import Main.Entity.Balance;
import Main.Entity.Operations;

import java.util.List;

public interface OperationsServices
{
  void createOperation(Articles article, Balance balance);
  void createOperation(Operations operation);
  List<Operations> readOperations();
  Operations findOperation(Integer id);
  void updateOperation(Integer id, Operations operation);
  void deleteOperations();
  void deleteOperationById(Integer id);
  Operations findOperationById(Integer id);
  List<Operations> findOperationsByArticleName(String name);
  List<Operations> findOperationsByGreaterCredit();
  List<Operations> findOperationsByGreaterDebit();
  void deleteOperationByArticle(String name);
  List<Operations> findOperationsByArticleId(Integer id);
  Integer findAmountByOperationId(Integer id);
}