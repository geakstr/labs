package me.geakstr.insapp.ejb.facades;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import me.geakstr.insapp.dao.UserDao;

@Stateless
public class AdminFacade {
	@EJB
	private UserDao userDao;
	
	public String test() {
		try {
		userDao.getUserByUsernameAndPassword("azaza", "1");
		}
		 catch(Exception e) {
			 System.err.println(e.getMessage());
		 }
		return "admin?faces-redirect=true";
	}
}
