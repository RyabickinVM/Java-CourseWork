package Main;

import Main.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class TestDataInit implements CommandLineRunner
{
  @Autowired
  UserRepository userRepository;

  public TestDataInit(UserRepository userRepository, PasswordEncoder pwdEncoder)
  {
    this.userRepository = userRepository;
    this.pwdEncoder = pwdEncoder;
  }

  @Autowired
  PasswordEncoder pwdEncoder;

  @Override
  public void run(String... args) throws Exception
  {
    //userRepository.save(new User("admin", pwdEncoder.encode("apwd"), Collections.singletonList("ROLE_ADMIN")));
  }
}

