package ru.aspu.javaee.lab5.datamanager.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import ru.aspu.javaee.lab5.datamanager.DataManager;
import ru.aspu.javaee.lab5.datamanager.dao.UserDao;
import ru.aspu.javaee.lab5.datamanager.dao.generics.GenericDaoJpa;
import ru.aspu.javaee.lab5.entities.Comment;
import ru.aspu.javaee.lab5.entities.User;

public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {
	public UserDaoJpa() {
		super(User.class);
		insert(new User("Вася"));
		insert(new User("Петя"));
	}

	@Override
	public List<User> getAll() {
		Query query = DataManager.getInstance().createQuery("SELECT u FROM User u ORDER BY id");
		List<User> resultSet = query.getResultList();
		return resultSet;
	}

	@Override
	public List<Comment> getUserComments(User user) {
		Query query = DataManager.getInstance().createQuery("SELECT u.commentsList FROM User u WHERE u.id = :id");
		query.setParameter("id", user.getId());
		List<Comment> resultSet = query.getResultList();
		return resultSet;
	}
}
