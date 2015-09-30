package me.geakstr.insapp.business.facades;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import me.geakstr.insapp.dao.DriverDao;
import me.geakstr.insapp.dao.entities.Driver;

@Stateless
@LocalBean
public class EmployeeDriverFacade implements ICrudFacade<Driver> {
	@EJB
	private DriverDao driverDao;

	@Override
	public List<Driver> findAll() {
		return driverDao.findAll();
	}

	@Override
	public void delete(final Driver driver) {
		driverDao.remove(driver);
	}

	@Override
	public void add(final Driver driver) {
		driverDao.create(driver);
	}

	@Override
	public void edit(final Driver driver) {
		driverDao.edit(driver);
	}
}
