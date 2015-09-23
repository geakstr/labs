package ru.aspu.javaee.lab5.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cycle implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cycle")
	private List<Book> booksList = new ArrayList<Book>();

	public Cycle(String name) {
		this.name = name;
	}

	public Cycle(String name, List<Book> booksList) {
		this.name = name;
		this.booksList = booksList;
	}

	public void addBook(Book book) {
		this.booksList.add(book);
		book.setCycle(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}

	@Override
	public String toString() {
		return name;
	}
}
