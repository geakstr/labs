package me.geakstr.insapp.api.facades;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import me.geakstr.insapp.api.dao.DriverInsurancesCoeffsDao;
import me.geakstr.insapp.api.entities.DriverInsuranceCoeff;

@Stateless
public class InsuranceCoeffsFacade {
	@EJB
	private DriverInsurancesCoeffsDao driverInsurancesCoeffsDao;
	
	public List<DriverInsuranceCoeff> getAllCoeffs() {
		return driverInsurancesCoeffsDao.getAllCoeffs();
	}
	
	public DriverInsuranceCoeff getInsuranceByDriverLicense(final String driverLicense) {
		return driverInsurancesCoeffsDao.getInsuranceByDriverLicense(driverLicense);
	}
	
	public void add(final DriverInsuranceCoeff coeff) {
		driverInsurancesCoeffsDao.create(coeff);
	}
	
	public void edit(final DriverInsuranceCoeff coeff) {
		driverInsurancesCoeffsDao.edit(coeff);
	}
	
	public void delete(final DriverInsuranceCoeff coeff) {
		driverInsurancesCoeffsDao.remove(coeff);
	}
}
