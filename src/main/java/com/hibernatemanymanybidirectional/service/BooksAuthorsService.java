package com.hibernatemanymanybidirectional.service;

import java.util.GregorianCalendar;

public interface BooksAuthorsService {

	public void booksAuthorsPersistenceOperations();
	
	public GregorianCalendar getDatefromSysDate();
}
