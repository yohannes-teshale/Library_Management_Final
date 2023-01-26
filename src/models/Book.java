package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private String title;
    private String isbn;
    private int maxCheckLength;
    private List<BookCopy> bookCopyList= new ArrayList<>();
    private List<Author> authorList;
    
    
    public boolean isAvailable(){
           return bookCopyList.size()==0?  false: true;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public Book(String isbn, String title, int maxCheckLength, List<Author> authorList) {
        this.title = title;
        this.isbn = isbn;
        this.maxCheckLength = maxCheckLength;
        this.authorList = authorList;
    }

    public void addCopy() {
        int size;
        if(bookCopyList==null) size=0;
        else size=1;
        BookCopy bookCopy= new BookCopy(size+1,this);
        bookCopy.setIsAvailable(true);
       bookCopyList.add(bookCopy);
    }
    public boolean isAvailabe(){

        for(BookCopy bc: bookCopyList){
            if (bc.isAvailable()){
                System.out.println(bookCopyList.indexOf(bc));
              return true;
            }
        }
        return false;
    }

    public List<BookCopy> getBookCopyList() {
        return bookCopyList;
    }

    public BookCopy getAvailableCopy(){
        for(BookCopy bc: bookCopyList){
            if(bc.isAvailable()){
                return bc;
            }
        }
        return null;
    }
    public int numberofAvailableCopies(){
        int count=0;
        for(BookCopy bc: bookCopyList){
            if(bc.isAvailable()){
                count++;
            }
        }
        return count;

    }

    public Object getmax() {
        return this.maxCheckLength;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxCheckLength() {
        return maxCheckLength;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }
}
