package me.geakstr.insapp.web.beans;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

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
	
	public void sortByLicense() {
	   Collections.sort(items, new Comparator<Driver>() {
		@Override
		public int compare(Driver a, Driver b) {
			return a.getLicense().compareToIgnoreCase(b.getLicense());
		}
	   });	
	}
	
	public void sortByFio() {
	   Collections.sort(items, new Comparator<Driver>() {
		@Override
		public int compare(Driver a, Driver b) {
			return a.getFio().compareToIgnoreCase(b.getFio());
		}
	   });	
	}
	
	@Override
	public boolean query() {
		if (!super.query()) {
			items = items.stream().filter(x -> x.getLicense().equalsIgnoreCase(query)).collect(Collectors.toList());
		}
		return true;
	}
}
