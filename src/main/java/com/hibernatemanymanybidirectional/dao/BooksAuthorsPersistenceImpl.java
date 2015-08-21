package com.hibernatemanymanybidirectional.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
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
			tx = session.beginTransaction();
			Set<Author> authors = new HashSet<Author>();
			Set<Book> books = new HashSet<Book>();

			Author author1 = new Author();
			author1.setAuthorName("Rod johnson");
			authors.add(author1);
			Author author2 = new Author();
			author2.setAuthorName("Gavin King");
			authors.add(author2);
			Date time = Calendar.getInstance().getTime();
			Book book1 = new Book("Programming with Spring", time);
			Book book2 = new Book("Programming with Hibernate", time);
			books.add(book1);
			books.add(book2);

			author1.setBooks(books);
			author2.setBooks(books);

			session.save(author1);
			session.save(author2);
			tx.commit();
			//OperationsUtils.queryLanguages(session);
			//OperationsUtils.namedQueries(session);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			if(tx.isActive())
				tx.rollback();
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
	
	@Override
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
	
	@Override
	public List<?> getAuthors() {
		Session session = null;
		/*
		 * List<Author> will gives, Warning: Unchecked cast from List to List<Author>
		 */
		List<?> authors = null;
		try {
		session = sessionFactory.openSession();
		SQLQuery createSQLQuery = session.createSQLQuery("select * from mydb.AUTHOR");
		createSQLQuery.addEntity(Author.class);
		
		/*
		 * List<Author> will gives, Warning: Unchecked cast from List to List<Author>
		 * authors = (List<Author>) createSQLQuery.list();
		 */
		authors = createSQLQuery.list();
		session.close();
		}catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if(session.isOpen())
				session.close();
		}
		return authors;
	}

	
}
