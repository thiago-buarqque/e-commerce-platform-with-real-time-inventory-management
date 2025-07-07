package com.nozama.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;
  private String email;
  private String password;
  private String cpf;
  private String phone;
  @OneToMany private List<Address> addresses;

  protected User(String name, String email, String password, String cpf, String phone) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.cpf = cpf;
    this.phone = phone;
  }
}
