package ea.user;

import org.springframework.data.annotation.Id;

public class User {

  @Id
  private String id;
  private String username;
  private String password;

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public User withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User withPassword(String password) {
    this.password = password;
    return this;
  }
}
