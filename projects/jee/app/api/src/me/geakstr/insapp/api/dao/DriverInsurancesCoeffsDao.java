package me.geakstr.insapp.api.dao;

import java.util.List;

import javax.ejb.Stateless;

import me.geakstr.insapp.api.entities.DriverInsuranceCoeff;

@Stateless
public class DriverInsurancesCoeffsDao extends AbstractDao<DriverInsuranceCoeff> {
	public DriverInsurancesCoeffsDao() {
		super(DriverInsuranceCoeff.class);
	}

	@SuppressWarnings("unchecked")
	public DriverInsuranceCoeff getInsuranceByDriverLicense(final String driverLicense) {
		final String sql = "SELECT d From DriverInsuranceCoeff d WHERE d.driver_license = :driver_license";
		final List<DriverInsuranceCoeff> result = em.createQuery(sql)
				.setParameter("driver_license", driverLicense)
				.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}
}
