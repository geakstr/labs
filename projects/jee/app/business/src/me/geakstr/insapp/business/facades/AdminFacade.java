package me.geakstr.insapp.business.facades;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.security.auth.spi.Util;

import me.geakstr.insapp.dao.UserDao;
import me.geakstr.insapp.dao.entities.User;


@Stateless
public class AdminFacade {
	@EJB
	private UserDao userDao;

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public void deleteUser(final User user) {
		userDao.remove(user);
	}
	
	public void addUser(final User user) {
		user.setPassword(Util.createPasswordHash("SHA-256", "BASE64", null, null, user.getPassword()));
		user.setRole("EMPLOYEE");
		userDao.create(user);
	}
	
	public void editUser(final User user) {
		userDao.edit(user);
	}
}
