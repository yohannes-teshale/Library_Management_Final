package controllers;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import models.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

       if(book.isAvailabe()) {
           CheckOutRecordEntry checkOutRecordEntry = new CheckOutRecordEntry(LocalDate.now(), LocalDate.now().plusDays(Long.valueOf(""+book.getmax()) ),book.getAvailableCopy());
           member.addCheckOutRecord(checkOutRecordEntry);
           System.out.println(member.getCheckOutRecord());
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
    public boolean returnBook(int copyN,String memberid, String isbn1){
        LibraryMember member= dataAccess.getLibraryMember(memberid);
        System.out.println(isbn1);
        Book book = dataAccess.getBook(isbn1);
        book.returnaBook(copyN);

       member.getCheckOutRecord().getCheckoutRecordEntryFromIsbnAndCopyN(isbn1, copyN).setReturned(true);
       dataAccess.saveNewMember(member);
        dataAccess.saveNewBook(book);
        return true;
    }


    public static void main(String[] args) {
        DataAccessFacade dataAccess= new DataAccessFacade();
        SystemController systemController= new SystemController();
        systemController.returnBook(1 ,"1001","99-22223"  );


         //systemController.checkOutBook("99-22223","1001");
        LibraryMember member = dataAccess.getLibraryMember("1001");
        Book book = dataAccess.getBook("99-22223");

        for(CheckOutRecordEntry e: member.getCheckOutRecord().getEntry()){
            if(e.isReturned()==false) System.out.println(e.getBookCopy().getCopyNum());
        }


    }
}
