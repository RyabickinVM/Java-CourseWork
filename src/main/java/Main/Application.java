package Main;

import Main.Repository.UserRepository;
import Main.Services.ArticlesServices;
import Main.Services.BalanceServices;
import Main.Services.OperationsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application
{
  @Autowired
  ArticlesServices articlesServices;

  @Autowired
  BalanceServices balanceServices;

  @Autowired
  OperationsServices operationsServices;

  @Autowired
  UserRepository userRepository;

  public static void main(String[] args)
  {
    SpringApplication.run(Application.class, args);
  }

  /*@Override
  public void run(String... args) throws Exception
  {
    LocalDateTime time = LocalDateTime.now();
    Balance bal = new Balance(time, 6000, 4000);
    balanceServices.createBalance(bal);
    Articles art = new Articles("Test 1337");
    LocalDateTime time = LocalDateTime.now();
    Balance bal = new Balance(time, 1337, 337);
    operationsServices.createOperation(articlesServices.findArticle(6), balanceServices.findBalance(19));
    operationsServices.createOperation(articlesServices.findArticle(8), balanceServices.findBalance(20));
    operationsServices.createOperation(articlesServices.findArticle(7), balanceServices.findBalance(18));
  }*/

  @Bean
  public PasswordEncoder passwordEncoder()
  {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}