package models;

import java.io.Serializable;

public class BookCopy implements Serializable {
    private int copyNum;
    private Book book;
    private boolean isAvailable;

    public int getCopyNum() {
        return copyNum;
    }

    public BookCopy(int copyNum, Book book) {
        this.copyNum = copyNum;
        this.book = book;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean b) {
        this.isAvailable=b;
    }

    public Book getBook() {
        return book;
    }
}
