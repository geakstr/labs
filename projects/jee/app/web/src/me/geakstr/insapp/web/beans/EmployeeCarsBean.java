package me.geakstr.insapp.web.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.geakstr.insapp.business.facades.EmployeeCarFacade;
import me.geakstr.insapp.dao.entities.Car;

@ManagedBean
@ViewScoped
public class EmployeeCarsBean extends CrudBean<Car, EmployeeCarFacade> {
	@EJB
	private EmployeeCarFacade facade;
	
	@Override
	protected EmployeeCarFacade getFacade() {
		return facade;
	}
	
	@PostConstruct
	public void init() {
		init(Car.class);
	}
}
