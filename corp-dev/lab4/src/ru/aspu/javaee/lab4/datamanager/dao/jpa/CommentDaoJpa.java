package ru.aspu.javaee.lab4.datamanager.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import ru.aspu.javaee.lab4.datamanager.DataManager;
import ru.aspu.javaee.lab4.datamanager.dao.CommentDao;
import ru.aspu.javaee.lab4.datamanager.dao.generics.GenericDaoJpa;
import ru.aspu.javaee.lab4.entities.Comment;
import ru.aspu.javaee.lab4.entities.User;

public class CommentDaoJpa extends GenericDaoJpa<Comment> implements CommentDao {
	public CommentDaoJpa() {
		super(Comment.class);
	}

	@Override
	public List<Comment> getAll() {
		Query query = DataManager.getInstance().createQuery("SELECT c FROM Comment c ORDER BY id");
		List<Comment> resultSet = query.getResultList();
		return resultSet;
	}
	
	@Override
	public User getUser(Comment comment) {
		Query query = DataManager.getInstance().createQuery("SELECT c.user FROM Comment c WHERE c.id = :id");
		query.setParameter("id", comment.getId());
		User user = (User) query.getResultList().get(0);
		return user;
	}
}
