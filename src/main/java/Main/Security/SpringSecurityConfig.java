package Main.Security;

import Main.Security.JWT.JwtSecurityConfigurer;
import Main.Security.JWT.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception
  {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http
            //.httpBasic().disable()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET,"/").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers(HttpMethod.GET).permitAll()
            .antMatchers(HttpMethod.POST).hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .apply(new JwtSecurityConfigurer(jwtTokenProvider));
  }
}
