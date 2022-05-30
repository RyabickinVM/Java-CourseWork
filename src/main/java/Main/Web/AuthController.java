package Main.Web;

import Main.Entity.User;
import Main.Repository.UserRepository;
import Main.Security.JWT.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController
{
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder pwdEncoder;

  @PostMapping("/signin")
  public ResponseEntity signIn(@RequestBody AuthRequest request)
  {
    try
    {
      String name = request.getUserName();
      String password = request.getPassword();
      Optional<User> user = userRepository.findByUserName(name);
      boolean pwdMatch = false;
      if (user.isPresent())
      {
        User us = user.get();
        pwdMatch = pwdEncoder.matches(password, us.getPassword());
      }

      if (!pwdMatch)
      {
        throw new BadCredentialsException("Invalid username or password.");
      }

      String token = jwtTokenProvider.createToken(name, user.orElseThrow(() ->
              new UsernameNotFoundException("Can't find the user.")).getRoles());

      return ResponseEntity.ok(token);
    }
    catch (AuthenticationException e)
    {
      throw new BadCredentialsException("Invalid username or password.");
    }
  }
}
