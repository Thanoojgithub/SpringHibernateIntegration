package com.hibernatemanymanybidirectional.service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernatemanymanybidirectional.dao.BooksAuthorsPersistence;

@Service("booksAuthorsServiceImpl")
public class BooksAuthorsServiceImpl implements BooksAuthorsService {

	@Autowired
	private BooksAuthorsPersistence booksAuthorsPersistence;

	@Override
	@Transactional
	public void booksAuthorsPersistenceOperations() {
		booksAuthorsPersistence.booksAuthorsPersistenceOperations();

	}

	@Override
	public GregorianCalendar getGregorianCalendarfromSysDate() {
		return booksAuthorsPersistence.getGregorianCalendarfromSysDate();
	}

	@Override
	public Date getDatefromSysDate() {
		return booksAuthorsPersistence.getDatefromSysDate();
	}

	@Override
	public List<?> getAuthors() {
		return booksAuthorsPersistence.getAuthors();
	}
}
