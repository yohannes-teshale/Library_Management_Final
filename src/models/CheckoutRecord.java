package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
  private List<CheckOutRecordEntry> entry=new ArrayList<>();


  public CheckoutRecord() {


  }
  public void addCheckOutEntry(CheckOutRecordEntry checkOutRecordEntry){


    entry.add(checkOutRecordEntry);
  }
  public int getSize(){
    return entry.size();
  }

  public List<CheckOutRecordEntry> getEntry() {
    return entry;
  }
  public BookCopy getBookCopyByNumber(int cp){
    BookCopy temp;
    for(CheckOutRecordEntry e: entry){
      if(e.getBookCopy().getCopyNum()==cp)
        return e.getBookCopy();
    }
    return null;
  }
  public CheckOutRecordEntry getCheckoutRecordEntryFromIsbnAndCopyN(String isbn, int cn){
    for(CheckOutRecordEntry e: entry){
      if(e.getBookCopy().getCopyNum()==cn && e.getBookCopy().getBook().getIsbn().equals(isbn)){
        return e;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    String cont= "";
    for(CheckOutRecordEntry c: entry){
      cont+= c.toString()+"\n";
    }
    return cont;
  }
}
