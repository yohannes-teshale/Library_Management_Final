package models;

import java.io.Serializable;
import java.util.List;

public class Author extends Person implements Serializable {
    private String bio;
    private String credentials;

    List<Book> books;

    public Author(String firstName, String lastName, String phone,Address address, String bio) {
        super(firstName, lastName, address, phone);
        this.bio = bio;
    }

}
