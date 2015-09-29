package me.geakstr.insapp.api.responses;

public class DriverInsuranceCoeffResponse {
	public final String fio;
	public final Double coeff;
	
	public DriverInsuranceCoeffResponse(final String fio, final Double coeff) {
		this.fio = fio;
		this.coeff = coeff;
	}
}
