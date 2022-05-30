package Main.Services;

import Main.Entity.Articles;
import Main.Entity.Balance;
import Main.Entity.Operations;
import Main.Exceptions.OperationNotFoundException;
import Main.Repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationsServicesImpl implements OperationsServices
{
  @Autowired
  private OperationsRepository operationsRepository;

  @Override
  public void createOperation(Articles article, Balance balance)
  {
    operationsRepository.save(new Operations(article, balance));
  }

  @Override
  public void createOperation(Operations operation)
  {
    Operations oper = new Operations(operation);
    operationsRepository.save(oper);
  }

  @Override
  public List<Operations> readOperations()
  {
    return (List<Operations>) operationsRepository.findAll();
  }

  @Override
  public Operations findOperation(Integer id)
  {
    Operations desiredOper = findOperationById(id);
    return desiredOper;
  }

  @Override
  public void updateOperation(Integer id, Operations operation)
  {
    Operations operationOld = operationsRepository.findById(id)
            .orElseThrow(() -> new OperationNotFoundException("Invalid ID."));
    operationOld.setArticle(operation.getArticle());
    operationOld.setBalance(operation.getBalance());
    operationOld.sync(operationOld);
    operationsRepository.save(operationOld);
  }

  @Override
  public void deleteOperations()
  {
    operationsRepository.deleteAll();
  }

  @Override
  public void deleteOperationById(Integer id)
  {
    operationsRepository.deleteById(id);
  }

  @Override
  public Operations findOperationById(Integer id)
  {
    Optional<Operations> optionalOper = operationsRepository.findById(id);
    if(optionalOper.isPresent())
    {
      return optionalOper.get();
    }
    else
    {
      throw new OperationNotFoundException("Can't find the operation.");
    }
  }

  @Override
  public List<Operations> findOperationsByArticleName(String name)
  {
    return operationsRepository.findOperationsByArticleName(name);
  }

  @Override
  public List<Operations> findOperationsByGreaterCredit()
  {
    return operationsRepository.findOperationsByGreaterCredit();
  }

  @Override
  public List<Operations> findOperationsByGreaterDebit()
  {
    return operationsRepository.findOperationsByGreaterDebit();
  }

  @Override
  public void deleteOperationByArticle(String name)
  {
    operationsRepository.deleteOperationByArticle(name);
  }

  @Override
  public List<Operations> findOperationsByArticleId(Integer id)
  {
    return operationsRepository.findOperationsByArticleId(id);
  }

  @Override
  public Integer findAmountByOperationId(Integer id)
  {
    return operationsRepository.findAmountByOperationId(id);
  }
}