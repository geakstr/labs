package me.geakstr.insapp.web.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.geakstr.insapp.business.facades.AdminFacade;
import me.geakstr.insapp.dao.entities.User;

@ManagedBean
@ViewScoped
public class AdminBean extends CrudBean<User, AdminFacade> {
	@PostConstruct
	public void init() {
		init(User.class);
	}
}
