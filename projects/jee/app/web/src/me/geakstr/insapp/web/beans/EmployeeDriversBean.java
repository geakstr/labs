package me.geakstr.insapp.web.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.geakstr.insapp.business.facades.EmployeeDriverFacade;
import me.geakstr.insapp.dao.entities.Driver;

@ManagedBean
@ViewScoped
public class EmployeeDriversBean extends CrudBean<Driver, EmployeeDriverFacade> {
	@EJB
	private EmployeeDriverFacade facade;
	
	@Override
	protected EmployeeDriverFacade getFacade() {
		return facade;
	}
	
	@PostConstruct
	public void init() {
		init(Driver.class);
	}
}
