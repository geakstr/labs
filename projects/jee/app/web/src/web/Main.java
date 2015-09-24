package web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import me.geakstr.insapp.ejb.facades.AdminFacade;

@ManagedBean
@RequestScoped
public class Main {
	@EJB
	private AdminFacade admin;
	
	public String admin() {
		return admin.test();
	}
}
