package me.geakstr.insapp.dao.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car implements IEntity {
	@Id
	@Column(nullable = false)
	private String car_num;

	@Column(nullable = false, unique = false)
	private String car_model;
	
	@Column(nullable = false, unique = false)
	private Integer car_power;
	
	@ManyToOne
	@JoinColumn(name = "driver_license", referencedColumnName = "license")
	private Driver driver;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "car")
	private List<Insurance> insurances;
	
	public String getCar_num() {
		return car_num;
	}

	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}

	public String getCar_model() {
		return car_model;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public Integer getCar_power() {
		return car_power;
	}

	public void setCar_power(Integer car_power) {
		this.car_power = car_power;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
		driver.getCars().add(this);
	}

	public List<Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car_num == null) ? 0 : car_num.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (car_num == null) {
			if (other.car_num != null)
				return false;
		} else if (!car_num.equals(other.car_num))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Car : car_num = %s; car_model = %s; car_power = %s; driver_license = %s",
				car_num,
				car_model,
				car_power,
				driver.getLicense());
	}
}
