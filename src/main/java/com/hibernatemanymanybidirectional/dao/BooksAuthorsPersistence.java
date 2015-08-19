package com.hibernatemanymanybidirectional.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public interface BooksAuthorsPersistence {

	public void booksAuthorsPersistenceOperations();

	public GregorianCalendar getGregorianCalendarfromSysDate();

	public Date getDatefromSysDate();

	List<?> getAuthors();

}
