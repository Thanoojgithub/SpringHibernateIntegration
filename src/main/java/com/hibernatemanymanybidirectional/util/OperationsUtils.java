package com.hibernatemanymanybidirectional.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hibernatemanymanybidirectional.pojo.Author;
import com.hibernatemanymanybidirectional.pojo.Book;

public class OperationsUtils {

	public static void namedQueries(Session session) {
		Query queryNamedQuery = session.getNamedQuery("findBookByBookName")
				.setString("bookName", "Programming with Spring");
		List<Book> booksNamedQuery = (List<Book>) queryNamedQuery.list();
		for (Book book : booksNamedQuery) {
			System.out.println(book);
		}
		Query queryNamedNativeQuery = session.getNamedQuery(
				"findAuthorByAuthorNameNativeSQL").setString("authorName",
				"Rod johnson");
		List<Author> authorsNamedNativeQuery = (List<Author>) queryNamedNativeQuery
				.list();
		for (Author author : authorsNamedNativeQuery) {
			System.out.println(author);
		}
	}

	public static void queryLanguages(Session session) {
		doHQLCreateQuery(session, "Programming with Spring");
		doCriteria(session, "Programming with Spring");
		doSQLQuery(session, "Programming with Spring");
	}

	public static void doHQLCreateQuery(Session session, String bookName) {
		Query querySelect = session
				.createQuery("from Book where bookName = :bookName ");
		querySelect.setParameter("bookName", bookName);
		List<Book> books = (List<Book>) querySelect.list();
		for (Book book : books) {
			System.out.println(book);
			Query queryUpdate = session
					.createQuery("update Book set bookName = :bookName where bookId = :bookId");
			queryUpdate.setParameter("bookName", book.getBookName());
			queryUpdate.setParameter("bookId", book.getBookId());
			queryUpdate.executeUpdate();
		}

	}

	public static void doCriteria(Session session, String bookName) {

		Criteria criteria = session.createCriteria(Book.class);
		if (bookName != null) {
			criteria.add(Restrictions.like("bookName", bookName));
		}
		criteria.addOrder(Order.asc("bookId"));
		List<Book> books = (List<Book>) criteria.list();
		for (Book book : books) {
			System.out.println(book);
		}
	}

	public static void doSQLQuery(Session session, String bookName) {
		Query query = session
				.createSQLQuery(
						"select * from mydb.BOOK b where b.B_NAME = :bookName")
				.addEntity(Book.class).setParameter("bookName", bookName);
		List<Book> result = (List<Book>) query.list();
		for (Book book : result) {
			System.out.println(book);
		}
	}
}
