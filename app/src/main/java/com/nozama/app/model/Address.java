package com.nozama.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Address")
@NoArgsConstructor
public class Address {
  @Id private long id;
  private String street;
  private String city;
  private String state;
  private String cep;
  private String country;
  private int number;
}
