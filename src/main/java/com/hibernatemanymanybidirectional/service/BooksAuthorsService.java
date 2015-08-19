package com.hibernatemanymanybidirectional.service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public interface BooksAuthorsService {

	public void booksAuthorsPersistenceOperations();

	public GregorianCalendar getGregorianCalendarfromSysDate();

	public Date getDatefromSysDate();
	
	List<?> getAuthors();
}
