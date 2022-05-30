package Main.Exceptions;

public class OperationNotFoundException extends RuntimeException
{
  public OperationNotFoundException(String message)
  {
    super(message);
  }
}
