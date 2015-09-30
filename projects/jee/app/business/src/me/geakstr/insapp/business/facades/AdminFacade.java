package me.geakstr.insapp.business.facades;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.security.auth.spi.Util;

import me.geakstr.insapp.dao.UserDao;
import me.geakstr.insapp.dao.entities.User;

@Stateless
@LocalBean
public class AdminFacade implements ICrudFacade<User> {
	@EJB
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.findAllEmployees();
	}

	@Override
	public void delete(final User user) {
		userDao.remove(user);
	}

	@Override
	public void add(final User user) {
		user.setPassword(Util.createPasswordHash("SHA-256", "BASE64", null, null, user.getPassword()));
		user.setRole("EMPLOYEE");
		userDao.create(user);
	}

	@Override
	public void edit(final User user) {
		userDao.edit(user);
	}
}
