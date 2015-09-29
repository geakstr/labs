package me.geakstr.insapp.api.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import me.geakstr.insapp.api.entities.DriverInsuranceCoeff;
import me.geakstr.insapp.api.facades.InsuranceCoeffsFacade;
import me.geakstr.insapp.api.responses.DriverInsuranceCoeffResponse;

@Stateless
@Path("/rest")
public class Rest {
	@EJB
	private InsuranceCoeffsFacade insuranceCoeffsFacade;

	@GET
	@Path("/{license}")
	@Produces("application/json; charset=UTF-8")
	public DriverInsuranceCoeffResponse license(@PathParam("license") final String license) {
		final DriverInsuranceCoeff res = insuranceCoeffsFacade.getInsuranceByDriverLicense(license);
		if (res == null) {
			return new DriverInsuranceCoeffResponse(null, null);
		}
		return new DriverInsuranceCoeffResponse(res.getFio(), res.getCoeff());
	}
}
