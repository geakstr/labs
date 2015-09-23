package ru.aspu.javaee.lab5.datamanager.dao;

import java.util.List;

import ru.aspu.javaee.lab5.datamanager.dao.generics.IGenericDao;
import ru.aspu.javaee.lab5.entities.Book;
import ru.aspu.javaee.lab5.entities.Comment;

public interface BookDao extends IGenericDao<Book> {
	public abstract List<Book> getAll();

	public abstract List<Book> getAllOrderedByName();

	public abstract List<Book> getAllOrderedByYearOfPublishing();

	public abstract List<Book> getAllBooksByGenre(String genre);

	public abstract List<Book> getAllBooksByString(String name);

	public abstract List<Comment> getAllCommentsForBook(Book book);

	public abstract List<Comment> getAllComments();

	public abstract List<Book> getAllBooksWithRateMoreThan4();
	
	public abstract List<String> getAllGenres();
}