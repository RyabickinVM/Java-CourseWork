package Main.Services;

import Main.Entity.Balance;

import java.time.LocalDateTime;
import java.util.List;

public interface BalanceServices
{
  Balance createBalance(LocalDateTime create_date, double debit, double credit);
  void createBalance(Balance balance);
  List<Balance> readBalances();
  Balance findBalance(Integer id);
  void updateBalance(Integer id, Balance balance);
  void deleteBalances();
  void deleteByBalanceId(Integer id);
  Balance findBalanceById(Integer id);
}
