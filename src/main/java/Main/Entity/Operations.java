package Main.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Operations")
public class Operations
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "article_id", referencedColumnName = "id")
  private Articles article;

  @Column(name = "create_date")
  @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
  private LocalDateTime create_date;

  @Column(name = "debit")
  private double debit;

  @Column(name = "credit")
  private double credit;

  @OneToOne
  @JoinColumn(name = "balance_id", referencedColumnName = "id")
  private Balance balance;

  public Operations()
  {
  }

  public Operations(Articles article, Balance balance)
  {
    this.article = article;
    this.balance = balance;
    this.debit = balance.getDebit();
    this.credit = balance.getCredit();
    this.create_date = LocalDateTime.now();
  }

  public Operations(Operations operation)
  {
    this.article = operation.getArticle();
    this.balance = operation.getBalance();
    this.debit = balance.getDebit();
    this.credit = balance.getCredit();
    this.create_date = LocalDateTime.now();
  }

  public void sync(Operations operation)
  {
    operation.debit = balance.getDebit();
    operation.credit = balance.getCredit();
  }

  public Balance getBalance()
  {
    return balance;
  }

  public void setBalance(Balance balance)
  {
    this.balance = balance;
  }

  public Articles getArticle()
  {
    return article;
  }

  public void setArticle(Articles article)
  {
    this.article = article;
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

  @Override
  public String toString()
  {
    return "Operations{" +
            "id=" + id +
            ", article=" + article +
            ", create_date=" + create_date +
            ", debit=" + debit +
            ", credit=" + credit +
            ", balance=" + balance +
            '}';
  }
}
