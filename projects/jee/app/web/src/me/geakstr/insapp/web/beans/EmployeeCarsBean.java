package me.geakstr.insapp.web.beans;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

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
	
	public void sortByNum() {
	   Collections.sort(items, new Comparator<Car>() {
		@Override
		public int compare(Car a, Car b) {
			return a.getCar_num().compareToIgnoreCase(b.getCar_num());
		}
	   });	
	}
	
	public void sortByLicense() {
	   Collections.sort(items, new Comparator<Car>() {
		@Override
		public int compare(Car a, Car b) {
			return a.getDriver().getLicense().compareToIgnoreCase(b.getDriver().getLicense());
		}
	   });	
	}
	
	@Override
	public boolean query() {
		if (!super.query()) {
			items = items.stream().filter(x -> x.getCar_num().equalsIgnoreCase(query)).collect(Collectors.toList());
		}
		return true;
	}
}
