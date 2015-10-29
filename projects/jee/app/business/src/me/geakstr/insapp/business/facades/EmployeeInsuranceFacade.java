package me.geakstr.insapp.business.facades;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import me.geakstr.insapp.dao.InsuranceDao;
import me.geakstr.insapp.dao.InsuranceToDriverDao;
import me.geakstr.insapp.dao.entities.Driver;
import me.geakstr.insapp.dao.entities.Insurance;
import me.geakstr.insapp.dao.entities.InsuranceToDriver;
import me.geakstr.insapp.responses.InsuranceCoeff;

@Stateless
@LocalBean
public class EmployeeInsuranceFacade implements ICrudFacade<Insurance> {
	@EJB
	private InsuranceDao insuranceDao;
	
	@EJB
	private InsuranceToDriverDao insuranceToDriverDao;
	
	private final static WebTarget restTarget = ClientBuilder.newClient().target("http://localhost:8080/api/rest/{license}");

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
		
		final Iterator<InsuranceToDriver> it = insuranceToDrivers.iterator();
		while(it.hasNext()) {
			final InsuranceToDriver itd = it.next();
			if (!drivers.contains(itd.getDriver())) {
				insuranceToDriverDao.remove(itd);
				it.remove();
			}
		}
		
		final List<Driver> currentDrivers = insuranceToDrivers.stream().map(x -> x.getDriver()).collect(Collectors.toList());
		for (final Driver driver : drivers) {
			if (!currentDrivers.contains(driver)) {
				final InsuranceToDriver itd = new InsuranceToDriver();
				
				driver.addInsuranceToDriver(itd);
				insurance.addInsuranceToDriver(itd);
				
				insuranceToDriverDao.create(itd);
			}
		}
		
		Double minCoeff = 1.1;
		for (final InsuranceToDriver insuranceToDriver : insurance.getInsuranceToDrivers()) {
			final InsuranceCoeff coeff = getCoeff(insuranceToDriver.getDriver().getLicense());
			
			if (coeff.coeff != null) {
				minCoeff = Math.min(coeff.coeff,  minCoeff);
			}
		}
		insurance.setCost(getInsuranceSum(insurance, minCoeff));
		
		insuranceDao.edit(insurance);
	}
	
	public void add(final Insurance insurance, final List<Driver> drivers) {
		insurance.setActive(true);
		
		Double minCoeff = 1.1;
		for (final Driver driver : drivers) {
			final InsuranceCoeff coeff = getCoeff(driver.getLicense());
			
			if (coeff.coeff != null) {
				minCoeff = Math.min(coeff.coeff,  minCoeff);
			}
			
			final InsuranceToDriver insuranceToDriver = new InsuranceToDriver();
			
			insurance.addInsuranceToDriver(insuranceToDriver);
			driver.addInsuranceToDriver(insuranceToDriver);
		}
		insurance.setCost(getInsuranceSum(insurance, minCoeff));
		
		this.add(insurance);
	}
	
	public List<Driver> findDrivers(final Insurance insurance) {
		return insuranceDao.findDrivers(insurance);
	}
	
	private Double getInsuranceSum(final Insurance insurance, final Double coeff) {
		final Double cost = insurance.getCost();
		
		return (cost - cost * coeff) * (insurance.getCar().getCar_power() / 100.0);
	}
	
	private InsuranceCoeff getCoeff(final String license) {
		return restTarget.resolveTemplate("license", license).request("application/json").get().readEntity(InsuranceCoeff.class);
	}
}
