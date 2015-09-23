package ru.aspu.javaee.lab5.datamanager.dao;

import java.util.List;

import ru.aspu.javaee.lab5.datamanager.dao.generics.IGenericDao;
import ru.aspu.javaee.lab5.entities.Comment;
import ru.aspu.javaee.lab5.entities.User;

public interface CommentDao extends IGenericDao<Comment> {
	public abstract List<Comment> getAll();
	public abstract User getUser(Comment comment);
} 
