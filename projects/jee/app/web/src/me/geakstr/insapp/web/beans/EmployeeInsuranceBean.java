package me.geakstr.insapp.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.geakstr.insapp.business.facades.EmployeeInsuranceFacade;
import me.geakstr.insapp.dao.entities.Driver;
import me.geakstr.insapp.dao.entities.Insurance;

@ManagedBean
@ViewScoped
public class EmployeeInsuranceBean extends CrudBean<Insurance, EmployeeInsuranceFacade> {
	@EJB
	private EmployeeInsuranceFacade facade;
	
	private List<Driver> drivers;
	
	@Override
	protected EmployeeInsuranceFacade getFacade() {
		return facade;
	}
	
	@PostConstruct
	public void init() {
		init(Insurance.class);
	}
	
	@Override
	public void edit(final Insurance insurance) {
		super.edit(insurance);
		
		final List<Driver> drivers = facade.findDrivers(item);
		setDrivers(drivers);
	}
	
	@Override
	public void add() {
		facade.add(item, drivers);
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
}
