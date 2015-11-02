package me.geakstr.insapp.web.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.geakstr.insapp.business.facades.EmployeeInsuranceFacade;
import me.geakstr.insapp.dao.entities.Car;
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
		items = getFacade().findAll();
		
		super.reload();
	}
	
	@Override
	public void add() {
		items.add(item);
		facade.add(item, drivers);
		
		drivers = new ArrayList<>();
		item = new Insurance();
	}
	
	public void sortByID() {
	   Collections.sort(items, new Comparator<Insurance>() {
		@Override
		public int compare(Insurance a, Insurance b) {
			return a.getId().compareTo(b.getId());
		}
	   });	
	}
	
	public void sortByNum() {
	   Collections.sort(items, new Comparator<Insurance>() {
		@Override
		public int compare(Insurance a, Insurance b) {
			return a.getCar().getCar_num().compareToIgnoreCase(b.getCar().getCar_num());
		}
	   });	
	}
	
	public void sortByDateFrom() {
	   Collections.sort(items, new Comparator<Insurance>() {
		@Override
		public int compare(Insurance a, Insurance b) {
			return a.getDate_from().compareTo(b.getDate_from());
		}
	   });	
	}
	
	public void sortByDateTo() {
	   Collections.sort(items, new Comparator<Insurance>() {
		@Override
		public int compare(Insurance a, Insurance b) {
			return a.getDate_to().compareTo(b.getDate_to());
		}
	   });	
	}
	
	public void sortByCost() {
	   Collections.sort(items, new Comparator<Insurance>() {
		@Override
		public int compare(Insurance a, Insurance b) {
			return a.getCost().compareTo(b.getCost());
		}
	   });	
	}
	
	@Override
	public boolean query() {
		if (!super.query()) {
			try {
				items = items.stream().filter(x -> x.getId() == Integer.parseInt(query)).collect(Collectors.toList());
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		}
		return true;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
}
