package ru.aspu.javaee.lab5.datamanager.dao;

import java.util.List;

import ru.aspu.javaee.lab5.datamanager.dao.generics.IGenericDao;
import ru.aspu.javaee.lab5.entities.Comment;
import ru.aspu.javaee.lab5.entities.User;

public interface UserDao extends IGenericDao<User> {
	public abstract List<User> getAll();
	public abstract List<Comment> getUserComments(User user);
}
