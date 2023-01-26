package models;

import java.io.Serializable;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private Address address;
    private String phone;
    private Account account;

    public Person(String firstName, String lastName, Address address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Account getAccount() {
        return account;
    }
}
