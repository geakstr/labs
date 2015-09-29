package me.geakstr.insapp.dao;

import java.util.List;

import javax.ejb.Stateless;

import me.geakstr.insapp.dao.entities.User;

@Stateless
public class UserDao extends AbstractDao<User> {
	public UserDao() {
		super(User.class);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllEmployees() {
		return em.createQuery("SELECT u From User u WHERE u.role = 'EMPLOYEE' or u.role = 'BLOCKED'").getResultList();
	}
}
