package models;

import java.io.Serializable;

public class Admin extends Person implements Serializable {
  public String adminId;

  public Admin(String adminId,String firstName, String lastName, Address address, String account) {
    super(firstName, lastName, address, account);

  }
}
