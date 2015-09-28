package me.geakstr.insapp.dao;

import java.util.List;

import javax.ejb.Stateless;

import me.geakstr.insapp.dao.entities.User;

@Stateless
public class UserDao extends AbstractDao<User> {
	public UserDao() {
		super(User.class);
	}

	public User getUserByUsernameAndPassword(final String username, final String password) {
		final List<User> result = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
						  .setParameter("username", username)
					      .setParameter("password", password)
					      .getResultList();
		
		return result.isEmpty() ? null : result.get(0);
	}
	
	public List<User> getAllUsers() {
		return em.createQuery("SELECT u FROM User u").getResultList();
	}
}
