package me.geakstr.insapp.api.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.geakstr.insapp.api.dao.DriverInsurancesCoeffsDao;
import me.geakstr.insapp.api.entities.DriverInsuranceCoeff;
import me.geakstr.insapp.api.responses.DriverInsuranceCoeffResponse;

@Stateless
@Path("/")
public class Rest {
	@EJB
	private DriverInsurancesCoeffsDao driverInsurancesCoeffsDao;
	
	@GET
	@Path("/{license}")
	@Produces(MediaType.APPLICATION_JSON)
	public DriverInsuranceCoeffResponse license(@PathParam("license") final String license) {
		final DriverInsuranceCoeff res = driverInsurancesCoeffsDao.getInsuranceByDriverLicense(license);
		if (res == null) {
			return new DriverInsuranceCoeffResponse(null, null);
		}
		return new DriverInsuranceCoeffResponse(res.getFio(), res.getCoeff());
	}
}
