package ru.aspu.javaee.lab4.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String author;
	private String genre;
	private String publishing;
	private int yearOfPublishing;

	private static List<String> genres = Arrays.asList("фэнтези", "роман", "философский и психологический роман");

	@ManyToOne
	private Cycle cycle;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
	private Set<Comment> comments = new LinkedHashSet<Comment>();
	
	public Book() {}

	public Book(String name, String author, int yearOfPublishing) {
		this.name = name;
		this.author = author;
		this.yearOfPublishing = yearOfPublishing;
	}

	public Book(String name, String author, String genre, String publishing, int yearOfPublishing) {
		this.name = name;
		this.author = author;
		setGenre(genre);
		this.publishing = publishing;
		this.yearOfPublishing = yearOfPublishing;
	}

	public Book(String name, String author, String genre, String publishing, int yearOfPublishing, Cycle cycle) {
		this.name = name;
		this.author = author;
		setGenre(genre);
		this.publishing = publishing;
		this.yearOfPublishing = yearOfPublishing;
		this.cycle = cycle;
	}

	public Book(int id, String name, String author, String genre, String publishing, int yearOfPublishing) {
		this.id = id;
		this.name = name;
		this.author = author;
		setGenre(genre);
		this.publishing = publishing;
		this.yearOfPublishing = yearOfPublishing;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
		comment.setBook(this);
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if (!genres.contains(genre)) {
			throw new IllegalArgumentException("Жанр не соответствует заданному списку жанров");
		} else {
			this.genre = genre;
		}
	}

	public String getPublishing() {
		return publishing;
	}

	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}

	public int getYearOfPublishing() {
		return yearOfPublishing;
	}

	public void setYearOfPublishing(int yearOfPublishing) {
		this.yearOfPublishing = yearOfPublishing;
	}

	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public Set<Comment> getCommentsList() {
		return comments;
	}

	public void setCommentsList(Set<Comment> commentsList) {
		this.comments = commentsList;
	}

	@Override
	public String toString() {
		return id + " | " + name + " | " + author + " | " + genre + " | " + publishing + " | " + yearOfPublishing + " | " + cycle;
	}
}
