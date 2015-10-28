package me.geakstr.insapp.business.facades;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public void save(final Insurance insurance, final List<Driver> drivers) {
		final List<InsuranceToDriver> insuranceToDrivers = insurance.getInsuranceToDrivers();
		
		Iterator<InsuranceToDriver> it = insuranceToDrivers.iterator();
		
		while(it.hasNext()) {
			final InsuranceToDriver itd = it.next();
			if (!drivers.contains(itd.getDriver())) {
				insuranceToDriverDao.remove(itd);
				it.remove();
			}
		}
		insuranceDao.edit(insurance);
		
		final List<Driver> currentDrivers = insuranceToDrivers.stream().map(x -> x.getDriver()).collect(Collectors.toList());
		for (final Driver driver : drivers) {
			if (!currentDrivers.contains(driver)) {
				final InsuranceToDriver itd = new InsuranceToDriver();
				
				driver.addInsuranceToDriver(itd);
				insurance.addInsuranceToDriver(itd);
				
				insuranceToDriverDao.create(itd);
			}
		}
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
