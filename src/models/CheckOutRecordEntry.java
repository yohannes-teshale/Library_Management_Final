package models;

import java.io.Serializable;
import java.time.LocalDate;


public class CheckOutRecordEntry implements Serializable {
    private LocalDate dueDate,chekOutDate;
    private BookCopy bookCopy;

    public CheckOutRecordEntry(LocalDate dueDate, LocalDate chekOutDate, BookCopy bC) {
        this.dueDate = dueDate;
        this.chekOutDate = chekOutDate;
        this.bookCopy=bC;
        bC.setIsAvailable(false);
    }

    public String getDueDate() {
        return dueDate.toString();
    }

    public String getChekOutDate() {
        return chekOutDate.toString();
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    @Override
    public String toString() {
        return "Book title: "+bookCopy.getBook().getTitle()+"\n"+
                "Checkout Date: "+ this.chekOutDate+"\n"+
                "Due Date: "+this.dueDate+"\n";
    }
}
