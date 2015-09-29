package me.geakstr.insapp.dao;

import javax.ejb.Stateless;

import me.geakstr.insapp.dao.entities.User;

@Stateless
public class UserDao extends AbstractDao<User> {
	public UserDao() {
		super(User.class);
	}
}
