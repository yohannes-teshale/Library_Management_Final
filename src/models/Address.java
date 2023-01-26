package models;

import java.io.Serializable;

public class Address implements Serializable {
    private String city;
    private String state;
    private String street;
    private String zip;

    public Address(String street, String city, String state, String zip) {
        this.city = city;
        this.state = state;
        this.street = street;
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }
}
