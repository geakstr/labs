package me.geakstr.insapp.web.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.geakstr.insapp.business.facades.AdminFacade;
import me.geakstr.insapp.dao.entities.User;

@ManagedBean
@ViewScoped
public class AdminBean extends CrudBean<User, AdminFacade> {
	@EJB
	private AdminFacade facade;
	
	@Override
	protected AdminFacade getFacade() {
		return facade;
	}
	
	@PostConstruct
	public void init() {
		init(User.class);
	}
	
	public void block(final User user) {
		getFacade().block(user, true);
	}
	
	public void unblock(final User user) {
		getFacade().block(user, false);
	}
}
