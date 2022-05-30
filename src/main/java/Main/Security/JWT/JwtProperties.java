package Main.Security.JWT;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties
{
  private String secretKey = "verySecretKey";
  private Integer validityInMs = 180000;

  public String getSecretKey()
  {
    return secretKey;
  }

  public void setSecretKey(String secretKey)
  {
    this.secretKey = secretKey;
  }

  public int getValidityInMs()
  {
    return validityInMs;
  }

  public void setValidityInMs(Integer validityInMs)
  {
    this.validityInMs = validityInMs;
  }
}
