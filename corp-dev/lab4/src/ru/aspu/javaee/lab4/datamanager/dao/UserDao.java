package ru.aspu.javaee.lab4.datamanager.dao;

import java.util.List;

import ru.aspu.javaee.lab4.datamanager.dao.generics.IGenericDao;
import ru.aspu.javaee.lab4.entities.Comment;
import ru.aspu.javaee.lab4.entities.User;

public interface UserDao extends IGenericDao<User> {
	public abstract List<User> getAll();
	public abstract List<Comment> getUserComments(User user);
}
