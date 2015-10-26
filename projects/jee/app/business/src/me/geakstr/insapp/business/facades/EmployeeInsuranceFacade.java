package me.geakstr.insapp.business.facades;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import me.geakstr.insapp.dao.InsuranceDao;
import me.geakstr.insapp.dao.InsuranceToDriverDao;
import me.geakstr.insapp.dao.entities.Driver;
import me.geakstr.insapp.dao.entities.Insurance;
import me.geakstr.insapp.dao.entities.InsuranceToDriver;

@Stateless
@LocalBean
public class EmployeeInsuranceFacade implements ICrudFacade<Insurance> {
	@EJB
	private InsuranceDao insuranceDao;
	
	@EJB
	private InsuranceToDriverDao insuranceToDriverDao;

	@Override
	public List<Insurance> findAll() {
		return insuranceDao.findAll();
	}

	@Override
	public void delete(final Insurance insurance) {
		insuranceDao.remove(insurance);
	}

	@Override
	public void add(final Insurance insurance) {
		insuranceDao.create(insurance);
	}

	@Override
	public void edit(final Insurance insurance) {
		insuranceDao.edit(insurance);
	}
	
	public void add(final Insurance insurance, final List<Driver> drivers) {
		insurance.setActive(true);
		
		for (final Driver driver : drivers) {
			final InsuranceToDriver insuranceToDriver = new InsuranceToDriver();
			
			insurance.addInsuranceToDriver(insuranceToDriver);
			driver.addInsuranceToDriver(insuranceToDriver);
		}
		
		this.add(insurance);
	}
	
	public List<Driver> findDrivers(final Insurance insurance) {
		return insuranceDao.findDrivers(insurance);
	}
}
