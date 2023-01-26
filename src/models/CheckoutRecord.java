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

  @Override
  public String toString() {
    String cont= "";
    for(CheckOutRecordEntry c: entry){
      cont+= c.toString()+"\n";
    }
    return cont;
  }
}
