package ru.aspu.javaee.lab5.datamanager.dao.jpa;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.Query;

import ru.aspu.javaee.lab5.datamanager.DataManager;
import ru.aspu.javaee.lab5.datamanager.dao.BookDao;
import ru.aspu.javaee.lab5.datamanager.dao.generics.GenericDaoJpa;
import ru.aspu.javaee.lab5.entities.Book;
import ru.aspu.javaee.lab5.entities.Comment;

@ManagedBean
@ApplicationScoped
public class BookDaoJpa extends GenericDaoJpa<Book> implements BookDao {
	public BookDaoJpa() {
		super(Book.class);
		insert(new Book("Война и мир", "Толстой", "роман", "Эксмо", 2007));
		insert(new Book("Преступление и наказание", "Достоевский", "философский и психологический роман", "Эксмо", 2005));
		insert(new Book("Гарри Поттер", "Роулинг", "фэнтези", "Махаон", 2014));
	}

	@Override
	public List<Comment> getAllComments() {
		return DataManager.getInstance().createQuery("SELECT b.commentsList FROM Book b").getResultList();
	}

	@Override
	public List<Comment> getAllCommentsForBook(Book book) {
		Query q = DataManager.getInstance().createQuery("SELECT b.commentsList FROM Book b WHERE b.id = :id");
		q.setParameter("id", book.getId());
		return q.getResultList();
	}

	@Override
	public List<Book> getAll() {
		return DataManager.getInstance().createQuery("SELECT b FROM Book b ORDER BY id").getResultList();
	}

	@Override
	public List<Book> getAllOrderedByName() {
		return DataManager.getInstance().createQuery("SELECT b FROM Book b ORDER BY name ASC").getResultList();
	}

	@Override
	public List<Book> getAllOrderedByYearOfPublishing() {
		return DataManager.getInstance().createQuery("SELECT b FROM Book b ORDER BY yearOfPublishing").getResultList();
	}
	
	public List<String> getAllGenres() {
		return Book.genres;
	}

	@Override
	public List<Book> getAllBooksByGenre(String genre) {
		Query q = DataManager.getInstance().createQuery("SELECT b FROM Book b WHERE genre = :genre");
		q.setParameter("genre", genre);
		return q.getResultList();
	}

	@Override
	public List<Book> getAllBooksByString(String name) {
		Query q = DataManager.getInstance().createQuery("SELECT b FROM Book b WHERE name LIKE :name");
		q.setParameter("name", "%" + name + "%");
		return q.getResultList();
	}

	@Override
	public List<Book> getAllBooksWithRateMoreThan4() {
		return DataManager.getInstance().createQuery("SELECT b FROM Book b WHERE b.id IN (SELECT c.book.id FROM Comment c GROUP BY c.book HAVING AVG(c.rating) > 4)")
				.getResultList();
	}
}
