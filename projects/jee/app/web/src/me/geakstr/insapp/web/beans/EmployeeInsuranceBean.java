package me.geakstr.insapp.web.beans;

import java.util.ArrayList;
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
		
		setDrivers(facade.findDrivers(item));
	}
	
	@Override
	public void save() {
		facade.save(item, drivers);
		
		drivers = new ArrayList<>();
	}
	
	@Override
	public void add() {
		items.add(item);
		facade.add(item, drivers);
		
		drivers = new ArrayList<>();
		item = new Insurance();
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
}
