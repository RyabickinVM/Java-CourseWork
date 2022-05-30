package Main.Exceptions;

public class BalanceNotFoundException extends RuntimeException
{
  public BalanceNotFoundException(String message)
  {
    super(message);
  }
}
