package me.geakstr.insapp.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "insurance_to_driver")
public class InsuranceToDriver implements IEntity {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "insurance_id", referencedColumnName = "id")
	private Insurance insurance;
	
	@ManyToOne
	@JoinColumn(name = "driver_license", referencedColumnName = "license")
	private Driver driver;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	@Override
	public String toString() {
		return String.format(
				"InsuranceToDriver : \n id = %s \n %s; \n %s",
				id,
				insurance.toString(),
				driver.toString());
	}
}
