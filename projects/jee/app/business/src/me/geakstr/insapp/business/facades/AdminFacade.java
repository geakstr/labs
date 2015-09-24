package me.geakstr.insapp.business.facades;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import me.geakstr.insapp.dao.UserDao;

@Stateless
public class AdminFacade {
	@EJB
	private UserDao userDao;

	public String test() {
		return "admin/index?faces-redirect=true";
	}
}
