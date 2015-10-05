package com.hibernatemanymanybidirectional.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hibernatemanymanybidirectional.service.BooksAuthorsService;
import com.hibernatemanymanybidirectional.util.AuthorSortByColumn;

public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		displayAuthors();
		authorsPaginationUsingCriteriaAPI();
	}

	private static void authorsPaginationUsingCriteriaAPI() {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-beans.xml")) {
			BooksAuthorsService booksAuthorsService = (BooksAuthorsService) ctx.getBean("booksAuthorsServiceImpl");
			// sorting column could me send by UI to DAO call
			List<?> paginationAuthorsUsingCriteriaAPISortByAuthorId = booksAuthorsService.getPaginationAuthorsUsingCriteriaAPI(1, 10, AuthorSortByColumn.authorName.getValue());
			List<?> paginationAuthorsUsingCriteriaAPISortByAuthorName = booksAuthorsService.getPaginationAuthorsUsingCriteriaAPI(11, 10, AuthorSortByColumn.authorId.getValue());
			for (Object object : paginationAuthorsUsingCriteriaAPISortByAuthorId) {
				System.out.println("List<?> paginationAuthorsUsingCriteriaAPISortByAuthorId "+ object.toString());
			}
			
			for (Object object : paginationAuthorsUsingCriteriaAPISortByAuthorName) {
				System.out.println("List<?> paginationAuthorsUsingCriteriaAPISortByAuthorName "+ object.toString());
			}
		}
	
	}

	private static void displayAuthors() {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-beans.xml")) {
			BooksAuthorsService booksAuthorsService = (BooksAuthorsService) ctx.getBean("booksAuthorsServiceImpl");
			booksAuthorsService.booksAuthorsPersistenceOperations();
			System.out.println(booksAuthorsService.getDatefromSysDate());
			System.out.println(booksAuthorsService.getGregorianCalendarfromSysDate().getTime());
			List<?> authors = booksAuthorsService.getAuthors();
			for (Object object : authors) {
				System.out.println("List<?> authors "+ object.toString());
			}
		}
	}
}

/**
 * OUTPUT
 * 
 * Author [authorId=67, authorName=Rod johnson]
 * Author [authorId=69, authorName=Rod johnson]
 * Author [authorId=71, authorName=Rod johnson]
 * Author [authorId=73, authorName=Rod johnson]
 * Author [authorId=75, authorName=Rod johnson]
 * Author [authorId=77, authorName=Rod johnson]
 * Author [authorId=79, authorName=Rod johnson]
 * 
 * 2015-08-19
 * Wed Aug 19 00:00:00 IST 2015
 */








/**
 * http://javabeginnerstutorial.com/spring-framework-tutorial/spring-transaction-management-tutorial/
 */
