package Main.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Articles")
public class Articles
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  public Articles()
  {
  }

  public Articles(String name)
  {
    this.name = name;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @Override
  public String toString()
  {
    return "Articles{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
