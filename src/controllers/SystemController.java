package controllers;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import models.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SystemController {


    private final DataAccessFacade dataAccess= new DataAccessFacade();

    public boolean addNewMemeber(String memberId, String firstName,String lastName,String phone, Address address){
        LibraryMember libraryMember= new LibraryMember(memberId,firstName,lastName,phone,address);
        dataAccess.saveNewMember(libraryMember);
        return true;
    }
    public boolean addNewBook(String isbn, String title, int maxCheckLength, List<Author> authorList){
        Book book= new Book(isbn,title,maxCheckLength,authorList);
        book.addCopy();
        dataAccess.saveNewBook(book);
        return true;
    }
    public boolean checkOutBook(String isbn,  String memberid){
       Book book= dataAccess.getBook(isbn);
       LibraryMember member= dataAccess.getLibraryMember(memberid);
        System.out.println(book.isAvailabe());
       if(book.isAvailabe()) {

           CheckOutRecordEntry checkOutRecordEntry = new CheckOutRecordEntry(LocalDate.now(), LocalDate.now().plusDays(Long.valueOf(""+book.getmax()) ),book.getAvailableCopy());
           member.addCheckOutRecord(checkOutRecordEntry);
           dataAccess.saveNewBook(book);
           dataAccess.saveNewMember(member);
       }else return false;

       return true;
    }
    public boolean addBookcopy(String isbn){
        Book book= dataAccess.getBook(isbn);
        book.addCopy();
        dataAccess.saveNewBook(book);
        return true;


    }


    public static void main(String[] args) {

        SystemController systemController= new SystemController();
        systemController.checkOutBook("99-22223","1001");
        DataAccessFacade da= new DataAccessFacade();
        System.out.println(da.getLibraryMember("1001").getCheckOutRecord());

    }
}
