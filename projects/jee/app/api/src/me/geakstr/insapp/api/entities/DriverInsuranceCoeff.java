package me.geakstr.insapp.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "insurances_coeffs")
public class DriverInsuranceCoeff {
	@Id 
	@Column(nullable = false)
	private String driver_license;
	
	@Column(nullable = false, unique = false)
	private String fio;
	
	@Column(nullable = false, unique = false)
	private Double coeff;

	public String getDriver_license() {
		return driver_license;
	}

	public void setDriver_license(String driver_license) {
		this.driver_license = driver_license;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public Double getCoeff() {
		return coeff;
	}

	public void setCoeff(Double coeff) {
		this.coeff = coeff;
	}
	
	public String toString() {
		return String.format(
				"InsuranceCoeff : driver_license = %s; fio = %s; coeff = %s",
				driver_license,
				fio,
				coeff);
	}
}
