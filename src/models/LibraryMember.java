package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LibraryMember extends Person implements Serializable {
    private String memberId;
    private CheckoutRecord checkOutRecord= new CheckoutRecord();

    public LibraryMember(String memberId, String firstName,String lastName,String phone, Address address) {
        super(firstName, lastName, address, phone);
        this.memberId = memberId;

    }

    public String getMemberId() {
        return this.memberId;
    }
    public void addCheckOutRecord(CheckOutRecordEntry checkoutRecordEntry){

        this.checkOutRecord.addCheckOutEntry(checkoutRecordEntry);
    }

    public CheckoutRecord getCheckOutRecord() {
        return checkOutRecord;
    }
}
