package com.hibernatemanymanybidirectional.service;

import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hibernatemanymanybidirectional.dao.BooksAuthorsPersistence;

@Component("booksAuthorsServiceImpl")
public class BooksAuthorsServiceImpl implements BooksAuthorsService {

	@Autowired
	private BooksAuthorsPersistence booksAuthorsPersistence;

	@Override
	@Transactional
	public void booksAuthorsPersistenceOperations() {
		booksAuthorsPersistence.booksAuthorsPersistenceOperations();

	}

	@Override
	public GregorianCalendar getDatefromSysDate() {
		return booksAuthorsPersistence.getDatefromSysDate();
	}
}
