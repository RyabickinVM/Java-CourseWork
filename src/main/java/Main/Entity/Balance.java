package Main.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Balance")
public class Balance
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @Column(name = "create_date")
  @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
  private LocalDateTime create_date;

  @Column(name = "debit")
  private double debit;

  @Column(name = "credit")
  private double credit;

  @Column(name = "amount")
  private double amount;

  public Balance()
  {
  }

  public Balance(LocalDateTime create_date, double debit, double credit)
  {
    this.create_date = create_date;
    this.debit = debit;
    this.credit = credit;
    this.amount = debit - credit;
  }

  public Balance(Balance balance)
  {
    this.create_date = balance.getCreate_date();
    this.debit = balance.getDebit();
    this.credit = balance.getCredit();
    this.amount = debit - credit;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public LocalDateTime getCreate_date()
  {
    return create_date;
  }

  public void setCreate_date(LocalDateTime create_date)
  {
    this.create_date = create_date;
  }

  public double getDebit()
  {
    return debit;
  }

  public void setDebit(double debit)
  {
    this.debit = debit;
  }

  public double getCredit()
  {
    return credit;
  }

  public void setCredit(double credit)
  {
    this.credit = credit;
  }

  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }

  @Override
  public String toString()
  {
    return "Balance{" +
            "id=" + id +
            ", create_date=" + create_date +
            ", debit=" + debit +
            ", credit=" + credit +
            ", amount=" + amount +
            '}';
  }
}
