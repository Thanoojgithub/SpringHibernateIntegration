package com.hibernatemanymanybidirectional.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernatemanymanybidirectional.pojo.Author;
import com.hibernatemanymanybidirectional.pojo.Book;
import com.hibernatemanymanybidirectional.util.OperationsUtils;

@Repository
public class BooksAuthorsPersistenceImpl implements BooksAuthorsPersistence {

	private static final Logger logger = LoggerFactory.getLogger(BooksAuthorsPersistenceImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void booksAuthorsPersistenceOperations() {
		logger.debug(" ****** Hibernate One-Many Bidirectional - Foreignkey (Annotation) *** START **** ");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			Set<Author> authors = new HashSet<Author>();
			Set<Book> books = new HashSet<Book>();

			Author author1 = new Author();
			author1.setAuthorName("Rod johnson");
			authors.add(author1);
			Author author2 = new Author();
			author2.setAuthorName("Gavin King");
			authors.add(author2);

			Book book1 = new Book();
			book1.setBookName("Programming with Spring");
			Book book2 = new Book();
			book2.setBookName("Programming with Hibernate");
			books.add(book1);
			books.add(book2);

			author1.setBooks(books);
			author2.setBooks(books);

			book1.setAuthors(authors);
			book2.setAuthors(authors);

			session.save(author1);
			session.save(author2);

			OperationsUtils.queryLanguages(session);
			OperationsUtils.namedQueries(session);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if(session.isOpen())
				session.close();
			
		}
		logger.debug(" ****** Hibernate One-Many Bidirectional - Foreignkey (Annotation) *** END **** ");

	}

	/**
	 * MYSQL - select sysdate();
	 * ORACLE select SYSDATE from dual
	 */
	@Override
	public GregorianCalendar getGregorianCalendarfromSysDate() {
		Session session = null;
		GregorianCalendar gregorianCalendar = null;
		try {
		session = sessionFactory.openSession();
		SQLQuery createSQLQuery = session.createSQLQuery("SELECT CURRENT_TIMESTAMP sysdateVal FROM SYSIBM.SYSDUMMY1");
		createSQLQuery.addScalar("sysdateVal",StandardBasicTypes.CALENDAR_DATE);
		gregorianCalendar = (GregorianCalendar) createSQLQuery.uniqueResult();
		session.close();
		}catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if(session.isOpen())
				session.close();
		}
		return gregorianCalendar;
	}
	
	public Date getDatefromSysDate() {
		Session session = null;
		Date date = null;
		try {
		session = sessionFactory.openSession();
		SQLQuery createSQLQuery = session.createSQLQuery("SELECT CURRENT_TIMESTAMP sysdateVal FROM SYSIBM.SYSDUMMY1");
		
		/* returns Date in a format : 2015-08-19
		 * createSQLQuery.addScalar("sysdateVal",StandardBasicTypes.DATE);
		 */
		createSQLQuery.addScalar("sysdateVal", StandardBasicTypes.DATE);
		date = (Date) createSQLQuery.uniqueResult();
		session.close();
		}catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if(session.isOpen())
				session.close();
		}
		return date;
	}

}
