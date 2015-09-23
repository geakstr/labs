import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ru.aspu.javaee.lab5.datamanager.dao.BookDao;
import ru.aspu.javaee.lab5.entities.Book;

@ManagedBean
@ViewScoped
public class ManageBookBean {
	@ManagedProperty(value = "#{booksBean}")
	private BooksBean booksBean;

	@ManagedProperty(value = "#{bookDaoJpa}")
	private BookDao dao;

	private Book book;

	private int id;
	private String action;

	private boolean createChoice = true;
	private boolean editDeleteChoice = false;

	public void init() {
		if ("create".equals(action)) {
			this.book = new Book();
			setCreateChoice(true);
			setEditDeleteChoice(false);
		} else if ("edit".equals(action)) {
			this.book = dao.find(id);
			setCreateChoice(false);
			setEditDeleteChoice(true);
		}
	}

	public String createBookAction() {
		dao.insert(book);
		return "index";
	}

	public String editBookAction() {
		dao.update(book);
		return "index";
	}

	public String deleteBookAction() {
		dao.delete(book);
		return "index";
	}

	public BooksBean getBooksBean() {
		return booksBean;
	}

	public void setBooksBean(BooksBean booksBean) {
		this.booksBean = booksBean;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookDao getDao() {
		return dao;
	}

	public void setDao(BookDao dao) {
		this.dao = dao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isCreateChoice() {
		return createChoice;
	}

	public void setCreateChoice(boolean createChoice) {
		this.createChoice = createChoice;
	}

	public boolean isEditDeleteChoice() {
		return editDeleteChoice;
	}

	public void setEditDeleteChoice(boolean editDeleteChoice) {
		this.editDeleteChoice = editDeleteChoice;
	}

}
