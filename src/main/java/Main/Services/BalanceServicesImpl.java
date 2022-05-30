package Main.Services;

import Main.Entity.Balance;
import Main.Exceptions.BalanceNotFoundException;
import Main.Repository.BalanceRepository;
import Main.Repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceServicesImpl implements BalanceServices
{
  @Autowired
  private BalanceRepository balanceRepository;

  @Autowired
  private OperationsRepository operationsRepository;

  @Override
  public Balance createBalance(LocalDateTime create_date, double debit, double credit)
  {
    return balanceRepository.save(new Balance(create_date, debit, credit));
  }

  @Override
  public void createBalance(Balance balance)
  {
    Balance b = new Balance(balance);
    balanceRepository.save(b);
  }

  @Override
  public List<Balance> readBalances()
  {
    return (List<Balance>) balanceRepository.findAll();
  }

  @Override
  public Balance findBalance(Integer id)
  {
    Balance desiredBal = findBalanceById(id);
    return desiredBal;
  }

  @Override
  public void updateBalance(Integer id, Balance balance)
  {
    Balance balanceOld = balanceRepository.findById(id)
                    .orElseThrow(() -> new BalanceNotFoundException("Invalid ID."));
    balanceOld.setCreate_date(balance.getCreate_date());
    balanceOld.setDebit(balance.getDebit());
    balanceOld.setCredit(balance.getCredit());
    balanceOld.setAmount(balanceOld.getDebit() - balanceOld.getCredit());
    balanceRepository.save(balanceOld);
  }

  @Override
  public void deleteBalances()
  {
    operationsRepository.deleteAll();
    balanceRepository.deleteAll();
  }

  @Override
  public void deleteByBalanceId(Integer id)
  {
    balanceRepository.deleteOperationsByBalanceId(id);
    balanceRepository.deleteById(id);
  }

  @Override
  public Balance findBalanceById(Integer id)
  {
    Optional<Balance> optionalBal = balanceRepository.findById(id);
    if(optionalBal.isPresent())
    {
      return optionalBal.get();
    }
    else
    {
      throw new BalanceNotFoundException("Can't find the balance.");
    }
  }
}