package dataaccess;

import java.util.HashMap;


import dataaccess.DataAccessFacade.StorageType;
import models.Book;
import models.LibraryMember;

public interface DataAccess { 
	public HashMap<String, Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
}
