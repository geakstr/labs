import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import ru.aspu.javaee.lab5.datamanager.dao.BookDao;
import ru.aspu.javaee.lab5.entities.Book;

@ManagedBean
@ViewScoped
public class BooksBean {
	@ManagedProperty(value = "#{bookDaoJpa}")
	private BookDao dao;

	private List<Book> books;
	private List<String> genres;

	private String sort;
	private String search;
	private String genre;

	public BooksBean() {
	}

	public void init() {
		if (null != genre) {
			genre = genre.trim();
			this.books = dao.getAllBooksByGenre(genre);
		} else if (null != search) {
			search = search.trim();
			this.books = dao.getAllBooksByString(search);
		} else if (null != sort) {
			if ("title".equals(sort)) {
				this.books = dao.getAllOrderedByName();
			} else if ("year".equals(sort)) {
				this.books = dao.getAllOrderedByYearOfPublishing();
			}
		} else {
			this.books = dao.getAll();
		}
		this.genres = dao.getAllGenres();
	}
	
	public void genreSelectedAction(ValueChangeEvent e){
		if (null == e.getNewValue()) {
			this.books = dao.getAll();
		} else {
			this.books = dao.getAllBooksByGenre(e.getNewValue().toString());
		}		
	}

	public BookDao getDao() {
		return dao;
	}

	public void setDao(BookDao dao) {
		this.dao = dao;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	

}
